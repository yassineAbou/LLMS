@file:OptIn(ExperimentalTime::class)

package org.yassineabou.llms.repository

import kotlinx.coroutines.flow.map
import org.jetbrains.exposed.v1.core.SortOrder
import org.jetbrains.exposed.v1.core.and
import org.jetbrains.exposed.v1.core.statements.UpsertSqlExpressionBuilder.eq
import org.jetbrains.exposed.v1.r2dbc.deleteWhere
import org.jetbrains.exposed.v1.r2dbc.selectAll
import org.jetbrains.exposed.v1.r2dbc.upsert
import org.yassineabou.llms.database.DatabaseFactory.dbQuery
import org.yassineabou.llms.database.tables.Chat
import org.yassineabou.llms.database.tables.ChatsTable
import kotlin.time.ExperimentalTime

class ChatRepository {

    suspend fun createChat(userId: String, chat: Chat) = dbQuery {
        // Verify the chat's userId matches the authenticated user
        if (chat.userId != userId) {
            throw SecurityException("User ID mismatch")
        }

        ChatsTable.upsert {
            it[id] = chat.id
            it[ChatsTable.userId] = chat.userId
            it[title] = chat.title
            it[description] = chat.description
            it[textModelName] = chat.textModelName
            it[isBookmarked] = chat.isBookmarked
            it[createdAt] = chat.createdAt
        }.let { chat }
    }

    suspend fun getChatsForUser(userId: String) = dbQuery {
        ChatsTable.selectAll()
            .where { ChatsTable.userId eq userId }
            .orderBy(ChatsTable.createdAt, SortOrder.DESC)
            .map { row ->
                Chat(
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