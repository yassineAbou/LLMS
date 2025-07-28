package org.yassineabou.llms.app.core.util

actual object PlatformConfig {
    actual val platform: Platform = Platform.Desktop
    actual fun supportsPasskeys() = false
}