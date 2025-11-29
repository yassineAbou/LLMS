@file:OptIn(ExperimentalTime::class)

package org.yassineabou.llms.app.core.data.async

import co.touchlab.kermit.Logger
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
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
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

/**
 * Production implementation of [AsyncManager].
 *
 * ## Strategy:
 * - **Not Logged In**: All operations go directly to local database
 * - **On Login**: Pushes all local data to remote server
 * - **Logged In**: Periodic two-way sync (pull remote → push local)
 *
 * ## Architecture:
 * - Local-first: All writes go to local DB immediately
 * - Background sync: Remote operations run in coroutines
 * - Retry mechanism: Failed operations are tracked and retried
 */
class AsyncManagerImpl(
    private val localDb: LlmsDatabaseRepository,
    private val remoteDataSource: RemoteDataSource,
    private val config: SyncConfig = SyncConfig(),
    private val scope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
) : AsyncManager {

    val logger = Logger.withTag("AsyncManager")

    // =================================================================================
    //                                  STATE
    // =================================================================================

    private val _isLoggedIn = MutableStateFlow(false)
    override val isLoggedIn: StateFlow<Boolean> = _isLoggedIn.asStateFlow()

    private val _currentUserId = MutableStateFlow<String?>(null)
    override val currentUserId: StateFlow<String?> = _currentUserId.asStateFlow()

    private val _syncState = MutableStateFlow<SyncState>(SyncState.Idle)
    override val syncState: StateFlow<SyncState> = _syncState.asStateFlow()

    // =================================================================================
    //                              SYNC INTERNALS
    // =================================================================================

    private var syncJob: Job? = null
    private val syncMutex = Mutex()

    private val pendingChatIds = MutableStateFlow<Set<String>>(emptySet())
    private val pendingImageIds = MutableStateFlow<Set<String>>(emptySet())

    // =================================================================================
    //                              INITIALIZATION
    // =================================================================================

    init {
        observeAuthStateChanges()
    }

    private fun observeAuthStateChanges() {
        scope.launch {
            localDb.getCurrentUser().collect { user ->
                handleAuthStateChange(previouslyLoggedIn = _isLoggedIn.value, user = user)
            }
        }
    }

    private suspend fun handleAuthStateChange(previouslyLoggedIn: Boolean, user: User?) {
        val isNowLoggedIn = user != null

        _isLoggedIn.value = isNowLoggedIn
        _currentUserId.value = user?.google_sub_id

        when {
            isNowLoggedIn && !previouslyLoggedIn -> {
                logger.i { "Auth state changed: User logged in (${user.email})" }
                performFirstLoginSync()
            }
            !isNowLoggedIn && previouslyLoggedIn -> {
                logger.i { "Auth state changed: User logged out" }
                stopSync()
            }
        }
    }

    // =================================================================================
    //                              AUTH OPERATIONS
    // =================================================================================

    override suspend fun onUserLogin(
        userId: String,
        email: String,
        username: String,
        profilePicUrl: String?
    ) {
        logger.i { "Processing login for: $email" }

        saveUserToLocalDb(userId, email, username, profilePicUrl)
        createOrUpdateRemoteUser(userId, email, username, profilePicUrl)
    }

    override suspend fun onUserLogout() {
        logger.i { "Processing logout" }

        stopSync()
        localDb.clearUser()
        resetAuthState()
    }

    private suspend fun saveUserToLocalDb(
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
            createdAt = Clock.System.now().toString()
        )
    }

    private suspend fun createOrUpdateRemoteUser(
        userId: String,
        email: String,
        username: String,
        profilePicUrl: String?
    ) {
        runCatchingWithLog("create remote user") {
            remoteDataSource.getOrCreateUser(
                id = userId,
                username = username,
                email = email,
                profilePicUrl = profilePicUrl
            )
        }
    }

    private fun resetAuthState() {
        _isLoggedIn.value = false
        _currentUserId.value = null
        pendingChatIds.value = emptySet()
        pendingImageIds.value = emptySet()
    }

    // =================================================================================
    //                              SYNC LIFECYCLE
    // =================================================================================

    override fun startSync() {
        if (syncJob?.isActive == true) {
            logger.d { "Sync already running, skipping start" }
            return
        }

        logger.i { "Starting periodic sync (interval: ${config.syncInterval})" }

        syncJob = scope.launch {
            while (isActive) {
                delay(config.syncInterval)
                performSyncIfLoggedIn()
            }
        }
    }

    override fun stopSync() {
        logger.i { "Stopping periodic sync" }

        syncJob?.cancel()
        syncJob = null
        _syncState.value = SyncState.Idle

    }

    override suspend fun forceSyncNow() {
        performSyncIfLoggedIn()
    }

    private suspend fun performSyncIfLoggedIn() {
        val userId = _currentUserId.value ?: return
        performTwoWaySync(userId)
    }

    private suspend fun performFirstLoginSync() {
        val userId = _currentUserId.value ?: return

        logger.i { "Performing first login sync..." }
        updateSyncState(SyncState.Syncing)

        val success = pushAllLocalDataToRemote(userId)

        if (success) {
            updateSyncState(SyncState.Success())
            logger.i { "First login sync completed successfully" }
        } else {
            updateSyncState(SyncState.Error("Initial sync partially failed"))
            logger.w { "First login sync completed with errors" }
        }

        startSync()
    }

    // =================================================================================
    //                              TWO-WAY SYNC
    // =================================================================================

    private suspend fun performTwoWaySync(userId: String) {
        syncMutex.withLock {
            logger.d { "Starting sync cycle..." }
            updateSyncState(SyncState.Syncing)

            try {
                pullRemoteChats(userId)
                pullRemoteImages(userId)
                pushPendingChats(userId)
                pushPendingImages(userId)

                updateSyncState(SyncState.Success())
                logger.d { "Sync cycle completed successfully" }
            }  catch (e: Exception) {
                updateSyncState(SyncState.Error(e.message ?: "Sync failed"))
                logger.e(e) { "Sync cycle failed" }
            }

        }
    }

    // =================================================================================
    //                              PULL OPERATIONS (Remote → Local)
    // =================================================================================

    private suspend fun pullRemoteChats(userId: String) {
        runCatchingWithLog("pull remote chats") {
            val localChatIds = getLocalChatIds()
            remoteDataSource.getChatsForUser(userId).collect { remoteChat ->
                if (remoteChat.id !in localChatIds) {
                    logger.d { "Pulled new chat: ${remoteChat.id}" }
                    localDb.insertChat(remoteChat)
                }
            }

        }
    }

    private suspend fun pullRemoteImages(userId: String) {
        runCatchingWithLog("pull remote images") {
            val localImageIds = getLocalImageIds()

            remoteDataSource.getImagesForUser(userId).collect { remoteImage ->
                if (remoteImage.id !in localImageIds) {
                    logger.d { "Pulled new image: ${remoteImage.id}" }
                    localDb.insertImage(remoteImage)
                }
            }
        }
    }


    // =================================================================================
    //                              PUSH OPERATIONS (Local → Remote)
    // =================================================================================

    private suspend fun pushAllLocalDataToRemote(userId: String): Boolean {
        val chatSuccess = pushAllLocalChats(userId)
        val imageSuccess = pushAllLocalImages(userId)
        return chatSuccess && imageSuccess
    }

    private suspend fun pushAllLocalChats(userId: String): Boolean {
        val localChats = localDb.getAllChats().first()
        logger.i { "Pushing ${localChats.size} local chats to remote" }

        var allSuccessful = true
        for (chat in localChats) {
            val success = pushChatToRemote(userId, chat)
            if (!success) allSuccessful = false
        }
        return allSuccessful
    }

    private suspend fun pushAllLocalImages(userId: String): Boolean {
        val localImages = localDb.getAllImages().first()
        logger.i { "Pushing ${localImages.size} local images to remote" }

        var allSuccessful = true

        for (image in localImages) {
            val success = pushImageToRemote(userId, image)
            if (!success) allSuccessful = false
        }
        return allSuccessful
    }

    private suspend fun pushPendingChats(userId: String) {
        val pendingIds = pendingChatIds.value
        if (pendingIds.isEmpty()) return

        logger.d { "Pushing ${pendingIds.size} pending chats" }

        val localChats = localDb.getAllChats().first()
        val chatsToPush = localChats.filter { it.id in pendingIds }

        for (chat in chatsToPush) {
            pushChatToRemote(userId, chat)
        }
    }

    private suspend fun pushPendingImages(userId: String) {
        val pendingIds = pendingImageIds.value
        if (pendingIds.isEmpty()) return

        logger.d { "Pushing ${pendingIds.size} pending images" }

        val localImages = localDb.getAllImages().first()
        val imagesToPush = localImages.filter { it.id in pendingIds }

        for (image in imagesToPush) {
            pushImageToRemote(userId, image)
        }
    }

    private suspend fun pushChatToRemote(userId: String, chat: Chats): Boolean {
        return try {
            remoteDataSource.createChat(
                id = chat.id,
                userId = userId,
                title = chat.title,
                description = chat.description,
                textModelName = chat.text_model_name
            )

            pushMessagesForChat(userId, chat.id)
            markChatAsSynced(chat.id)

            logger.d { "Pushed chat: ${chat.id}" }
            true
        } catch (e: Exception) {
            logger.e(e) { "Failed to push chat: ${chat.id}" }
            markChatAsPending(chat.id)
            false
        }
    }

    private suspend fun pushMessagesForChat(userId: String, chatId: String) {
        val messages = localDb.getMessagesByChatId(chatId).first()

        for (message in messages) {
            remoteDataSource.addMessage(
                userId = userId,
                chatId = chatId,
                message = message.message,
                isUser = message.is_user == 1L
            )
        }
    }

    private suspend fun pushImageToRemote(userId: String, image: Generated_images): Boolean {
        return try {
            remoteDataSource.createImage(
                id = image.id,
                userId = userId,
                prompt = image.prompt,
                imageData = image.image_data,
                imageModelName = image.image_model_name
            )

            markImageAsSynced(image.id)
            logger.d { "Pushed image: ${image.id}" }
            true
        } catch (e: Exception) {
            logger.e(e) { "Failed to push image: ${image.id}" }
            markImageAsPending(image.id)
            false
        }
    }

    // =================================================================================
    //                              CHAT OPERATIONS
    // =================================================================================

    override fun getAllChats(): Flow<List<Chats>> = localDb.getAllChats()

    override suspend fun insertChatWithMessages(chat: Chats, messages: List<Chat_messages>) {
        localDb.insertChatWithMessages(chat, messages)
        syncChatToRemoteInBackground(chat)
    }

    override suspend fun insertChat(chat: Chats) {
        localDb.insertChat(chat)
        syncChatUpdateToRemoteInBackground(chat)
    }

    override suspend fun deleteChatById(id: String) {
        localDb.deleteChatById(id)
        deleteChatFromRemoteInBackground(id)
    }

    override suspend fun clearAllChats() {
        localDb.clearAllChats()
        deleteAllChatsFromRemoteInBackground()
    }

    private fun syncChatToRemoteInBackground(chat: Chats) {
        launchIfLoggedIn { userId ->
            pushChatToRemote(userId, chat)
        }
    }

    private fun syncChatUpdateToRemoteInBackground(chat: Chats) {
        launchIfLoggedIn { userId ->
            runCatchingWithLog("sync chat update") {
                remoteDataSource.createChat(
                    id = chat.id,
                    userId = userId,
                    title = chat.title,
                    description = chat.description,
                    textModelName = chat.text_model_name
                )
            } ?: markChatAsPending(chat.id)
        }
    }

    private fun deleteChatFromRemoteInBackground(chatId: String) {
        launchIfLoggedIn { userId ->
            runCatchingWithLog("delete remote chat") {
                remoteDataSource.deleteChat(userId, chatId)
            }
        }
    }

    private fun deleteAllChatsFromRemoteInBackground() {
        launchIfLoggedIn { userId ->
            runCatchingWithLog("delete all remote chats") {
                remoteDataSource.getChatsForUser(userId).collect { chat ->
                    remoteDataSource.deleteChat(userId, chat.id)
                }
            }
        }
    }

    // =================================================================================
    //                              MESSAGE OPERATIONS
    // =================================================================================

    override fun getMessagesByChatId(chatId: String): Flow<List<Chat_messages>> =
        localDb.getMessagesByChatId(chatId)

    // =================================================================================
    //                              IMAGE OPERATIONS
    // =================================================================================

    override fun getAllImages(): Flow<List<Generated_images>> = localDb.getAllImages()

    override suspend fun insertImage(image: Generated_images) {
        localDb.insertImage(image)
        syncImageToRemoteInBackground(image)
    }

    override suspend fun deleteImageById(id: String) {
        localDb.deleteImageById(id)
        deleteImageFromRemoteInBackground(id)
    }

    override suspend fun deleteImagesByIds(ids: List<String>) {
        localDb.deleteImagesByIds(ids)
        deleteImagesFromRemoteInBackground(ids)
    }

    override suspend fun clearAllImages() {
        localDb.clearAllImages()
        clearAllImagesFromRemoteInBackground()
    }

    private fun syncImageToRemoteInBackground(image: Generated_images) {
        launchIfLoggedIn { userId ->
            pushImageToRemote(userId, image)
        }
    }

    private fun deleteImageFromRemoteInBackground(imageId: String) {
        launchIfLoggedIn { userId ->
            runCatchingWithLog("delete remote image") {
                remoteDataSource.deleteImage(userId, imageId)
            }
        }
    }

    private fun deleteImagesFromRemoteInBackground(imageIds: List<String>) {
        launchIfLoggedIn { userId ->
            for (imageId in imageIds) {
                runCatchingWithLog("delete remote image") {
                    remoteDataSource.deleteImage(userId, imageId)
                }
            }
        }
    }

    private fun clearAllImagesFromRemoteInBackground() {
        launchIfLoggedIn { userId ->
            runCatchingWithLog("clear all remote images") {
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
    //                              PENDING TRACKING
    // =================================================================================

    private fun markChatAsPending(chatId: String) {
        pendingChatIds.update { it + chatId }
    }

    private fun markChatAsSynced(chatId: String) {
        pendingChatIds.update { it - chatId }
    }

    private fun markImageAsPending(imageId: String) {
        pendingImageIds.update { it + imageId }
    }

    private fun markImageAsSynced(imageId: String) {
        pendingImageIds.update { it - imageId }
    }

    // =================================================================================
    //                              UTILITIES
    // =================================================================================

    private suspend fun getLocalChatIds(): Set<String> =
        localDb.getAllChats().first().map { it.id }.toSet()

    private suspend fun getLocalImageIds(): Set<String> =
        localDb.getAllImages().first().map { it.id }.toSet()

    private fun updateSyncState(state: SyncState) {
        _syncState.value = state
    }

    /**
     * Launches a coroutine only if user is logged in.
     * Silently returns if not logged in.
     */
    private fun launchIfLoggedIn(action: suspend (userId: String) -> Unit) {
        val userId = _currentUserId.value ?: return
        if (!_isLoggedIn.value) return

        scope.launch { action(userId) }
    }

    /**
     * Executes a block and catches any exception, logging it with context.
     * Returns null if an exception occurred, otherwise returns the result.
     */
    private suspend fun <T> runCatchingWithLog(
        operationName: String,
        block: suspend () -> T
    ): T? {
        return try {
            block()
        } catch (e: Exception) {
            logger.e(e) { "Failed to $operationName" }
            null
        }
    }
}