package org.yassineabou.llms.feature.imagine.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ImageModelResponse(
    val name: String,
    val aliases: List<String> = emptyList(),
    val pricing: ImagePricingInfo? = null,
    val description: String,
    @SerialName("input_modalities")
    val inputModalities: List<String> = emptyList(),
    @SerialName("output_modalities")
    val outputModalities: List<String> = emptyList(),
    @SerialName("paid_only")
    val paidOnly: Boolean = false
)

@Serializable
data class ImagePricingInfo(
    val currency: String? = null,
    val promptTextTokens: Double? = null,
    val promptCachedTokens: Double? = null,
    val promptImageTokens: Double? = null,
    val completionImageTokens: Double? = null,
    val completionVideoTokens: Double? = null,
    val completionVideoSeconds: Double? = null,
    val completionAudioSeconds: Double? = null
)