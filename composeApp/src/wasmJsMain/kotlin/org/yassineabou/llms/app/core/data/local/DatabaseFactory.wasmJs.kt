package org.yassineabou.llms.app.core.data.local

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.worker.WebWorkerDriver
import org.w3c.dom.Worker
import org.yassineabou.llms.LlmsDatabase


private val workerScriptUrl: String =
    js("""new URL("@cashapp/sqldelight-sqljs-worker/sqljs.worker.js", import.meta.url)""")

actual class DatabaseFactory {
    actual fun createDriver(): SqlDriver {
        val driver = WebWorkerDriver(Worker(workerScriptUrl)).apply {
            enableForeignKeys()
        }
        LlmsDatabase.Schema.create(driver)
        return driver
    }
}