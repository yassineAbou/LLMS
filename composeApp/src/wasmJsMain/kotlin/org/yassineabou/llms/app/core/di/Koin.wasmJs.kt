package org.yassineabou.llms.app.core.di

import app.cash.sqldelight.driver.worker.WebWorkerDriver
import org.koin.dsl.module
import org.w3c.dom.Worker
import org.yassineabou.llms.LlmsDatabase

// Top-level function to get the worker URL as a String
private fun getWorkerUrl(): String =
    js("new URL('@cashapp/sqldelight-sqljs-worker/sqljs.worker.js', import.meta.url).href")

actual val platformModule = module {
    single {
        val workerUrl = getWorkerUrl()
        val driver = WebWorkerDriver(Worker(workerUrl))
        LlmsDatabaseWrapper(driver, LlmsDatabase(driver))
    }
}


