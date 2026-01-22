package org.yassineabou.llms.feature.chat.data.model

import org.jetbrains.compose.resources.DrawableResource
import org.yassineabou.llms.feature.imagine.data.model.IdGenerator


data class TextModel(
    val id: Int = IdGenerator().generatedId(),
    val title: String,
    val provider: String,
    val modelName: String,
    val image: DrawableResource,
    val description: String,
    val efficiency: String? = null
)


