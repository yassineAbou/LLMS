package org.yassineabou.llms.app.core.di

import app.cash.sqldelight.driver.worker.createDefaultWebWorkerDriver
import org.koin.dsl.module
import org.yassineabou.llms.LlmsDatabase


actual val platformModule = module {
    single {
        val driver = createDefaultWebWorkerDriver()
        LlmsDatabaseWrapper(driver, LlmsDatabase(driver))
    }
}