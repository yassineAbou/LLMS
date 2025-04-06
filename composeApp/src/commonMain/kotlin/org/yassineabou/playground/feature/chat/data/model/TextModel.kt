package org.yassineabou.playground.feature.chat.data.model

import org.jetbrains.compose.resources.DrawableResource
import org.yassineabou.playground.feature.imagine.model.IdGenerator


data class TextModel(
    val id: Int = IdGenerator().generatedId(),
    val title: String,
    val image: DrawableResource,
    val description: String,
    val provider: String
)

