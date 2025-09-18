package org.yassineabou.llms.database


/*
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
        suspendTransaction(Dispatchers.IO, db = database) {
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

    suspend fun <T> dbQuery(block: suspend () -> T): T =
        suspendTransaction(Dispatchers.IO, db = database) { block() }
}

 */

