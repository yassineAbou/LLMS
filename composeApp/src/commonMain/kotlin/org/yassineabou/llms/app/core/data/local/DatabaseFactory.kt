package org.yassineabou.llms.app.core.data.local

import app.cash.sqldelight.db.SqlDriver

expect class DatabaseFactory {
      fun createDriver(): SqlDriver
}

fun SqlDriver.enableForeignKeys() {
    this.execute(null, "PRAGMA foreign_keys = ON;", 0)
}