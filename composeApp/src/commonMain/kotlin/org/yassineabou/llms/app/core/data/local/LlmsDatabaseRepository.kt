package org.yassineabou.llms.app.core.data.local

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.yassineabou.llms.db.*

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

    // User
    fun getCurrentUser(): Flow<User?>
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

    // =====================================================================
    //                              CHATS
    // =====================================================================

    override fun getAllChats(): Flow<List<Chats>> =
        llmsDatabase.chats.selectAllChats
            .asFlow()
            .map { results -> results.toChats() }

    override suspend fun insertChat(chat: Chats) {
        llmsDatabase.chats.insertChat(
            ChatsQuery.InsertChat.Params(
                id = chat.id,
                title = chat.title,
                description = chat.description,
                textModelName = chat.text_model_name,
                isBookmarked = chat.is_bookmarked == 1L,
                createdAt = chat.created_at
            )
        )
    }

    override suspend fun deleteChatById(id: String) {
        llmsDatabase.chats.deleteChatById(
            ChatsQuery.DeleteChatById.Params(id = id)
        )
    }

    override suspend fun clearAllChats() {
        llmsDatabase.chats.clearAllChats()
    }

    // =====================================================================
    //                         CHATS + MESSAGES
    // =====================================================================

    override suspend fun insertChatWithMessages(
        chat: Chats,
        messages: List<Chat_messages>
    ) {
        llmsDatabase.chats.insertChat(
            ChatsQuery.InsertChat.Params(
                id = chat.id,
                title = chat.title,
                description = chat.description,
                textModelName = chat.text_model_name,
                isBookmarked = chat.is_bookmarked == 1L,
                createdAt = chat.created_at
            )
        )

        llmsDatabase.chatMessages.deleteMessagesByChatId(
            ChatMessagesQuery.DeleteMessagesByChatId.Params(chatId = chat.id)
        )

        messages.forEach { message ->
            llmsDatabase.chatMessages.insertMessage(
                ChatMessagesQuery.InsertMessage.Params(
                    chatId = chat.id,
                    message = message.message,
                    isUser = message.is_user == 1L,
                    timestamp = message.timestamp
                )
            )
        }
    }

    // =====================================================================
    //                            MESSAGES
    // =====================================================================

    override fun getMessagesByChatId(chatId: String): Flow<List<Chat_messages>> =
        llmsDatabase.chatMessages.selectMessagesByChatId(
            ChatMessagesQuery.SelectMessagesByChatId.Params(chatId = chatId)
        ).asFlow()
            .map { results -> results.toChatMessages() }

    override suspend fun deleteMessagesByChatId(chatId: String) {
        llmsDatabase.chatMessages.deleteMessagesByChatId(
            ChatMessagesQuery.DeleteMessagesByChatId.Params(chatId = chatId)
        )
    }

    // =====================================================================
    //                             IMAGES
    // =====================================================================

    override fun getAllImages(): Flow<List<Generated_images>> =
        llmsDatabase.generatedImages.selectAllImages
            .asFlow()
            .map { results -> results.toGeneratedImages() }

    override suspend fun insertImage(image: Generated_images) {
        llmsDatabase.generatedImages.insertImage(
            GeneratedImagesQuery.InsertImage.Params(
                id = image.id,
                prompt = image.prompt,
                imageData = image.image_data,
                imageModelName = image.image_model_name,
                generatedAt = image.generated_at
            )
        )
    }

    override suspend fun deleteImageById(id: String) {
        llmsDatabase.generatedImages.deleteImageById(
            GeneratedImagesQuery.DeleteImageById.Params(id = id)
        )
    }

    override suspend fun clearAllImages() {
        llmsDatabase.generatedImages.clearAllImages()
    }

    override suspend fun deleteImagesByIds(ids: List<String>) {
        llmsDatabase.generatedImages.deleteImagesByIds(
            GeneratedImagesQuery.DeleteImagesByIds.Params(ids = ids)
        )
    }

    // =====================================================================
    //                              USER
    // =====================================================================

    override fun getCurrentUser(): Flow<User?> =
        llmsDatabase.user.getUser
            .asFlow()
            .map { results -> results.firstOrNull()?.toUser() }

    override suspend fun saveUser(
        googleSubId: String,
        email: String,
        username: String,
        profilePicUrl: String?,
        createdAt: String
    ) {
        llmsDatabase.user.insertOrUpdateUser(
            UserQuery.InsertOrUpdateUser.Params(
                googleSubId = googleSubId,
                email = email,
                username = username,
                profilePicUrl = profilePicUrl,
                createdAt = createdAt
            )
        )
    }

    override suspend fun clearUser() {
        llmsDatabase.user.deleteUser()
    }
}
