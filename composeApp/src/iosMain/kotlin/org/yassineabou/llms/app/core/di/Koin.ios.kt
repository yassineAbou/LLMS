package org.yassineabou.llms.app.core.di

import app.cash.sqldelight.driver.native.NativeSqliteDriver
import org.koin.dsl.module
import org.yassineabou.llms.LlmsDatabase


actual val platformModule = module {
    single {
        val driver = NativeSqliteDriver(
            schema = LlmsDatabase.Schema,
            name = "LlmsDatabase.db",
        )

        LlmsDatabaseWrapper(driver, LlmsDatabase(driver))
    }
}
