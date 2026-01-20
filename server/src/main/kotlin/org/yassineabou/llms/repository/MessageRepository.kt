@file:OptIn(ExperimentalTime::class)


package org.yassineabou.llms.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import org.jetbrains.exposed.v1.core.SortOrder
import org.jetbrains.exposed.v1.core.and
import org.jetbrains.exposed.v1.core.eq
import org.jetbrains.exposed.v1.r2dbc.deleteWhere
import org.jetbrains.exposed.v1.r2dbc.insert
import org.jetbrains.exposed.v1.r2dbc.select
import org.jetbrains.exposed.v1.r2dbc.selectAll
import org.jetbrains.exposed.v1.r2dbc.upsert
import org.yassineabou.llms.database.DatabaseFactory.dbQuery
import org.yassineabou.llms.database.DatabaseFactory.dbQueryAsFlow
import org.yassineabou.llms.database.tables.ChatEntity
import org.yassineabou.llms.database.tables.ChatMessageEntity
import org.yassineabou.llms.database.tables.ChatMessagesTable
import org.yassineabou.llms.database.tables.ChatsTable
import kotlin.time.ExperimentalTime


class MessageRepository {

    suspend fun addMessageToChat(userId: String, message: ChatMessageEntity) = dbQuery {
        // First verify the chat belongs to the user using selectAll().where { }
        val chatExists = ChatsTable
            .selectAll()
            .where { (ChatsTable.id eq message.chatId) and (ChatsTable.userId eq userId) }
            .count() > 0

        if (!chatExists) {
            throw SecurityException("User does not have access to this chat")
        }

        // Then insert the message
        ChatMessagesTable.insert {
            it[chatId] = message.chatId
            it[this.message] = message.message
            it[isUser] = message.isUser
            it[timestamp] = message.timestamp
        }.let { message.copy(id = it[ChatMessagesTable.id]) }
    }

    fun getChatsForUser(userId: String): Flow<ChatEntity> = dbQueryAsFlow {
        ChatsTable
            .selectAll()
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
            .toList()
    }

    suspend fun clearChatMessages(userId: String, chatId: String): Int = dbQuery {
        // First verify the chat belongs to the user using selectAll().where { }
        val chatExists = ChatsTable
            .selectAll()
            .where { (ChatsTable.id eq chatId) and (ChatsTable.userId eq userId) }
            .count() > 0

        if (!chatExists) {
            throw SecurityException("User does not have access to this chat")
        }

        // Then delete the messages
        ChatMessagesTable.deleteWhere { ChatMessagesTable.chatId eq chatId }
    }
}