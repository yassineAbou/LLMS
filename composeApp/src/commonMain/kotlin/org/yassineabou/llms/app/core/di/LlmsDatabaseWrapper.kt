package org.yassineabou.llms.app.core.di

import app.cash.sqldelight.db.SqlDriver
import org.yassineabou.llms.LlmsDatabase

class LlmsDatabaseWrapper(val driver: SqlDriver, val instance: LlmsDatabase)