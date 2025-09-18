@file:OptIn(ExperimentalTime::class)


package org.yassineabou.llms.repository

import kotlin.time.ExperimentalTime

/*
class MessageRepository {

    suspend fun addMessageToChat(userId: String, message: ChatMessageEntity) = dbQuery {
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

 */