package org.yassineabou.llms.feature.imagine.data.model

import androidx.compose.runtime.Immutable
import org.jetbrains.compose.resources.DrawableResource
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Immutable
data class ImageModel(
    val id: Int = IdGenerator().generatedId(),
    val title: String,
    val description: String,
    val modelName: String,
    val efficiency: String? = null
)