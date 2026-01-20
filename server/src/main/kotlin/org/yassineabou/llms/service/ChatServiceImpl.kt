@file:OptIn(ExperimentalTime::class)

package org.yassineabou.llms.service

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.yassineabou.llms.api.ChatDto
import org.yassineabou.llms.api.ChatService
import org.yassineabou.llms.database.tables.ChatEntity
import org.yassineabou.llms.repository.ChatRepository
import kotlin.time.Clock
import kotlin.time.ExperimentalTime


class ChatServiceImpl(private val chatRepository: ChatRepository) : ChatService {

    override suspend fun createChat(
        id: String,
        userId: String,
        title: String,
        description: String?,
        textModelName: String,
        isBookmarked: Boolean
    ): ChatDto {
        // 1. Create a server-side Entity. The server is authoritative for the ID and timestamp.
        val newChatEntity = ChatEntity(
            id = id,
            userId = userId, // Use the ID from the authenticated user context
            title = title,
            description = description,
            textModelName = textModelName,
            isBookmarked = isBookmarked, // New chats are not bookmarked by default
            createdAt = Clock.System.now()
        )

        // 2. Call the repository to save the new chat.
        val createdEntity = chatRepository.createChat(userId, newChatEntity)

        // 3. Convert the resulting entity to a DTO to send back to the client.
        return ChatDto(
            id = createdEntity.id,
            userId = createdEntity.userId,
            title = createdEntity.title,
            description = createdEntity.description,
            textModelName = createdEntity.textModelName,
            isBookmarked = createdEntity.isBookmarked,
            createdAt = createdEntity.createdAt.toString()
        )
    }

    override fun getChatsForUser(userId: String): Flow<ChatDto> {
        // 1. Call the repository to get the list of Chat entities.
        val chatEntities = chatRepository.getChatsForUser(userId)

        // 2. Map the list of entities to a list of DTOs.
        //    (Your repository returns a List directly, so no .toList() is needed here).
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

    override suspend fun deleteChat(userId: String, chatId: String): Int {
        // This is a direct pass-through, as the repository method already
        // handles the logic and security check (matching userId and chatId).
        return chatRepository.deleteChat(userId, chatId)
    }
}