package org.yassineabou.llms.app.core.data.local

import app.cash.sqldelight.async.coroutines.synchronous
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import me.sujanpoudel.utils.paths.appCacheDirectory
import org.yassineabou.llms.LlmsDatabase

actual class DatabaseFactory {
    actual  fun createDriver(): SqlDriver =
        JdbcSqliteDriver(
            url = "jdbc:sqlite:${appCacheDirectory("org.yassineabou.llms", true)}/LlmsDatabase.db",
            schema = LlmsDatabase.Schema.synchronous()
        ).apply { enableForeignKeys() }
}