package org.yassineabou.llms.app.core.util

enum class Platform {
    Android,
    Ios,
    Desktop,
    Wasm
}

expect object PlatformConfig {
    val platform: Platform
    fun supportsPasskeys(): Boolean
}

fun PlatformConfig.isAndroid() = platform == Platform.Android
fun PlatformConfig.isIos() = platform == Platform.Ios
fun PlatformConfig.isDesktop() = platform == Platform.Desktop
fun PlatformConfig.isWasm() = platform == Platform.Wasm