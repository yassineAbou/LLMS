@file:OptIn(ExperimentalTime::class)

package org.yassineabou.llms.database.tables

import org.jetbrains.exposed.v1.core.ReferenceOption
import org.jetbrains.exposed.v1.core.Table
import org.jetbrains.exposed.v1.core.eq
import org.jetbrains.exposed.v1.datetime.timestamp
import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

object ChatsTable : Table("chats") {
    val id = text("id")
    val userId = text("user_id").references(
        UsersTable.id,
        onDelete = ReferenceOption.CASCADE
    ).index() // Index for filtering by user
    val title = text("title")
    val description = text("description").nullable()
    val textModelName = text("text_model_name")
    val isBookmarked = bool("is_bookmarked").default(false)
    val createdAt = timestamp("created_at").default(Clock.System.now())

    override val primaryKey = PrimaryKey(id)

    init {
        // Composite index for the common query: WHERE userId = ? ORDER BY createdAt DESC
        index("idx_chats_user_created", false, userId, createdAt)

        // Composite index for security checks: WHERE id = ? AND userId = ?
        index("idx_chats_id_user", false, id, userId)

        // Partial index for bookmarked chats (if you query them separately)
        index("idx_chats_bookmarked", false, userId, createdAt) { isBookmarked eq true }
    }
}

// Data class for Chat entity
data class ChatEntity(
    val id: String,
    val userId: String,
    val title: String,
    val description: String?,
    val textModelName: String,
    val isBookmarked: Boolean,
    val createdAt: Instant
)
