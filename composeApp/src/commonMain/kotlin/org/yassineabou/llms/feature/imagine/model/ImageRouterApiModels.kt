package org.yassineabou.llms.feature.imagine.model

import kotlinx.serialization.Serializable

@Serializable
data class ImageRouterGenerationRequest(
    val prompt: String,
    val model: String,
    val quality: String? = "auto",
    val response_format: String? = "url"
)

@Serializable
data class ImageRouterGenerationResponse(
    val created: Long,
    val data: List<ImageData>
)

@Serializable
data class ImageData(
    val url: String? = null,
    val b64_json: String? = null
)
