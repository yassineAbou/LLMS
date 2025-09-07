@file:OptIn(ExperimentalTime::class)

package org.yassineabou.llms.repository

import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.flow.singleOrNull
import org.jetbrains.exposed.v1.core.ResultRow
import org.jetbrains.exposed.v1.core.SqlExpressionBuilder.eq
import org.jetbrains.exposed.v1.r2dbc.deleteWhere
import org.jetbrains.exposed.v1.r2dbc.insert
import org.jetbrains.exposed.v1.r2dbc.select
import org.yassineabou.llms.database.DatabaseFactory.dbQuery
import org.yassineabou.llms.database.tables.UserEntity
import org.yassineabou.llms.database.tables.UsersTable
import kotlin.time.ExperimentalTime

class UserRepository {

    suspend fun findByIdOrCreateUser(
        id: String,
        username: String,
        email: String,
        profilePicUrl: String?
    ) = dbQuery {
        // Try to find the user first
        val existingUser = UsersTable.select(UsersTable.id eq id)
            .map { toUser(it) }
            .singleOrNull()

        if (existingUser != null) {
            return@dbQuery existingUser
        }

        // If not found, insert a new user
        UsersTable.insert {
            it[UsersTable.id] = id
            it[UsersTable.username] = username
            it[UsersTable.email] = email
            it[UsersTable.profilePicUrl] = profilePicUrl
        }
        // Query again to get the fully created User object with defaults (like createdAt)
        UsersTable.select(UsersTable.id eq id)
            .map { toUser(it) }
            .single()
    }

    /**
     * Deletes a user and all their associated data (chats, messages, images)
     * due to the CASCADE constraint in the database schema.
     *
     * @param userId The ID of the user to delete.
     * @return The number of deleted rows (should be 1 if successful).
     */
    suspend fun deleteUser(userId: String) = dbQuery {
        UsersTable.deleteWhere { id eq userId }
    }

    // Helper function to map a ResultRow to a User data class
    @OptIn(ExperimentalTime::class)
    private fun toUser(row: ResultRow): UserEntity = UserEntity(
        id = row[UsersTable.id],
        username = row[UsersTable.username],
        email = row[UsersTable.email],
        profilePicUrl = row[UsersTable.profilePicUrl],
        createdAt = row[UsersTable.createdAt]
    )
}