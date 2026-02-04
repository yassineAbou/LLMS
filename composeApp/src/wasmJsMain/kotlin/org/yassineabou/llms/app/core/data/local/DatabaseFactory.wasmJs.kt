
package org.yassineabou.llms.app.core.data.local

import app.cash.sqldelight.async.coroutines.awaitCreate
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.worker.createDefaultWebWorkerDriver
import kotlinx.coroutines.*
import org.yassineabou.llms.LlmsDatabase


actual class DatabaseFactory {

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    actual fun createDriver(): SqlDriver {
        val driver = createDefaultWebWorkerDriver().apply {
            enableForeignKeys()
        }

        scope.launch {
            LlmsDatabase.Schema.awaitCreate(driver)
        }

        return driver
    }
}