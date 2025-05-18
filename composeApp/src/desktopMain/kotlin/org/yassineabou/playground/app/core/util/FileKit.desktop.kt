package org.yassineabou.playground.app.core.util

import org.yassineabou.playground.app.core.util.fileKit.div
import org.yassineabou.playground.app.core.util.fileKit.pictureDir
import org.yassineabou.playground.app.core.util.fileKit.write

actual object FileKit {
    private var _appId: String? = null

    fun init(appId: String) {
        _appId = appId
    }
}

actual suspend fun FileKit.saveImage(
    bytes: ByteArray,
    fileName: String
): String {
    FileKit.pictureDir / fileName write bytes
    return "Image saved successfully."
}