package org.yassineabou.llms.app.core.data.local

import app.cash.sqldelight.db.SqlDriver

expect suspend fun createDriver(): SqlDriver
