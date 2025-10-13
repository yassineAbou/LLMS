package org.yassineabou.llms.api

import kotlinx.coroutines.flow.Flow
import kotlinx.rpc.annotations.Rpc
import kotlinx.serialization.Serializable


@Serializable
data class ChatMessageDto(
    val id: Int,
    val chatId: String,
    val message: String,
    val isUser: Boolean,
    val timestamp: String // Converted from Instant for serialization
)


@Rpc
interface MessageService {

    /**
     * Adds a new message to a specific chat and returns the created message.
     */
    suspend fun addMessageToChat(
        userId: String,
        chatId: String,
        message: String,
        isUser: Boolean
    ): ChatMessageDto

    /**
     * Retrieves a list of all chat sessions for a given user.
     */
    fun getChatsForUser(userId: String): Flow<ChatDto>

    /**
     * Deletes all messages within a specific chat.
     * Returns the number of messages deleted.
     */
    suspend fun clearChatMessages(userId: String, chatId: String): Int
}

