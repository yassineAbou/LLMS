package org.yassineabou.playground.app.core.util.fileKit


import kotlinx.io.files.Path
import kotlinx.io.files.SystemFileSystem
import org.yassineabou.playground.app.core.util.FileKit
import java.io.File

fun Path.toFile(): File = File(this.toString())

fun String.toPath(): Path = Path(this)

operator fun Path.div(child: String): Path = Path(this, child)

val FileKit.pictureDir: PlatformFile
    get() = when (FileKitPlatformUtil.current) {
        FileKitPlatform.Linux -> getEnv("HOME").toPath() / "Pictures"
        FileKitPlatform.MacOS -> getEnv("HOME").toPath() / "Pictures"
        FileKitPlatform.Windows -> getEnv("USERPROFILE").toPath() / "Pictures"
    }.also(Path::assertExists).let(::PlatformFile)

private fun getEnv(key: String): String {
    return System.getenv(key)
        ?: throw IllegalStateException("Environment variable $key not found.")
}

private fun Path.assertExists() {
    if (!SystemFileSystem.exists(this)) {
        this.toFile().mkdirs()
    }
}