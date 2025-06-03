package org.yassineabou.llms.app.core.di

import app.cash.sqldelight.db.SqlDriver
import org.yassineabou.llms.LlmsDatabase

class LlmsDatabaseWrapper(val driver: SqlDriver, val instance: LlmsDatabase)

fun SqlDriver.enableForeignKeys() {
    this.execute(null, "PRAGMA foreign_keys = ON;", 0)
}