package org.yassineabou.llms.app.core.util

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

/*
  Physical Android device: replace with your computer's local IP (run: ipconfig getifaddr en0)
  Android emulator: use http://10.0.2.2:8081
  Both devices must be on the same WiFi network
 */
val PlatformConfig.serverBaseUrl: String
    get() = if (platform == Platform.Android) "http://192.168.1.108:8081" else "http://127.0.0.1:8081"