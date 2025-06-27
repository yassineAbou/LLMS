package org.yassineabou.llms.feature.imagine.model

import androidx.compose.runtime.Immutable
import org.jetbrains.compose.resources.DrawableResource
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Immutable
data class ImageModel @OptIn(ExperimentalUuidApi::class) constructor(
    val id: String = Uuid.random().toString(),
    val title: String,
    val description: String,
    val image: DrawableResource,
    val modelName: String,
    val defaultParams: Map<String, Any> = emptyMap()
)