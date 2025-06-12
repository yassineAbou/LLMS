package org.yassineabou.llms.app.core.di

import app.cash.sqldelight.async.coroutines.synchronous
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import org.koin.dsl.module
import org.yassineabou.llms.LlmsDatabase
import org.yassineabou.llms.MyApp


actual val platformModule = module {
    single {
        val driver = AndroidSqliteDriver(
                schema = LlmsDatabase.Schema.synchronous(),
                context = MyApp.getContext(),
                name = "LlmsDatabase.db",
            ).apply {
                enableForeignKeys()
            }
        LlmsDatabaseWrapper(driver, LlmsDatabase(driver))
    }
}
