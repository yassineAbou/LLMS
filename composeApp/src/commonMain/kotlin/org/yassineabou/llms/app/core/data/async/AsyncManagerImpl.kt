@file:OptIn(ExperimentalTime::class)

package org.yassineabou.llms.app.core.data.async

import co.touchlab.kermit.Logger
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import org.yassineabou.llms.Chat_messages
import org.yassineabou.llms.Chats
import org.yassineabou.llms.Generated_images
import org.yassineabou.llms.User
import org.yassineabou.llms.app.core.data.local.LlmsDatabaseRepository
import org.yassineabou.llms.app.core.data.remote.rpc.RemoteDataSource
import kotlin.concurrent.Volatile
import kotlin.jvm.JvmName
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

/**
 * Production implementation of [AsyncManager].
 *
 * ## Strategy
 * - **Not Logged In**: All operations go directly to local database
 * - **On Login**: Pulls remote data first, then pushes local data
 * - **Logged In**: Periodic two-way sync (pull remote → push local)
 *
 * ## Architecture
 * - Local-first: All writes go to local DB immediately
 * - Background sync: Remote operations run in coroutines
 * - Retry mechanism: Failed operations are tracked and retried
 */
class AsyncManagerImpl(
    private val localDb: LlmsDatabaseRepository,
    private val remoteDataSource: RemoteDataSource,
    private val config: SyncConfig = SyncConfig(),
    private val scope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Default),
    private val clock: Clock = Clock.System
) : AsyncManager {

    private val logger = Logger.withTag("AsyncManager")

    // =================================================================================
    //                                    STATE
    // =================================================================================

    private val _isLoggedIn = MutableStateFlow(false)
    override val isLoggedIn: StateFlow<Boolean> = _isLoggedIn.asStateFlow()

    private val _currentUserId = MutableStateFlow<String?>(null)
    override val currentUserId: StateFlow<String?> = _currentUserId.asStateFlow()

    private val _syncState = MutableStateFlow<SyncState>(SyncState.Idle)
    override val syncState: StateFlow<SyncState> = _syncState.asStateFlow()

    // =================================================================================
    //                                SYNC INTERNALS
    // =================================================================================

    private var syncJob: Job? = null
    private val syncMutex = Mutex()
    private val loginMutex = Mutex()
    private val pendingSync = PendingSyncTracker()

    @Volatile
    private var isLoginInProgress = false

    // =================================================================================
    //                               INITIALIZATION
    // =================================================================================

    init {
        observeAuthStateChanges()
    }

    // =================================================================================
    //                            AUTH STATE OBSERVATION
    // =================================================================================

    private fun observeAuthStateChanges() {
        scope.launch {
            localDb.getCurrentUser().collect { user ->
                if (shouldSkipAuthChange()) {
                    logger.d { "Skipping auth state change - login in progress" }
                    return@collect
                }
                handleAuthStateChange(previouslyLoggedIn = _isLoggedIn.value, user = user)
            }
        }
    }

    private suspend fun shouldSkipAuthChange(): Boolean =
        loginMutex.withLock { isLoginInProgress }

    private suspend fun handleAuthStateChange(previouslyLoggedIn: Boolean, user: User?) {
        val isNowLoggedIn = user != null
        updateAuthState(isLoggedIn = isNowLoggedIn, userId = user?.google_sub_id)

        when {
            isNowLoggedIn && !previouslyLoggedIn -> {
                logger.i { "Auth state restored: ${user?.email}" }
                startSync()
            }
            !isNowLoggedIn && previouslyLoggedIn -> {
                logger.i { "User logged out" }
                stopSync()
            }
        }
    }

    private fun updateAuthState(isLoggedIn: Boolean, userId: String?) {
        _isLoggedIn.value = isLoggedIn
        _currentUserId.value = userId
    }

    // =================================================================================
    //                            PUBLIC AUTH OPERATIONS
    // =================================================================================

    override suspend fun onUserLogin(
        userId: String,
        email: String,
        username: String,
        profilePicUrl: String?
    ) {
        logger.i { "Processing login for: $email" }

        executeWithLoginLock {
            val remoteUserCreated = createRemoteUserSafely(userId, email, username, profilePicUrl)
            saveUserLocally(userId, email, username, profilePicUrl)
            updateAuthState(isLoggedIn = true, userId = userId)

            if (remoteUserCreated) {
                performFirstLoginSync()
            } else {
                logger.w { "Remote user creation failed - starting regular sync" }
                startSync()
            }
        }
    }

    override suspend fun deleteUserAccount() {
        val userId = _currentUserId.value
        logger.i { "Deleting user account: $userId" }

        stopSync()

        if (userId != null) {
            runCatchingLogged("delete remote user") {
                remoteDataSource.deleteUser(userId)
                logger.i { "Remote user deleted successfully" }
            }
        }

        localDb.clearAllChats()
        localDb.clearAllImages()
        localDb.clearUser()

        resetState()

        logger.i { "User account deleted completely" }
    }

    override suspend fun onUserLogout() {
        logger.i { "Processing logout" }
        stopSync()
        localDb.clearUser()
        resetState()
    }

    // =================================================================================
    //                               AUTH HELPERS
    // =================================================================================

    private suspend inline fun <T> executeWithLoginLock(block: () -> T): T {
        loginMutex.withLock { isLoginInProgress = true }
        return try {
            block()
        } finally {
            loginMutex.withLock { isLoginInProgress = false }
        }
    }

    private suspend fun createRemoteUserSafely(
        userId: String,
        email: String,
        username: String,
        profilePicUrl: String?
    ): Boolean = runCatchingLogged("create remote user") {
        remoteDataSource.getOrCreateUser(
            id = userId,
            username = username,
            email = email,
            profilePicUrl = profilePicUrl
        )
    } != null

    private suspend fun saveUserLocally(
        userId: String,
        email: String,
        username: String,
        profilePicUrl: String?
    ) {
        localDb.saveUser(
            googleSubId = userId,
            email = email,
            username = username,
            profilePicUrl = profilePicUrl,
            createdAt = clock.now().toString()
        )
    }

    private fun resetState() {
        updateAuthState(isLoggedIn = false, userId = null)
        pendingSync.clear()
    }

    // =================================================================================
    //                               SYNC LIFECYCLE
    // =================================================================================

    override fun startSync() {
        if (syncJob?.isActive == true) {
            logger.d { "Sync already running" }
            return
        }

        logger.i { "Starting periodic sync (interval: ${config.syncInterval})" }

        syncJob = scope.launch {
            performSyncIfLoggedIn() // Immediate sync on start

            while (isActive) {
                delay(config.syncInterval)
                performSyncIfLoggedIn()
            }
        }
    }

    override fun stopSync() {
        logger.i { "Stopping sync" }
        syncJob?.cancel()
        syncJob = null
        _syncState.value = SyncState.Idle
    }

    override suspend fun forceSyncNow() {
        performSyncIfLoggedIn()
    }

    private suspend fun performSyncIfLoggedIn() {
        _currentUserId.value?.let { performTwoWaySync(it) }
    }

    // =================================================================================
    //                               SYNC EXECUTION
    // =================================================================================

    private suspend fun performFirstLoginSync() {
        val userId = _currentUserId.value ?: return
        logger.i { "Performing first login sync..." }

        executeSyncWithStateTracking {
            pullAllRemoteData(userId)
            pushAllLocalData(userId)
        }

        startSync()
    }

    private suspend fun performTwoWaySync(userId: String) {
        syncMutex.withLock {
            logger.d { "Starting sync cycle..." }

            executeSyncWithStateTracking {
                pullAllRemoteData(userId)
                pushPendingData(userId)
            }
        }
    }

    private suspend inline fun executeSyncWithStateTracking(block: suspend () -> Unit) {
        _syncState.value = SyncState.Syncing

        val result = runCatching { block() }

        _syncState.value = result.fold(
            onSuccess = {
                logger.d { "Sync completed successfully" }
                SyncState.Success()
            },
            onFailure = { error ->
                logger.e(error) { "Sync failed" }
                SyncState.Error(error.message ?: "Sync failed")
            }
        )
    }

    // =================================================================================
    //                        PULL OPERATIONS (Remote → Local)
    // =================================================================================

    private suspend fun pullAllRemoteData(userId: String) {
        pullRemoteChats(userId)
        pullRemoteImages(userId)
    }

    private suspend fun pullRemoteChats(userId: String) {
        runCatchingLogged("pull remote chats") {
            val existingIds = localDb.getAllChats().first().toIdSet()

            remoteDataSource.getChatsForUser(userId)
                .filter { it.id !in existingIds }
                .collect { remoteChat ->
                    logger.d { "Pulled chat: ${remoteChat.id}" }
                    localDb.insertChat(remoteChat)
                }
        }
    }

    private suspend fun pullRemoteImages(userId: String) {
        runCatchingLogged("pull remote images") {
            val existingIds = localDb.getAllImages().first().toIdSet()

            remoteDataSource.getImagesForUser(userId)
                .filter { it.id !in existingIds }
                .collect { remoteImage ->
                    logger.d { "Pulled image: ${remoteImage.id}" }
                    localDb.insertImage(remoteImage)
                }
        }
    }

    // =================================================================================
    //                        PUSH OPERATIONS (Local → Remote)
    // =================================================================================

    private suspend fun pushAllLocalData(userId: String) {
        val chats = localDb.getAllChats().first()
        val images = localDb.getAllImages().first()

        logger.i { "Pushing ${chats.size} chats and ${images.size} images to remote" }

        chats.forEach { pushChat(userId, it) }
        images.forEach { pushImage(userId, it) }
    }

    private suspend fun pushPendingData(userId: String) {
        pushPendingChats(userId)
        pushPendingImages(userId)
    }

    private suspend fun pushPendingChats(userId: String) {
        val pendingIds = pendingSync.chatIds
        if (pendingIds.isEmpty()) return

        logger.d { "Pushing ${pendingIds.size} pending chats" }

        localDb.getAllChats().first()
            .filter { it.id in pendingIds }
            .forEach { pushChat(userId, it) }
    }

    private suspend fun pushPendingImages(userId: String) {
        val pendingIds = pendingSync.imageIds
        if (pendingIds.isEmpty()) return

        logger.d { "Pushing ${pendingIds.size} pending images" }

        localDb.getAllImages().first()
            .filter { it.id in pendingIds }
            .forEach { pushImage(userId, it) }
    }

    private suspend fun pushChat(userId: String, chat: Chats): Boolean = try {
        remoteDataSource.createChat(
            id = chat.id,
            userId = userId,
            title = chat.title,
            description = chat.description,
            textModelName = chat.text_model_name,
            isBookmarked = chat.is_bookmarked == 1L
        )
        pushMessagesForChat(userId, chat.id)
        pendingSync.markChatSynced(chat.id)
        logger.d { "Pushed chat: ${chat.id}" }
        true
    } catch (e: Exception) {
        logger.e(e) { "Failed to push chat: ${chat.id}" }
        pendingSync.markChatPending(chat.id)
        false
    }

    private suspend fun pushMessagesForChat(userId: String, chatId: String) {
        localDb.getMessagesByChatId(chatId).first().forEach { message ->
            remoteDataSource.addMessage(
                userId = userId,
                chatId = chatId,
                message = message.message,
                isUser = message.is_user == 1L
            )
        }
    }

    private suspend fun pushImage(userId: String, image: Generated_images): Boolean = try {
        remoteDataSource.createImage(
            id = image.id,
            userId = userId,
            prompt = image.prompt,
            imageData = image.image_data,
            imageModelName = image.image_model_name
        )
        pendingSync.markImageSynced(image.id)
        logger.d { "Pushed image: ${image.id}" }
        true
    } catch (e: Exception) {
        logger.e(e) { "Failed to push image: ${image.id}" }
        pendingSync.markImagePending(image.id)
        false
    }

    // =================================================================================
    //                              CHAT OPERATIONS
    // =================================================================================

    override fun getAllChats(): Flow<List<Chats>> = localDb.getAllChats()

    override suspend fun insertChatWithMessages(chat: Chats, messages: List<Chat_messages>) {
        localDb.insertChatWithMessages(chat, messages)
        launchRemoteSync { userId -> pushChat(userId, chat) }
    }

    override suspend fun insertChat(chat: Chats) {
        localDb.insertChat(chat)
        launchChatSync(chat)
    }

    override suspend fun deleteChatById(id: String) {
        localDb.deleteChatById(id)
        launchRemoteSync { userId ->
            runCatchingLogged("delete remote chat") {
                remoteDataSource.deleteChat(userId, id)
            }
        }
    }

    override suspend fun clearAllChats() {
        localDb.clearAllChats()
        launchRemoteSync { userId ->
            runCatchingLogged("delete all remote chats") {
                remoteDataSource.getChatsForUser(userId).collect { chat ->
                    remoteDataSource.deleteChat(userId, chat.id)
                }
            }
        }
    }

    private fun launchChatSync(chat: Chats) {
        launchRemoteSync { userId ->
            val success = runCatchingLogged("sync chat") {
                remoteDataSource.createChat(
                    id = chat.id,
                    userId = userId,
                    title = chat.title,
                    description = chat.description,
                    textModelName = chat.text_model_name,
                    isBookmarked = chat.is_bookmarked == 1L
                )
            } != null

            if (!success) pendingSync.markChatPending(chat.id)
        }
    }

    // =================================================================================
    //                             MESSAGE OPERATIONS
    // =================================================================================

    override fun getMessagesByChatId(chatId: String): Flow<List<Chat_messages>> =
        localDb.getMessagesByChatId(chatId)

    // =================================================================================
    //                              IMAGE OPERATIONS
    // =================================================================================

    override fun getAllImages(): Flow<List<Generated_images>> = localDb.getAllImages()

    override suspend fun insertImage(image: Generated_images) {
        localDb.insertImage(image)
        launchRemoteSync { userId -> pushImage(userId, image) }
    }

    override suspend fun deleteImageById(id: String) {
        localDb.deleteImageById(id)
        launchRemoteSync { userId ->
            runCatchingLogged("delete remote image") {
                remoteDataSource.deleteImage(userId, id)
            }
        }
    }

    override suspend fun deleteImagesByIds(ids: List<String>) {
        localDb.deleteImagesByIds(ids)
        launchRemoteSync { userId ->
            ids.forEach { id ->
                runCatchingLogged("delete remote image") {
                    remoteDataSource.deleteImage(userId, id)
                }
            }
        }
    }

    override suspend fun clearAllImages() {
        localDb.clearAllImages()
        launchRemoteSync { userId ->
            runCatchingLogged("clear all remote images") {
                remoteDataSource.clearAllImages(userId)
            }
        }
    }

    // =================================================================================
    //                              USER OPERATIONS
    // =================================================================================

    override fun getCurrentUser(): Flow<User?> = localDb.getCurrentUser()

    override suspend fun saveUser(
        googleSubId: String,
        email: String,
        username: String,
        profilePicUrl: String?,
        createdAt: String
    ) {
        localDb.saveUser(googleSubId, email, username, profilePicUrl, createdAt)
    }

    override suspend fun clearUser() {
        localDb.clearUser()
    }

    // =================================================================================
    //                             UTILITY FUNCTIONS
    // =================================================================================

    /**
     * Launches a background coroutine to sync data if the user is logged in.
     * Silently returns if not authenticated.
     */
    private inline fun launchRemoteSync(crossinline action: suspend (userId: String) -> Unit) {
        val userId = _currentUserId.value ?: return
        if (!_isLoggedIn.value) return

        scope.launch { action(userId) }
    }

    /**
     * Executes a suspending block and catches exceptions, logging them with context.
     * @return The result of [block], or null if an exception occurred.
     */
    private suspend inline fun <T> runCatchingLogged(
        operation: String,
        block: () -> T
    ): T? = try {
        block()
    } catch (e: Exception) {
        logger.e(e) { "Failed to $operation" }
        null
    }

    private fun List<Chats>.toIdSet(): Set<String> = mapTo(HashSet()) { it.id }

    @JvmName("imagesToIdSet")
    private fun List<Generated_images>.toIdSet(): Set<String> = mapTo(HashSet()) { it.id }
}