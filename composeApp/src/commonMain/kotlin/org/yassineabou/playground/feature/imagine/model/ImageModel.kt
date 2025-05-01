package org.yassineabou.playground.feature.imagine.model

import androidx.compose.runtime.Immutable
import org.jetbrains.compose.resources.DrawableResource
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Immutable
data class ImageModel @OptIn(ExperimentalUuidApi::class) constructor(
    val id: String = Uuid.random().toString(),
    val urlExamples: List<UrlExample>,
    val title: String,
    val description: String,
    val isNsfw: Boolean = false,
    val image: DrawableResource,
)