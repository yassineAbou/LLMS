package org.yassineabou.llms.app.core.util

expect object FileKit

expect suspend fun FileKit.saveImage(
    bytes: ByteArray,
    fileName: String
): String