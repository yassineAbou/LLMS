package org.yassineabou.llms.app.core.di

import app.cash.sqldelight.async.coroutines.synchronous
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import org.koin.dsl.module
import org.yassineabou.llms.LlmsDatabase


actual val platformModule = module {
    single {
        val driver = NativeSqliteDriver(
            schema = LlmsDatabase.Schema.synchronous(),
            name = "LlmsDatabase.db",
        ).apply {
            enableForeignKeys()
        }

        LlmsDatabaseWrapper(driver, LlmsDatabase(driver))
    }
}
