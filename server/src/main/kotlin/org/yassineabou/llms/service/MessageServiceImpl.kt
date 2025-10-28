@file:OptIn(ExperimentalTime::class)

package org.yassineabou.llms.service

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.yassineabou.llms.api.ChatDto
import org.yassineabou.llms.api.ChatMessageDto
import org.yassineabou.llms.api.MessageService
import org.yassineabou.llms.database.tables.ChatMessageEntity
import org.yassineabou.llms.repository.MessageRepository
import kotlin.time.Clock
import kotlin.time.ExperimentalTime


class MessageServiceImpl(private val messageRepository: MessageRepository) : MessageService {

    override suspend fun addMessageToChat(
        userId: String,
        chatId: String,
        message: String,
        isUser: Boolean
    ): ChatMessageDto {
        // 1. Create a server-side Entity from the incoming data.
        //    The ID is irrelevant here as the DB will generate it.
        //    The server is the authority on the timestamp.
        val messageEntity = ChatMessageEntity(
            id = 0, // Will be replaced by DB auto-increment
            chatId = chatId,
            message = message,
            isUser = isUser,
            timestamp = Clock.System.now()
        )

        // 2. Call the repository to save the entity to the database.
        val createdEntity = messageRepository.addMessageToChat(userId, messageEntity)

        // 3. Convert the resulting entity into a DTO to send back to the client.
        return ChatMessageDto(
            id = createdEntity.id,
            chatId = createdEntity.chatId,
            message = createdEntity.message,
            isUser = createdEntity.isUser,
            timestamp = createdEntity.timestamp.toString()
        )
    }

    override fun getChatsForUser(userId: String): Flow<ChatDto> {
        // 1. Call the repository to get the list of Chat entities.
        val chatEntities = messageRepository.getChatsForUser(userId)

        // 2. Map the list of entities to a list of DTOs for the client.
        return chatEntities.map { entity ->
            ChatDto(
                id = entity.id,
                userId = entity.userId,
                title = entity.title,
                description = entity.description,
                textModelName = entity.textModelName,
                isBookmarked = entity.isBookmarked,
                createdAt = entity.createdAt.toString()
            )
        }
    }

    override suspend fun clearChatMessages(userId: String, chatId: String): Int {
        // This function has a simple return type, so we can just pass the call through.
        // The repository already contains the necessary security checks.
        return messageRepository.clearChatMessages(userId, chatId)
    }
}