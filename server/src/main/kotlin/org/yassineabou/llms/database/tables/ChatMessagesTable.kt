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
    )
    val message = text("message")
    val isUser = bool("is_user")
    val timestamp = timestamp("created_at").default(Clock.System.now())

    override val primaryKey = PrimaryKey(id)
}

// Data class for ChatMessage entity
data class ChatMessage(
    val id: Int,
    val chatId: String,
    val message: String,
    val isUser: Boolean,
    val timestamp: Instant
)