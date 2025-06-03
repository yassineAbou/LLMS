package org.yassineabou.llms.app.core.di

import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import org.koin.dsl.module
import org.yassineabou.llms.LlmsDatabase

actual val platformModule = module {
    single {
        val driver = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
            .also { LlmsDatabase.Schema.create(it) }

        LlmsDatabaseWrapper(driver, LlmsDatabase(driver))

    }
}
