package org.yassineabou.playground.app.core.util

expect object FileKit

expect suspend fun FileKit.saveImage(
    bytes: ByteArray,
    fileName: String
): String