package org.yassineabou.llms.app.core.data.async

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import org.yassineabou.llms.Chat_messages
import org.yassineabou.llms.Chats
import org.yassineabou.llms.Generated_images
import org.yassineabou.llms.User

/**
 * Central data manager that abstracts local and remote data sources.
 *
 * ## Responsibilities:
 * - Provides unified data access for ViewModels
 * - Manages local-first data storage
 * - Handles automatic synchronization when user is logged in
 * - Tracks and retries failed remote operations
 *
 * ## Usage Strategy:
 * - **Not Logged In**: All operations use local database only
 * - **On Login**: Pushes existing local data to remote server
 * - **Logged In**: Periodic two-way sync (pull remote, push local)
 *
 * @see AsyncManagerImpl for implementation details
 */
interface AsyncManager {

    // =================================================================================
    //                              AUTHENTICATION STATE
    // =================================================================================

    val isLoggedIn: StateFlow<Boolean>

    val currentUserId: StateFlow<String?>

    val syncState: StateFlow<SyncState>

    // =================================================================================
    //                              AUTHENTICATION OPERATIONS
    // =================================================================================

    /**
     * Processes user login.
     * - Saves user to local database
     * - Creates/updates user on remote server
     * - Triggers initial sync to push local data
     * - Starts periodic background sync
     */
    suspend fun onUserLogin(
        userId: String,
        email: String,
        username: String,
        profilePicUrl: String?
    )

    /**
     * Processes user logout.
     * - Stops background sync
     * - Clears user from local database
     * - Resets pending changes tracker
     */
    suspend fun onUserLogout()

    suspend fun deleteUserAccount()

    // =================================================================================
    //                              CHAT OPERATIONS
    // =================================================================================

    fun getAllChats(): Flow<List<Chats>>

    suspend fun insertChatWithMessages(chat: Chats, messages: List<Chat_messages>)

    suspend fun insertChat(chat: Chats)

    suspend fun deleteChatById(id: String)

    suspend fun clearAllChats()

    // =================================================================================
    //                              MESSAGE OPERATIONS
    // =================================================================================

    fun getMessagesByChatId(chatId: String): Flow<List<Chat_messages>>

    // =================================================================================
    //                              IMAGE OPERATIONS
    // =================================================================================

    fun getAllImages(): Flow<List<Generated_images>>

    suspend fun insertImage(image: Generated_images)

    suspend fun deleteImageById(id: String)

    suspend fun deleteImagesByIds(ids: List<String>)

    suspend fun clearAllImages()

    // =================================================================================
    //                              USER OPERATIONS
    // =================================================================================

    fun getCurrentUser(): Flow<User?>

    suspend fun saveUser(
        googleSubId: String,
        email: String,
        username: String,
        profilePicUrl: String?,
        createdAt: String
    )

    suspend fun clearUser()

    // =================================================================================
    //                              SYNC CONTROLS
    // =================================================================================

    suspend fun forceSyncNow()
}