package org.yassineabou.llms.app.core.data.remote.rpc

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.yassineabou.llms.Chat_messages
import org.yassineabou.llms.Chats
import org.yassineabou.llms.Generated_images
import org.yassineabou.llms.api.UserDto
import org.yassineabou.llms.app.core.util.DtoMappers.toLocalChat
import org.yassineabou.llms.app.core.util.DtoMappers.toLocalChatMessage
import org.yassineabou.llms.app.core.util.DtoMappers.toLocalImage


/**
 * Remote data source that communicates with the backend via RPC.
 * All methods in this class make network calls to the remote server.
 */
class RemoteDataSource(
    private val rpcClientProvider: RpcClientProvider
) {

    // === USER ===

    /**
     * Fetches a user by ID or creates one if it doesn't exist.
     */
    suspend fun getOrCreateUser(
        id: String,
        username: String,
        email: String,
        profilePicUrl: String?
    ): UserDto {
        return rpcClientProvider.userService.findByIdOrCreateUser(
            id = id,
            username = username,
            email = email,
            profilePicUrl = profilePicUrl
        )
    }

    /**
     * Deletes a user from the remote database.
     */
    suspend fun deleteUser(userId: String): Int {
        return rpcClientProvider.userService.deleteUser(userId)
    }

    // === CHATS ===

    /**
     * Retrieves all chats for a specific user as a Flow.
     */
    fun getChatsForUser(userId: String): Flow<Chats> {
        return rpcClientProvider.chatService.getChatsForUser(userId)
            .map { it.toLocalChat() }
    }

    /**
     * Creates a new chat session for a user.
     */
    suspend fun createChat(
        id: String,
        userId: String,
        title: String,
        description: String?,
        textModelName: String,
        isBookmarked: Boolean
    ): Chats {
        val dto = rpcClientProvider.chatService.createChat(
            id = id,
            userId = userId,
            title = title,
            description = description,
            textModelName = textModelName,
            isBookmarked = isBookmarked
        )
        return dto.toLocalChat()
    }

    /**
     * Deletes a specific chat and all its messages.
     */
    suspend fun deleteChat(userId: String, chatId: String): Int {
        return rpcClientProvider.chatService.deleteChat(userId, chatId)
    }

    // === MESSAGES ===

    /**
     * Adds a new message to a chat.
     */
    suspend fun addMessage(
        userId: String,
        chatId: String,
        message: String,
        isUser: Boolean
    ): Chat_messages {
        val dto = rpcClientProvider.messageService.addMessageToChat(
            userId = userId,
            chatId = chatId,
            message = message,
            isUser = isUser
        )
        return dto.toLocalChatMessage()
    }

    /**
     * Clears all messages from a specific chat.
     */
    suspend fun clearChatMessages(userId: String, chatId: String): Int {
        return rpcClientProvider.messageService.clearChatMessages(userId, chatId)
    }

    // === IMAGES ===

    /**
     * Retrieves all generated images for a specific user as a Flow.
     */
    fun getImagesForUser(userId: String): Flow<Generated_images> {
        return rpcClientProvider.imageService.getImagesForUser(userId)
            .map { it.toLocalImage() }
    }

    /**
     * Creates and stores a new generated image.
     */
    suspend fun createImage(
        id: String,
        userId: String,
        prompt: String,
        imageData: ByteArray,
        imageModelName: String
    ): Generated_images {
        val dto = rpcClientProvider.imageService.createImage(
            id = id,
            userId = userId,
            prompt = prompt,
            imageData = imageData,
            imageModelName = imageModelName
        )
        return dto.toLocalImage()
    }

    /**
     * Deletes a specific image by ID.
     */
    suspend fun deleteImage(userId: String, imageId: String): Int {
        return rpcClientProvider.imageService.deleteImage(userId, imageId)
    }

    /**
     * Deletes all images for a specific user.
     */
    suspend fun clearAllImages(userId: String): Int {
        return rpcClientProvider.imageService.clearAllImagesForUser(userId)
    }
}