package org.yassineabou.llms.app.core.data.local

import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import co.touchlab.kermit.Logger
import me.sujanpoudel.utils.paths.appCacheDirectory
import org.yassineabou.llms.LlmsDatabase
import java.util.Properties

actual class DatabaseFactory {
    actual fun createDriver(): SqlDriver {
        val databasePath = "${appCacheDirectory("org.yassineabou.llms", true)}/LlmsDatabase.db"
        val driver = JdbcSqliteDriver(
            url = "jdbc:sqlite:$databasePath",
            properties = Properties().apply {
                put("foreign_keys", "true")
            }
        )

        return migrateIfNeeded(driver)
    }
}

private const val versionPragma = "user_version"

fun migrateIfNeeded(driver: JdbcSqliteDriver): SqlDriver {
    val oldVersion = driver.executeQuery(
        identifier = null,
        sql = "PRAGMA $versionPragma",
        mapper = { cursor ->
            if (cursor.next().value) {
                QueryResult.Value(cursor.getLong(0))
            } else {
                QueryResult.Value(null)
            }
        },
        parameters = 0,
        binders = null
    ).value ?: 0L

    val newVersion = LlmsDatabase.Schema.version.toLong()

    if (oldVersion == 0L) {
        Logger.e { "Creating DB version $newVersion!"}
        LlmsDatabase.Schema.create(driver)
        driver.execute(null, "PRAGMA $versionPragma=$newVersion", 0)
    } else if (oldVersion < newVersion) {
        Logger.e {"Migrating DB from version $oldVersion to $newVersion!"}
        LlmsDatabase.Schema.migrate(driver, oldVersion, newVersion)
        driver.execute(null, "PRAGMA $versionPragma=$newVersion", 0)
    } else {
        Logger.i {"DB version $oldVersion is up to date."}
    }
    return driver
}
