package org.yassineabou.llms.app.core.data.local

import androidx.sqlite.db.SupportSQLiteDatabase
import app.cash.sqldelight.async.coroutines.synchronous
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import org.yassineabou.llms.LlmsDatabase
import org.yassineabou.llms.MyApp
import java.io.File


actual class DatabaseFactory {
    actual fun createDriver(): SqlDriver =
        AndroidSqliteDriver(
            schema = LlmsDatabase.Schema.synchronous(),
            context = MyApp.getContext(),
            name = "LlmsDatabase.db",
            callback = object : AndroidSqliteDriver.Callback(LlmsDatabase.Schema.synchronous()) {
                override fun onOpen(db: SupportSQLiteDatabase) {
                    super.onOpen(db)
                    db.setForeignKeyConstraintsEnabled(true)
                }
            }
        )
}


/*
actual class DatabaseFactory {
    actual fun createDriver(): SqlDriver {
        val context = MyApp.getContext()
        val dbName = "LlmsDatabase.db"
        val minSizeInMb = 1 // Adjust based on your embedded database size

        // Validate database before creating driver
        val dbPath = context.getDatabasePath(dbName).absolutePath

        if (!isValidSQLiteDatabase(dbPath, minSizeInMb)) {
            // Delete corrupted database if it exists
            context.deleteDatabase(dbName)
        }

        return AndroidSqliteDriver(
            schema = LlmsDatabase.Schema.synchronous(),
            context = context,
            name = dbName
        ).apply { enableForeignKeys() }
    }

    private fun isValidSQLiteDatabase(dbPath: String, minSizeInMb: Int): Boolean {
        val file = File(dbPath)

        // Check if file exists and is readable
        if (!file.exists() || !file.canRead()) {
            return false
        }

        // Check SQLite header format
        val isReadable = try {
            val buffer = ByteArray(16)
            file.inputStream().use { it.read(buffer) }
            val header = String(buffer, Charsets.UTF_8)
            header.startsWith("SQLite format 3")
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }

        // Validate header and minimum size
        val isValidSize = file.length() > (1024 * 1024 * minSizeInMb)

        return isReadable && isValidSize
    }
}

 */