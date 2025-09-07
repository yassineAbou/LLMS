@file:OptIn(ExperimentalTime::class)

package org.yassineabou.llms.database.tables

import org.jetbrains.exposed.v1.core.ReferenceOption
import org.jetbrains.exposed.v1.core.Table
import org.jetbrains.exposed.v1.datetime.timestamp
import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

object ChatMessagesTable : Table("chat_messages") {
    val id = integer("id").autoIncrement()
    val chatId = text("chat_id").references(
        ChatsTable.id,
        onDelete = ReferenceOption.CASCADE
    ).index() // Index for foreign key lookups
    val message = text("message")
    val isUser = bool("is_user")
    val timestamp = timestamp("created_at").default(Clock.System.now())

    override val primaryKey = PrimaryKey(id)

    init {
        // Composite index for getting messages by chat ordered by time
        index("idx_messages_chat_timestamp", false, chatId, timestamp)

        // Partial index for user messages only (if you query them separately)
        index("idx_messages_user_only", false, chatId, timestamp) { isUser eq true }
    }
}

// Data class for ChatMessage entity
data class ChatMessageEntity(
    val id: Int,
    val chatId: String,
    val message: String,
    val isUser: Boolean,
    val timestamp: Instant
)