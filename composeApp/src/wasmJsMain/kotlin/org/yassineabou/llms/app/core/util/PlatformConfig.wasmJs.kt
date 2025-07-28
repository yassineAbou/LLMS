package org.yassineabou.llms.app.core.util

actual object PlatformConfig {
    actual val platform: Platform = Platform.Wasm
    actual fun supportsPasskeys() = false
}