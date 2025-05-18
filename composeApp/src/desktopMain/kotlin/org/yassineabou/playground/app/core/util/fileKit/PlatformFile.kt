package org.yassineabou.playground.app.core.util.fileKit


import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.io.files.Path
import okio.buffer
import okio.sink
import java.io.File

data class PlatformFile(
    val file: File,
) {
    // Use file.path instead of undefined 'path' property
    override fun toString(): String = file.path
}

// Extension function needs proper implementation
fun PlatformFile.sink(append: Boolean = false) = file.sink(append)

operator fun PlatformFile.div(child: String): PlatformFile =
    PlatformFile(File(this.file, child))

suspend infix fun PlatformFile.write(bytes: ByteArray): Unit =
    withContext(Dispatchers.IO) {
        this@write
            .sink()
            .buffer()
            .use { it.write(bytes) }
    }

// Helper functions
fun PlatformFile(path: Path): PlatformFile = PlatformFile(path.toFile())


