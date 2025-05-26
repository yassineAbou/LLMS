package org.yassineabou.llms.app.core.util.fileKit

object FileKitPlatformUtil {
    val current: FileKitPlatform
        get() {
            val system = System.getProperty("os.name").lowercase()
            return if (system.contains("win")) {
                FileKitPlatform.Windows
            } else if (
                system.contains("nix") ||
                system.contains("nux") ||
                system.contains("aix")
            ) {
                FileKitPlatform.Linux
            } else if (system.contains("mac")) {
                FileKitPlatform.MacOS
            } else {
                FileKitPlatform.Linux
            }
        }
}

enum class FileKitPlatform {
    Linux,
    MacOS,
    Windows
}