package org.yassineabou.llms.api

import kotlinx.coroutines.flow.Flow
import kotlinx.rpc.annotations.Rpc
import kotlinx.serialization.Serializable

@Serializable
data class ChatDto(
    val id: String,
    val userId: String,
    val title: String,
    val description: String?,
    val textModelName: String,
    val isBookmarked: Boolean,
    val createdAt: String
)

/*
/**
 * The RPC interface for managing chat sessions (creating, listing, deleting).
 */
@Rpc
interface ChatService {

    /**
     * Creates a new chat session for a user.
     * The server will generate the ID and creation timestamp.
     * @return The fully created ChatDto.
     */
    suspend fun createChat(
        id: String,
        userId: String,
        title: String,
        description: String?,
        textModelName: String
    ): ChatDto

    /**
     * Retrieves a list of all chat sessions for a given user.
     */
    suspend fun getChatsForUser(userId: String): Flow<ChatDto>

    /**
     * Deletes a chat session and all its associated messages (due to DB cascade).
     * @return The number of chats deleted (should be 1 or 0).
     */
    suspend fun deleteChat(userId: String, chatId: String): Int
}

 */