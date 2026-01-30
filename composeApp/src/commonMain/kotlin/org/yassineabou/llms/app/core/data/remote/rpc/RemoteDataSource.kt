package org.yassineabou.llms.app.core.data.remote.rpc

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.rpc.withService
import org.yassineabou.llms.Chat_messages
import org.yassineabou.llms.Chats
import org.yassineabou.llms.Generated_images
import org.yassineabou.llms.api.ChatService
import org.yassineabou.llms.api.ImageService
import org.yassineabou.llms.api.MessageService
import org.yassineabou.llms.api.UserDto
import org.yassineabou.llms.api.UserService
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

    suspend fun getOrCreateUser(
        id: String,
        username: String,
        email: String,
        profilePicUrl: String?
    ): UserDto = rpcClientProvider.execute {
        withService<UserService>().findByIdOrCreateUser(id, username, email, profilePicUrl)
    }

    suspend fun deleteUser(userId: String): Int = rpcClientProvider.execute {
        withService<UserService>().deleteUser(userId)
    }

    // === CHATS ===

    fun getChatsForUser(userId: String): Flow<Chats> = rpcClientProvider.executeFlow {
        withService<ChatService>().getChatsForUser(userId).map { it.toLocalChat() }
    }

    suspend fun createChat(
        id: String,
        userId: String,
        title: String,
        description: String?,
        textModelName: String,
        isBookmarked: Boolean
    ): Chats = rpcClientProvider.execute {
        withService<ChatService>().createChat(id, userId, title, description, textModelName, isBookmarked).toLocalChat()
    }

    suspend fun deleteChat(userId: String, chatId: String): Int = rpcClientProvider.execute {
        withService<ChatService>().deleteChat(userId, chatId)
    }

    // === MESSAGES ===

    suspend fun addMessage(
        userId: String,
        chatId: String,
        message: String,
        isUser: Boolean
    ): Chat_messages = rpcClientProvider.execute {
        withService<MessageService>().addMessageToChat(userId, chatId, message, isUser).toLocalChatMessage()
    }

    suspend fun clearChatMessages(userId: String, chatId: String): Int = rpcClientProvider.execute {
        withService<MessageService>().clearChatMessages(userId, chatId)
    }

    // === IMAGES ===

    fun getImagesForUser(userId: String): Flow<Generated_images> = rpcClientProvider.executeFlow {
        withService<ImageService>().getImagesForUser(userId).map { it.toLocalImage() }
    }

    suspend fun createImage(
        id: String,
        userId: String,
        prompt: String,
        imageData: ByteArray,
        imageModelName: String
    ): Generated_images = rpcClientProvider.execute {
        withService<ImageService>().createImage(id, userId, prompt, imageData, imageModelName).toLocalImage()
    }

    suspend fun deleteImage(userId: String, imageId: String): Int = rpcClientProvider.execute {
        withService<ImageService>().deleteImage(userId, imageId)
    }

    suspend fun clearAllImages(userId: String): Int = rpcClientProvider.execute {
        withService<ImageService>().clearAllImagesForUser(userId)
    }
}