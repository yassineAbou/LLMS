package org.yassineabou.llms.app.core.util

actual object PlatformConfig {
    actual val platform: Platform = Platform.Ios
    actual fun supportsPasskeys() = false
}