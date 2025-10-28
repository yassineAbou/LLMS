package org.yassineabou.llms.database

import org.jetbrains.exposed.v1.r2dbc.R2dbcDatabase
import org.jetbrains.exposed.v1.r2dbc.R2dbcTransaction
import org.jetbrains.exposed.v1.r2dbc.SchemaUtils
import org.jetbrains.exposed.v1.r2dbc.transactions.suspendTransaction
import org.yassineabou.llms.database.tables.ChatMessagesTable
import org.yassineabou.llms.database.tables.ChatsTable
import org.yassineabou.llms.database.tables.GeneratedImagesTable
import org.yassineabou.llms.database.tables.UsersTable


object DatabaseFactory {
    private val database: R2dbcDatabase by lazy {
        val r2dbcUrl = buildR2dbcUrl()
        R2dbcDatabase.connect(
            url = r2dbcUrl,
            user = System.getenv("DATABASE_USER") ?: "postgres",
            password = System.getenv("DATABASE_PASSWORD") ?: "password"
        )
    }

    suspend fun init() {
        // Schema creation in a suspended transaction
        suspendTransaction(db = database) {
            SchemaUtils.create(
                UsersTable,
                ChatsTable,
                GeneratedImagesTable,
                ChatMessagesTable
            )
        }
    }

    private fun buildR2dbcUrl(): String {
        val jdbcUrl = System.getenv("DATABASE_URL") ?: "jdbc:postgresql://localhost:5432/your_db_name"

        // Handle different JDBC URL formats
        return if (jdbcUrl.startsWith("jdbc:postgresql://")) {
            jdbcUrl.replace("jdbc:postgresql://", "r2dbc:postgresql://")
        } else {
            // Fallback to default R2DBC URL
            "r2dbc:postgresql://localhost:5432/your_db_name"
        }
    }

    suspend fun <T> dbQuery(block: suspend R2dbcTransaction.() -> T): T =
        suspendTransaction(db = database, statement = block)
}


