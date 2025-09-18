@file:OptIn(ExperimentalTime::class)

package org.yassineabou.llms.repository

import kotlin.time.ExperimentalTime

/*
class ChatRepository {

    suspend fun createChat(userId: String, chatEntity: ChatEntity) = dbQuery {
        // Verify the chat's userId matches the authenticated user
        if (chatEntity.userId != userId) {
            throw SecurityException("User ID mismatch")
        }

        ChatsTable.upsert {
            it[id] = chatEntity.id
            it[ChatsTable.userId] = chatEntity.userId
            it[title] = chatEntity.title
            it[description] = chatEntity.description
            it[textModelName] = chatEntity.textModelName
            it[isBookmarked] = chatEntity.isBookmarked
            it[createdAt] = chatEntity.createdAt
        }.let { chatEntity }
    }

    suspend fun getChatsForUser(userId: String) = dbQuery {
        ChatsTable.selectAll()
            .where { ChatsTable.userId eq userId }
            .orderBy(ChatsTable.createdAt, SortOrder.DESC)
            .map { row ->
                ChatEntity(
                    id = row[ChatsTable.id],
                    userId = row[ChatsTable.userId],
                    title = row[ChatsTable.title],
                    description = row[ChatsTable.description],
                    textModelName = row[ChatsTable.textModelName],
                    isBookmarked = row[ChatsTable.isBookmarked],
                    createdAt = row[ChatsTable.createdAt]
                )
            }
    }

    suspend fun deleteChat(userId: String, chatId: String): Int = dbQuery {
        ChatsTable.deleteWhere {
            (ChatsTable.id eq chatId) and (ChatsTable.userId eq userId)
        }
    }

}

 */