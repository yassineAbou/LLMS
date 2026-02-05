package org.yassineabou.llms.app.core.data.local

import app.cash.sqldelight.async.coroutines.synchronous
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import co.touchlab.sqliter.DatabaseConfiguration
import org.yassineabou.llms.LlmsDatabase

actual class DatabaseFactory {
    actual fun createDriver(): SqlDriver =
        NativeSqliteDriver(
            schema = LlmsDatabase.Schema.synchronous(),
            name = "LlmsDatabase.db",
            onConfiguration = { config: DatabaseConfiguration ->
                config.copy(
                    extendedConfig =
                        DatabaseConfiguration.Extended(
                            foreignKeyConstraints = true
                        )
                )
            }
        )
}