package org.yassineabou.llms.app.core.util

import io.ktor.util.decodeBase64Bytes
import kotlinx.datetime.Clock


object ImageMetadataUtil {

    fun extractImageData(dataUrl: String): Pair<String, ByteArray> {
        require(dataUrl.startsWith("data:")) { "Invalid data URL format" }

        val parts = dataUrl.split(",", limit = 2)
        require(parts.size == 2) { "Malformed data URL" }

        val header = parts[0]
        val base64Data = parts[1]

        val mimeType = header.substringAfter("data:").substringBefore(";")
        val bytes = base64Data.decodeBase64Bytes() // ✨ Use Ktor's Base64 decoder

        return Pair(mimeType, bytes)
    }

    // Generate filename from prompt and MIME type
    fun generateFileName(prompt: String, mimeType: String): String {
        val baseName = sanitizePrompt(prompt)
        val extension = mimeType.split("/").lastOrNull()?.takeIf { it.isNotBlank() } ?: "dat"
        return "$baseName.$extension"
    }

    // Sanitize prompt for filename safety
    private fun sanitizePrompt(prompt: String): String {
        return prompt.split(" ")
            .take(3)
            .map { word ->
                word.replace(Regex("[^A-Za-z0-9]"), "").lowercase()
            }
            .filter { it.isNotBlank() }
            .joinToString("_")
            .ifEmpty {
                "image_${Clock.System.now().toEpochMilliseconds()}"
            }
    }


    fun detectImageMimeType(bytes: ByteArray): String? {
        if (bytes.isEmpty()) return null

        return when {
            isJpeg(bytes) -> "image/jpeg"
            isPng(bytes) -> "image/png"
            isGif(bytes) -> "image/gif"
            isWebP(bytes) -> "image/webp"
            else -> null
        }
    }

    private fun isJpeg(bytes: ByteArray): Boolean {
        return bytes.size >= 3 &&
                bytes[0] == 0xFF.toByte() &&
                bytes[1] == 0xD8.toByte() &&
                bytes[2] == 0xFF.toByte()
    }

    private fun isPng(bytes: ByteArray): Boolean {
        return bytes.size >= 8 &&
                bytes[0] == 0x89.toByte() &&
                bytes[1] == 0x50.toByte() &&  // P
                bytes[2] == 0x4E.toByte() &&  // N
                bytes[3] == 0x47.toByte() &&  // G
                bytes[4] == 0x0D.toByte() &&
                bytes[5] == 0x0A.toByte() &&
                bytes[6] == 0x1A.toByte() &&
                bytes[7] == 0x0A.toByte()
    }

    private fun isGif(bytes: ByteArray): Boolean {
        return bytes.size >= 6 &&
                bytes[0] == 0x47.toByte() &&  // G
                bytes[1] == 0x49.toByte() &&  // I
                bytes[2] == 0x46.toByte() &&  // F
                bytes[3] == 0x38.toByte() &&  // 8
                bytes[5] == 0x61.toByte() &&  // a
                (bytes[4] == 0x37.toByte() || bytes[4] == 0x39.toByte())  // 7 or 9
    }

    private fun isWebP(bytes: ByteArray): Boolean {
        return bytes.size >= 12 &&
                bytes[0] == 0x52.toByte() &&  // R
                bytes[1] == 0x49.toByte() &&  // I
                bytes[2] == 0x46.toByte() &&  // F
                bytes[3] == 0x46.toByte() &&  // F
                bytes[8] == 0x57.toByte() &&  // W
                bytes[9] == 0x45.toByte() &&  // E
                bytes[10] == 0x42.toByte() &&  // B
                bytes[11] == 0x50.toByte()     // P
    }
}