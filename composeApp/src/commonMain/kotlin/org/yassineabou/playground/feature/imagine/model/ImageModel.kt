package org.yassineabou.playground.feature.imagine.model

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

data class ImageModel @OptIn(ExperimentalUuidApi::class) constructor(
    val id: String = Uuid.random().toString(),
    val urlExamples: List<UrlExample>,
    val title: String,
    val description: String,
    val isNsfw: Boolean = false,
)