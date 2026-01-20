@file:OptIn(ExperimentalTime::class)

package org.yassineabou.llms.repository

import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.flow.singleOrNull
import org.jetbrains.exposed.v1.core.ResultRow
import org.jetbrains.exposed.v1.core.eq
import org.jetbrains.exposed.v1.r2dbc.deleteWhere
import org.jetbrains.exposed.v1.r2dbc.insert
import org.jetbrains.exposed.v1.r2dbc.select
import org.jetbrains.exposed.v1.r2dbc.selectAll
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
        // Try to find the user first using selectAll().where { }
        val existingUser = UsersTable
            .selectAll()
            .where { UsersTable.id eq id }
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
        UsersTable
            .selectAll()
            .where { UsersTable.id eq id }
            .map { toUser(it) }
            .single()
    }

    suspend fun deleteUser(userId: String) = dbQuery {
        UsersTable.deleteWhere { id eq userId }
    }

    private fun toUser(row: ResultRow): UserEntity = UserEntity(
        id = row[UsersTable.id],
        username = row[UsersTable.username],
        email = row[UsersTable.email],
        profilePicUrl = row[UsersTable.profilePicUrl],
        createdAt = row[UsersTable.createdAt]
    )
}