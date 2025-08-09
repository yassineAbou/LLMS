package org.yassineabou.llms.app.core.data.local

import app.cash.sqldelight.async.coroutines.synchronous
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import org.yassineabou.llms.LlmsDatabase
import org.yassineabou.llms.MyApp

actual class DatabaseFactory {
    actual fun createDriver(): SqlDriver =
        AndroidSqliteDriver(
            schema = LlmsDatabase.Schema.synchronous(),
            context = MyApp.getContext(),
            name = "LlmsDatabase.db"
        ).apply { enableForeignKeys() }
}