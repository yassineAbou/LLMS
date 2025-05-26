package org.yassineabou.llms.app.core.util

import kotlinx.browser.document
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.khronos.webgl.toInt8Array
import org.w3c.dom.HTMLAnchorElement
import org.w3c.dom.url.URL
import org.w3c.files.File

actual object FileKit

actual suspend fun FileKit.saveImage(
    bytes: ByteArray,
    fileName: String
): String = withContext(Dispatchers.Default) {
    return@withContext try {
        // Create a blob
        val file = File(
            fileBits =  bytes.toCustomJsArray(),
            fileName = fileName,
        )
        // Create a element
        val a = document.createElement("a") as HTMLAnchorElement
        a.href = URL.createObjectURL(file)
        a.download = fileName
        // Trigger the download
        a.click()

        "Image saved successfully."
    } catch (e: Throwable) {
        "Failed to save image: ${e.message}"
    }
}


fun ByteArray.toCustomJsArray(): JsArray<JsAny?> {
    // Create a JS array
    val jsArray = JsArray<JsAny?>()
    jsArray[0] = this.toInt8Array()

    return jsArray
}