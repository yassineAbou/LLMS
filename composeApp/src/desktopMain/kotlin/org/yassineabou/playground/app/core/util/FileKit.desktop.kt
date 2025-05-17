package org.yassineabou.playground.app.core.util

actual object FileKit

actual suspend fun FileKit.saveImage(
    bytes: ByteArray,
    fileName: String
): String {
    return "hello"
}