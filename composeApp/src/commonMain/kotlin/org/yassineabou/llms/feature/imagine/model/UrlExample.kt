package org.yassineabou.llms.feature.imagine.model

import androidx.compose.runtime.Immutable
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

class IdGenerator {

    private var nextId = 0

    fun generatedId(): Int {
        nextId++
        return nextId
    }

}

@Immutable
data class UrlExample @OptIn(ExperimentalUuidApi::class) constructor(
    val id: String = Uuid.random().toString(),
    val url: String = "",
    val prompt: String = ""
)

data class GeneratedImageResult(
    val urlExample: UrlExample,
    val imageBytes: ByteArray
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as GeneratedImageResult

        if (urlExample != other.urlExample) return false
        if (!imageBytes.contentEquals(other.imageBytes)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = urlExample.hashCode()
        result = 31 * result + imageBytes.contentHashCode()
        return result
    }
}


