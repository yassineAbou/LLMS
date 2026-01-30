@file:OptIn(ExperimentalTime::class, ExperimentalCoroutinesApi::class)

package org.yassineabou.llms.app.core.data.async

import co.touchlab.kermit.Logger
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
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
import kotlin.time.Duration
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
 *
 * ## Sync Mechanism
 * - Uses Kotlin Flow with flatMapLatest for reactive periodic sync
 * - Automatically starts/stops based on isLoggedIn StateFlow
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

    private val syncMutex = Mutex()
    private val loginMutex = Mutex()
    private val pendingSync = PendingSyncTracker()

    // Tracks IDs that have been successfully pushed to remote
    // If local data exists but isn't in this set, it needs to be pushed
    private val syncedChatIds = mutableSetOf<String>()
    private val syncedImageIds = mutableSetOf<String>()

    @Volatile
    private var isLoginInProgress = false

    // =================================================================================
    //                               INITIALIZATION
    // =================================================================================

    init {
        observeAuthStateChanges()
        startPeriodicSyncFlow()
    }

    // =================================================================================
    //                            AUTH STATE OBSERVATION
    // =================================================================================

    private fun observeAuthStateChanges() {
        scope.launch {
            localDb.getCurrentUser().collect { user ->
                if (loginMutex.withLock { isLoginInProgress }) {
                    logger.d { "Skipping auth state change - login in progress" }
                    return@collect
                }
                handleAuthStateChange(previouslyLoggedIn = _isLoggedIn.value, user = user)
            }
        }
    }

    private fun handleAuthStateChange(previouslyLoggedIn: Boolean, user: User?) {
        val isNowLoggedIn = user != null

        _currentUserId.value = user?.google_sub_id

        when {
            isNowLoggedIn && !previouslyLoggedIn -> {
                logger.i { "Auth state changed: logged in as ${user?.email}" }
                // Clear synced IDs to force full sync on login
                clearSyncedIds()
            }
            !isNowLoggedIn && previouslyLoggedIn -> {
                logger.i { "Auth state changed: logged out" }
                _syncState.value = SyncState.Idle
                pendingSync.clear()
                clearSyncedIds()
            }
        }

        _isLoggedIn.value = isNowLoggedIn
    }

    private fun clearSyncedIds() {
        syncedChatIds.clear()
        syncedImageIds.clear()
    }

    // =================================================================================
    //                         FLOW-BASED PERIODIC SYNC
    // =================================================================================

    private fun startPeriodicSyncFlow() {
        scope.launch {
            isLoggedIn
                .flatMapLatest { loggedIn ->
                    if (loggedIn) {
                        logger.i { "Starting periodic sync (interval: ${config.syncInterval})" }
                        tickerFlow(config.syncInterval)
                    } else {
                        emptyFlow()
                    }
                }
                .collect { performSync() }
        }
    }

    private fun tickerFlow(interval: Duration): Flow<Unit> = flow {
        emit(Unit)
        while (currentCoroutineContext().isActive) {
            delay(interval)
            emit(Unit)
        }
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

        loginMutex.withLock { isLoginInProgress = true }
        try {
            localDb.saveUser(userId, email, username, profilePicUrl, clock.now().toString())
            logger.d { "User saved to local DB: $userId" }

            runCatchingLogged("create remote user") {
                remoteDataSource.getOrCreateUser(userId, username, email, profilePicUrl)
            }

            // Clear synced IDs to force full sync
            clearSyncedIds()

            _currentUserId.value = userId
            _isLoggedIn.value = true

            logger.i { "Login complete for: $email" }
        } finally {
            loginMutex.withLock { isLoginInProgress = false }
        }
    }

    override suspend fun onUserLogout() {
        logger.i { "Processing logout" }
        localDb.clearUser()
        _isLoggedIn.value = false
        _currentUserId.value = null
        pendingSync.clear()
        clearSyncedIds()
    }

    override suspend fun deleteUserAccount() {
        val userId = _currentUserId.value
        logger.i { "Deleting user account: $userId" }

        userId?.let {
            runCatchingLogged("delete remote user") {
                remoteDataSource.deleteUser(it)
                logger.i { "Remote user deleted successfully" }
            }
        }

        localDb.clearAllChats()
        localDb.clearAllImages()
        localDb.clearUser()

        _isLoggedIn.value = false
        _currentUserId.value = null
        pendingSync.clear()
        clearSyncedIds()

        logger.i { "User account deleted completely" }
    }

    // =================================================================================
    //                               SYNC EXECUTION
    // =================================================================================

    private suspend fun performSync() {
        val userId = _currentUserId.value ?: run {
            logger.w { "performSync: No userId available, skipping" }
            return
        }

        syncMutex.withLock {
            logger.d { "Starting sync for user: $userId" }
            _syncState.value = SyncState.Syncing

            val result = runCatching {
                if (!ensureRemoteUserExists()) {
                    throw IllegalStateException("Failed to ensure remote user exists")
                }

                pullAllRemoteData(userId)
                pushUnsyncedLocalData(userId)
            }

            _syncState.value = result.fold(
                onSuccess = {
                    logger.i { "Sync completed successfully" }
                    SyncState.Success()
                },
                onFailure = { error ->
                    logger.e(error) { "Sync failed: ${error.message}" }
                    SyncState.Error(error.message ?: "Sync failed")
                }
            )
        }
    }

    private suspend fun ensureRemoteUserExists(): Boolean {
        val localUser = localDb.getCurrentUser().first() ?: run {
            logger.w { "No local user found" }
            return false
        }

        return runCatchingLogged("ensure remote user exists") {
            remoteDataSource.getOrCreateUser(
                id = localUser.google_sub_id,
                username = localUser.username,
                email = localUser.email,
                profilePicUrl = localUser.profile_pic_url
            )
            logger.d { "Remote user verified: ${localUser.email}" }
            true
        } ?: false
    }

    override suspend fun forceSyncNow() {
        performSync()
    }

    // =================================================================================
    //                        PULL OPERATIONS (Remote → Local)
    // =================================================================================

    private suspend fun pullAllRemoteData(userId: String) {
        runCatchingLogged("pull remote chats") {
            val existingIds = localDb.getAllChats().first().toIdSet()
            remoteDataSource.getChatsForUser(userId)
                .collect { remoteChat ->
                    if (remoteChat.id !in existingIds) {
                        logger.d { "Pulled new chat: ${remoteChat.id}" }
                        localDb.insertChat(remoteChat)
                    }
                    // Mark as synced since it exists on remote
                    syncedChatIds.add(remoteChat.id)
                }
        }

        runCatchingLogged("pull remote images") {
            val existingIds = localDb.getAllImages().first().toIdSet()
            remoteDataSource.getImagesForUser(userId)
                .collect { remoteImage ->
                    if (remoteImage.id !in existingIds) {
                        logger.d { "Pulled new image: ${remoteImage.id}" }
                        localDb.insertImage(remoteImage)
                    }
                    // Mark as synced since it exists on remote
                    syncedImageIds.add(remoteImage.id)
                }
        }
    }

    // =================================================================================
    //                        PUSH OPERATIONS (Local → Remote)
    // =================================================================================

    /**
     * Pushes local data that hasn't been synced to remote.
     * This includes:
     * 1. Items that were never pushed (not in syncedIds)
     * 2. Items that failed to push previously (in pendingSync)
     */
    private suspend fun pushUnsyncedLocalData(userId: String) {
        val localChats = localDb.getAllChats().first()
        val localImages = localDb.getAllImages().first()

        // Find chats that need to be pushed
        val unsyncedChats = localChats.filter { chat ->
            chat.id !in syncedChatIds || chat.id in pendingSync.chatIds
        }

        // Find images that need to be pushed
        val unsyncedImages = localImages.filter { image ->
            image.id !in syncedImageIds || image.id in pendingSync.imageIds
        }

        if (unsyncedChats.isEmpty() && unsyncedImages.isEmpty()) {
            logger.d { "All local data is synced" }
            return
        }

        logger.i { "Pushing unsynced data: ${unsyncedChats.size} chats, ${unsyncedImages.size} images" }

        var chatSuccess = 0
        var chatFailed = 0
        unsyncedChats.forEach { chat ->
            if (pushChat(userId, chat)) chatSuccess++ else chatFailed++
        }

        var imageSuccess = 0
        var imageFailed = 0
        unsyncedImages.forEach { image ->
            if (pushImage(userId, image)) imageSuccess++ else imageFailed++
        }

        logger.i { "Push complete: chats($chatSuccess ok, $chatFailed failed), images($imageSuccess ok, $imageFailed failed)" }
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

        // Push messages for this chat
        localDb.getMessagesByChatId(chat.id).first().forEach { message ->
            remoteDataSource.addMessage(userId, chat.id, message.message, message.is_user == 1L)
        }

        syncedChatIds.add(chat.id)
        pendingSync.markChatSynced(chat.id)
        logger.d { "Pushed chat: ${chat.id}" }
        true
    } catch (e: Exception) {
        logger.e(e) { "Failed to push chat ${chat.id}: ${e.message}" }
        pendingSync.markChatPending(chat.id)
        false
    }

    private suspend fun pushImage(userId: String, image: Generated_images): Boolean = try {
        remoteDataSource.createImage(
            id = image.id,
            userId = userId,
            prompt = image.prompt,
            imageData = image.image_data,
            imageModelName = image.image_model_name
        )

        syncedImageIds.add(image.id)
        pendingSync.markImageSynced(image.id)
        logger.d { "Pushed image: ${image.id}" }
        true
    } catch (e: Exception) {
        logger.e(e) { "Failed to push image ${image.id}: ${e.message}" }
        pendingSync.markImagePending(image.id)
        false
    }

    // =================================================================================
    //                              CHAT OPERATIONS
    // =================================================================================

    override fun getAllChats(): Flow<List<Chats>> = localDb.getAllChats()

    override suspend fun insertChatWithMessages(chat: Chats, messages: List<Chat_messages>) {
        localDb.insertChatWithMessages(chat, messages)
        launchRemoteSync { pushChat(it, chat) }
    }

    override suspend fun insertChat(chat: Chats) {
        localDb.insertChat(chat)
        launchRemoteSync { userId ->
            if (!pushChat(userId, chat)) {
                pendingSync.markChatPending(chat.id)
            }
        }
    }

    override suspend fun deleteChatById(id: String) {
        localDb.deleteChatById(id)
        syncedChatIds.remove(id)
        launchRemoteSync { userId ->
            runCatchingLogged("delete remote chat") {
                remoteDataSource.deleteChat(userId, id)
            }
        }
    }

    override suspend fun clearAllChats() {
        localDb.clearAllChats()
        syncedChatIds.clear()
        launchRemoteSync { userId ->
            runCatchingLogged("delete all remote chats") {
                remoteDataSource.getChatsForUser(userId).collect { chat ->
                    remoteDataSource.deleteChat(userId, chat.id)
                }
            }
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
        launchRemoteSync { pushImage(it, image) }
    }

    override suspend fun deleteImageById(id: String) {
        localDb.deleteImageById(id)
        syncedImageIds.remove(id)
        launchRemoteSync { userId ->
            runCatchingLogged("delete remote image") {
                remoteDataSource.deleteImage(userId, id)
            }
        }
    }

    override suspend fun deleteImagesByIds(ids: List<String>) {
        localDb.deleteImagesByIds(ids)
        ids.forEach { syncedImageIds.remove(it) }
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
        syncedImageIds.clear()
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

    private inline fun launchRemoteSync(crossinline action: suspend (userId: String) -> Unit) {
        val userId = _currentUserId.value ?: return
        if (!_isLoggedIn.value) return
        scope.launch { action(userId) }
    }

    private suspend inline fun <T> runCatchingLogged(
        operation: String,
        block: () -> T
    ): T? = try {
        block()
    } catch (e: Exception) {
        logger.e(e) { "Failed to $operation: ${e.message}" }
        null
    }

    private fun List<Chats>.toIdSet(): Set<String> = mapTo(HashSet()) { it.id }

    @JvmName("imagesToIdSet")
    private fun List<Generated_images>.toIdSet(): Set<String> = mapTo(HashSet()) { it.id }
}