package org.yassineabou.llms.feature.chat.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TextModelResponse(
    val name: String,
    val aliases: List<String> = emptyList(),
    val pricing: PricingInfo? = null,
    val description: String,
    @SerialName("input_modalities")
    val inputModalities: List<String> = emptyList(),
    @SerialName("output_modalities")
    val outputModalities: List<String> = emptyList(),
    val tools: Boolean = false,
    @SerialName("is_specialized")
    val isSpecialized: Boolean = false,
    @SerialName("paid_only")
    val paidOnly: Boolean = false,
    val reasoning: Boolean = false
)

@Serializable
data class PricingInfo(
    val currency: String? = null,
    val promptTextTokens: Double? = null,
    val promptCachedTokens: Double? = null,
    val completionTextTokens: Double? = null,
    val promptAudioTokens: Double? = null,
    val completionAudioTokens: Double? = null
)