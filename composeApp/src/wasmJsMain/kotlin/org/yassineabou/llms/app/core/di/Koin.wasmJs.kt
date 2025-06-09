package org.yassineabou.llms.app.core.di

import app.cash.sqldelight.driver.worker.WebWorkerDriver
import org.koin.dsl.module
import org.w3c.dom.Worker
import org.yassineabou.llms.LlmsDatabase


private val workerScriptUrl: String =
    js("""new URL("@cashapp/sqldelight-sqljs-worker/sqljs.worker.js", import.meta.url)""")


actual val platformModule = module {
    single {
        val driver = WebWorkerDriver(Worker(workerScriptUrl)).apply {
            enableForeignKeys()
        }
        LlmsDatabaseWrapper(driver, LlmsDatabase(driver))
    }
}


