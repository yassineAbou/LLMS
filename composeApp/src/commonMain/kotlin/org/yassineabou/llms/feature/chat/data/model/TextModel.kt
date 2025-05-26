package org.yassineabou.llms.feature.chat.data.model

import org.jetbrains.compose.resources.DrawableResource
import org.yassineabou.llms.feature.imagine.model.IdGenerator


data class TextModel(
    val id: Int = IdGenerator().generatedId(),
    val title: String,
    val chutesName: String,
    val image: DrawableResource,
    val description: String,
)

