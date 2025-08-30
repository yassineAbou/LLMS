@file:OptIn(ExperimentalTime::class)

package org.yassineabou.llms.database.tables

import org.jetbrains.exposed.v1.core.Table
import org.jetbrains.exposed.v1.datetime.timestamp
import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlin.time.Instant


object UsersTable : Table("users") {
    val id = text("id") // Google Sub ID
    val username = text("username")
    val email = text("email")
    val profilePicUrl = text("profile_pic_url").nullable()
    val createdAt = timestamp("created_at").default(Clock.System.now())

    override val primaryKey = PrimaryKey(id)
}

// Data class for User entity
data class User(
    val id: String,
    val username: String,
    val email: String,
    val profilePicUrl: String?,
    val createdAt: Instant
)