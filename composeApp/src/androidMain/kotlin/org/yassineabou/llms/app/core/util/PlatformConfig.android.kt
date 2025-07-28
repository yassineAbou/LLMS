package org.yassineabou.llms.app.core.util

import android.os.Build

actual object PlatformConfig {
    actual val platform: Platform = Platform.Android
    actual fun supportsPasskeys() =  Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE
}