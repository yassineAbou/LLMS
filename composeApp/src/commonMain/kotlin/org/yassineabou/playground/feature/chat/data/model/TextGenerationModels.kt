package org.yassineabou.playground.feature.chat.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenerationResponse(
    @SerialName("id") val id: String,
    @SerialName("kudos") val kudos: Double,
    @SerialName("message") val message: String? = null,
    @SerialName("warnings") val warnings: List<ApiWarning>? = null
)

// Add this missing class
@Serializable
data class ApiWarning(
    @SerialName("code") val code: String,
    @SerialName("message") val message: String
)

// Rest of your existing classes
@Serializable
data class GenerationRequest(
    @SerialName("prompt") val prompt: String,
    @SerialName("params") val params: GenerationParams = GenerationParams(),
    @SerialName("nsfw") val nsfw: Boolean = false,
    @SerialName("trusted_workers") val trustedWorkers: Boolean = false,
    @SerialName("workers") val workers: List<String>? = null,
    @SerialName("models") val models: List<String>? = null
)

@Serializable
data class GenerationParams(
    @SerialName("max_length") val maxLength: Int = 80,
    @SerialName("temperature") val temperature: Double = 1.0,
    @SerialName("rep_pen") val repetitionPenalty: Double = 1.1,
    @SerialName("top_p") val topP: Double = 0.9,
    @SerialName("top_k") val topK: Int = 50,
    @SerialName("stop_sequence") val stopSequence: List<String>? = null,
    @SerialName("max_context_length") val maxContextLength: Int = 2048
)

@Serializable
data class GenerationStatus(
    @SerialName("done") val done: Boolean,
    @SerialName("faulted") val faulted: Boolean,
    @SerialName("generations") val generations: List<GenerationResult>? = null
)

@Serializable
data class GenerationResult(
    @SerialName("text") val text: String
)

@Serializable
data class ApiError(
    @SerialName("message") val message: String,
    @SerialName("rc") val errorCode: String
)