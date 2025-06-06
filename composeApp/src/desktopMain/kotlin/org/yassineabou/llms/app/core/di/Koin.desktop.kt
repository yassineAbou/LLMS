package org.yassineabou.llms.app.core.di

import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import me.sujanpoudel.utils.paths.appCacheDirectory
import org.koin.dsl.module
import org.yassineabou.llms.LlmsDatabase

actual val platformModule = module {
    single {
        /* Before
        val driver = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
            .also { LlmsDatabase.Schema.create(it) }
            .apply {
                enableForeignKeys()
            }

         */
        // After following the solution
        val appCacheDirectory = appCacheDirectory(appId = "org.yassineabou.llms", createDir = true)
        val driver = JdbcSqliteDriver(url = "jdbc:sqlite:$appCacheDirectory/LlmsDatabase.db", schema = LlmsDatabase.Schema).apply {
            enableForeignKeys()
        }
        LlmsDatabaseWrapper(driver, LlmsDatabase(driver))
    }
}
