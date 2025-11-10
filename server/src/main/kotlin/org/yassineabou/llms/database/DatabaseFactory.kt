package org.yassineabou.llms.database

import io.ktor.util.logging.KtorSimpleLogger
import io.r2dbc.spi.ConnectionFactoryOptions
import io.r2dbc.spi.IsolationLevel
import org.jetbrains.exposed.v1.migration.r2dbc.MigrationUtils
import org.jetbrains.exposed.v1.r2dbc.R2dbcDatabase
import org.jetbrains.exposed.v1.r2dbc.R2dbcDatabaseConfig
import org.jetbrains.exposed.v1.r2dbc.R2dbcTransaction
import org.jetbrains.exposed.v1.r2dbc.transactions.suspendTransaction
import org.yassineabou.llms.database.tables.ChatMessagesTable
import org.yassineabou.llms.database.tables.ChatsTable
import org.yassineabou.llms.database.tables.GeneratedImagesTable
import org.yassineabou.llms.database.tables.UsersTable


object DatabaseFactory {
    private val logger = KtorSimpleLogger("or.yassineabou.llms.DatabaseFactory")

    private val database: R2dbcDatabase by lazy {
        val url = System.getenv("SERVER_DATABASE_URL")
            ?: throw IllegalStateException("SERVER_DATABASE_URL environment variable must be set")

        val user = System.getenv("SERVER_DATABASE_USER")
            ?: throw IllegalStateException("SERVER_DATABASE_USER environment variable must be set")

        val password = System.getenv("SERVER_DATABASE_PASSWORD")
            ?: throw IllegalStateException("SERVER_DATABASE_PASSWORD environment variable must be set")

        logger.info("📍 Attempting to connect to: ${maskUrl(url)}")
        logger.info("👤 Username: $user")

        try {
            R2dbcDatabase.connect(
                url = url,
                databaseConfig = R2dbcDatabaseConfig {
                    defaultMaxAttempts = 3
                    defaultR2dbcIsolationLevel = IsolationLevel.READ_COMMITTED
                    connectionFactoryOptions {
                        option(ConnectionFactoryOptions.USER, user)
                        option(ConnectionFactoryOptions.PASSWORD, password)
                        if (System.getenv("SERVER_DATABASE_SSL") == "true") {
                            option(ConnectionFactoryOptions.SSL, true)
                        }
                    }
                }
            ).also {
                logger.info("✅ Database connection initialized successfully")
                logger.info("🔧 Connected to: ${maskUrl(url)}")
            }
        } catch (e: Exception) {
            logger.error("❌ Failed to connect to database", e)
            logger.error("🔍 Connection Details:")
            logger.error("   - URL: ${maskUrl(url)}")
            logger.error("   - User: $user")
            logger.error("   - Error: ${e.message}")
            throw e
        }
    }

    private fun maskUrl(url: String): String {
        return url.replace(Regex("(password=)[^&]*"), "$1******")
    }

    suspend fun init() {
        try {
            logger.info("🚀 Starting database initialization...")

            suspendTransaction(db = database) {
                logger.info("📋 Checking migration requirements...")

                val statements = MigrationUtils.statementsRequiredForDatabaseMigration(
                    UsersTable,
                    ChatsTable,
                    GeneratedImagesTable,
                    ChatMessagesTable
                )

                logger.info("📊 Found ${statements.size} migration statements")

                if (statements.isNotEmpty()) {
                    logger.info("🔄 Executing migrations...")
                    statements.forEachIndexed { index, statement ->
                        logger.debug("  [${index + 1}/${statements.size}] Executing: ${statement.take(50)}...")
                        try {
                            exec(statement)
                            logger.debug("  ✓ Statement ${index + 1} executed successfully")
                        } catch (e: Exception) {
                            logger.error("  ✗ Statement ${index + 1} failed: ${e.message}")
                            throw e
                        }
                    }
                    logger.info("✅ Database schema initialized successfully")
                } else {
                    logger.info("✅ Database schema is up to date")
                }
            }
        } catch (e: Exception) {
            logger.error("❌ Database initialization failed", e)
            throw e
        }
    }

    suspend fun <T> dbQuery(block: suspend R2dbcTransaction.() -> T): T =
        try {
            suspendTransaction(db = database, statement = block)
        } catch (e: Exception) {
            logger.error("❌ Database query failed: ${e.message}", e)
            throw e
        }
}