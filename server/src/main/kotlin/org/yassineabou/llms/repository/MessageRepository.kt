@file:OptIn(ExperimentalTime::class)


package org.yassineabou.llms.repository

import kotlinx.coroutines.flow.map
import org.jetbrains.exposed.v1.core.SortOrder
import org.jetbrains.exposed.v1.core.and
import org.jetbrains.exposed.v1.core.statements.UpsertSqlExpressionBuilder.eq
import org.jetbrains.exposed.v1.r2dbc.deleteWhere
import org.jetbrains.exposed.v1.r2dbc.insert
import org.jetbrains.exposed.v1.r2dbc.select
import org.jetbrains.exposed.v1.r2dbc.selectAll
import org.jetbrains.exposed.v1.r2dbc.upsert
import org.yassineabou.llms.database.DatabaseFactory.dbQuery
import org.yassineabou.llms.database.tables.Chat
import org.yassineabou.llms.database.tables.ChatMessage
import org.yassineabou.llms.database.tables.ChatMessagesTable
import org.yassineabou.llms.database.tables.ChatsTable
import kotlin.time.ExperimentalTime


class MessageRepository {

    suspend fun addMessageToChat(userId: String, message: ChatMessage) = dbQuery {
        // First verify the chat belongs to the user
        val chatExists = ChatsTable
            .select ( (ChatsTable.id eq message.chatId) and (ChatsTable.userId eq userId) )
            .count() > 0

        if (!chatExists) {
            throw SecurityException("User does not have access to this chat")
        }

        // Then insert the message
        ChatMessagesTable.upsert {
            it[chatId] = message.chatId
            it[this.message] = message.message
            it[isUser] = message.isUser
            it[timestamp] = message.timestamp
        }.let { message.copy(id = it[ChatMessagesTable.id]) }
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

    suspend fun clearChatMessages(userId: String, chatId: String): Int = dbQuery {
        // First verify the chat belongs to the user
        val chatExists = ChatsTable
            .select ( (ChatsTable.id eq chatId) and (ChatsTable.userId eq userId) )
            .count() > 0

        if (!chatExists) {
            throw SecurityException("User does not have access to this chat")
        }

        // Then delete the messages
        ChatMessagesTable.deleteWhere { ChatMessagesTable.chatId eq chatId }
    }

}