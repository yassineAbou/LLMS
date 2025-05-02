package org.yassineabou.playground.feature.imagine.model

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

