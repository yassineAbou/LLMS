package org.yassineabou.playground.app.core.util

enum class Platform {
    Android,
    Ios,
    Desktop,
    Wasm
}

expect object PlatformConfig {
    val platform: Platform
}

fun PlatformConfig.isAndroid() = platform == Platform.Android
fun PlatformConfig.isIos() = platform == Platform.Ios
fun PlatformConfig.isDesktop() = platform == Platform.Desktop
fun PlatformConfig.isWasm() = platform == Platform.Wasm