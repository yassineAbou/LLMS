package org.yassineabou.llms.app.core.data.local

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.cash.sqldelight.coroutines.mapToOneOrNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import org.yassineabou.llms.Chat_messages
import org.yassineabou.llms.Chats
import org.yassineabou.llms.Generated_images
import org.yassineabou.llms.LlmsDatabase
import org.yassineabou.llms.User

interface LlmsDatabaseInterface {
    // Chats + Messages
    suspend fun insertChatWithMessages(chat: Chats, messages: List<Chat_messages>)

    // Chats
    fun getAllChats(): Flow<List<Chats>>
    suspend fun insertChat(chat: Chats)
    suspend fun deleteChatById(id: String)
    suspend fun clearAllChats()

    // Messages
    fun getMessagesByChatId(chatId: String): Flow<List<Chat_messages>>

    suspend fun deleteMessagesByChatId(chatId: String)


    // Images
    fun getAllImages(): Flow<List<Generated_images>>
    suspend fun insertImage(image: Generated_images)
    suspend fun deleteImageById(id: String)
    suspend fun clearAllImages()

    suspend fun deleteImagesByIds(ids: List<String>)


    //User
    fun getCurrentUser(): Flow<User?>

    // NEW INTERFACE
    suspend fun saveUser(
        googleSubId: String,
        email: String,
        username: String,
        profilePicUrl: String?,
        createdAt: String
    )

    suspend fun clearUser()

}

class LlmsDatabaseRepository(
    private val llmsDatabase: LlmsDatabase
) : LlmsDatabaseInterface {

    private val queries get() = llmsDatabase.llmsDatabaseQueries

    override fun getAllChats(): Flow<List<Chats>> =
        queries.selectAllChats()
            .asFlow()
            .mapToList(Dispatchers.Default)


    override suspend fun insertChatWithMessages(
        chat: Chats,
        messages: List<Chat_messages>
    ) = withContext(Dispatchers.Default) {
        queries.transaction {
            // 1. Insert/Replace chat
            queries.insertChat(
                id = chat.id,
                title = chat.title,
                description = chat.description,
                text_model_name = chat.text_model_name,
                is_bookmarked = chat.is_bookmarked,
                created_at = chat.created_at
            )

            // 2. DELETE existing messages for this chat first
            queries.deleteMessagesByChatId(chat.id)

            // 3. Insert all messages fresh
            messages.forEach { message ->
                queries.insertMessage(
                    chat_id = chat.id,
                    message = message.message,
                    is_user = message.is_user,
                    timestamp = message.timestamp
                )
            }
        }
    }

    override suspend fun insertChat(chat: Chats) = withContext(Dispatchers.Default) {
        queries.insertChat(
            id = chat.id,
            title = chat.title,
            description = chat.description,
            text_model_name = chat.text_model_name,
            is_bookmarked = chat.is_bookmarked,
            created_at = chat.created_at
        )
        Unit
    }

    override suspend fun deleteChatById(id: String) = withContext(Dispatchers.Default) {
        queries.deleteChatById(id)
        Unit
    }

    override suspend fun clearAllChats() = withContext(Dispatchers.Default) {
        queries.clearAllChats()
        Unit
    }

    override fun getMessagesByChatId(chatId: String): Flow<List<Chat_messages>> =
        queries.selectMessagesByChatId(chatId)
            .asFlow()
            .mapToList(Dispatchers.Default)

    override suspend fun deleteMessagesByChatId(chatId: String) = withContext(Dispatchers.Default) {
        queries.deleteMessagesByChatId(chatId)
        Unit
    }


    override fun getAllImages(): Flow<List<Generated_images>> =
        queries.selectAllImages()
            .asFlow()
            .mapToList(Dispatchers.Default)

    override suspend fun insertImage(image: Generated_images) = withContext(Dispatchers.Default) {
        queries.insertImage(
            id = image.id,
            prompt = image.prompt,
            image_data = image.image_data,
            image_model_name = image.image_model_name,
            generated_at = image.generated_at
        )
        Unit
    }

    override suspend fun deleteImageById(id: String) = withContext(Dispatchers.Default) {
        queries.deleteImageById(id)
        Unit
    }

    override suspend fun clearAllImages() = withContext(Dispatchers.Default) {
        queries.clearAllImages()
        Unit
    }

    override suspend fun deleteImagesByIds(ids: List<String>) = withContext(Dispatchers.Default) {
        queries.transaction {
            ids.forEach { id ->
                queries.deleteImageById(id)
            }
        }
    }

    override fun getCurrentUser(): Flow<User?> =
        queries.getUser()
            .asFlow()
            .mapToOneOrNull(Dispatchers.Default)


    override suspend fun saveUser(
        googleSubId: String,
        email: String,
        username: String,
        profilePicUrl: String?,
        createdAt: String
    ) = withContext(Dispatchers.Default) {
        queries.insertOrUpdateUser(
            google_sub_id = googleSubId,
            email = email,
            username = username,
            profile_pic_url = profilePicUrl,
            created_at = createdAt
        )
        Unit
    }

    override suspend fun clearUser() = withContext(Dispatchers.Default) {
        queries.deleteUser()
        Unit
    }

}
