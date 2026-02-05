
package org.yassineabou.llms.app.core.data.local

import app.cash.sqldelight.async.coroutines.awaitCreate
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.worker.createDefaultWebWorkerDriver
import org.yassineabou.llms.LlmsDatabase


actual suspend fun createDriver(): SqlDriver {
    val driver = createDefaultWebWorkerDriver()

    LlmsDatabase.Schema.awaitCreate(driver)
    driver.execute(null, "PRAGMA foreign_keys = ON;", 0)
    return driver
}