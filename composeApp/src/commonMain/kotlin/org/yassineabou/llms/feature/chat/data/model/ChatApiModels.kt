package org.yassineabou.llms.feature.chat.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ChatMessage(
    val role: String,
    val content: String,
    val name: String? = null,
    @SerialName("cache_control")
    val cacheControl: CacheControl? = null
)

@Serializable
data class CacheControl(
    val type: String = "ephemeral"
)

@Serializable
data class ChatCompletionRequest(
    val model: String,
    val messages: List<ChatMessage>,
    val stream: Boolean = true,
    @SerialName("max_tokens")
    val maxTokens: Int? = 1000,
    val temperature: Double? = 0.7,
    @SerialName("frequency_penalty")
    val frequencyPenalty: Double? = null,
    @SerialName("presence_penalty")
    val presencePenalty: Double? = null,
    val seed: Int? = null,
    @SerialName("top_p")
    val topP: Double? = null
)

@Serializable
data class ChatCompletionChunk(
    val id: String? = null,
    @SerialName("object")
    val objectType: String? = null,
    val created: Long? = null,
    val model: String? = null,
    val choices: List<StreamChoice> = emptyList(),
    val usage: UsageInfo? = null,
    @SerialName("system_fingerprint")
    val systemFingerprint: String? = null
)

@Serializable
data class StreamChoice(
    val index: Int? = null,
    @SerialName("delta")
    val delta: DeltaMessage? = null,
    val message: ChatMessage? = null,
    val logprobs: LogProbs? = null,
    @SerialName("finish_reason")
    val finishReason: String? = null
)

@Serializable
data class DeltaMessage(
    val role: String? = null,
    val content: String? = null,
    @SerialName("reasoning_content")
    val reasoningContent: String? = null,
    @SerialName("tool_calls")
    val toolCalls: List<ToolCall>? = null
)

@Serializable
data class ToolCall(
    val id: String? = null,
    val type: String? = "function",
    val function: FunctionCall? = null
)

@Serializable
data class FunctionCall(
    val name: String? = null,
    val arguments: String? = null
)

@Serializable
data class LogProbs(
    val content: List<TokenLogProb>? = null
)

@Serializable
data class TokenLogProb(
    val token: String,
    val logprob: Double,
    val bytes: List<Int>? = null,
    @SerialName("top_logprobs")
    val topLogprobs: List<TopLogProb>? = null
)

@Serializable
data class TopLogProb(
    val token: String,
    val logprob: Double,
    val bytes: List<Int>? = null
)

@Serializable
data class UsageInfo(
    @SerialName("prompt_tokens")
    val promptTokens: Int? = null,
    @SerialName("total_tokens")
    val totalTokens: Int? = null,
    @SerialName("completion_tokens")
    val completionTokens: Int? = null,
    @SerialName("prompt_tokens_details")
    val promptTokensDetails: PromptTokensDetails? = null,
    @SerialName("completion_tokens_details")
    val completionTokensDetails: CompletionTokensDetails? = null
)

@Serializable
data class PromptTokensDetails(
    @SerialName("audio_tokens")
    val audioTokens: Int? = null,
    @SerialName("cached_tokens")
    val cachedTokens: Int? = null
)

@Serializable
data class CompletionTokensDetails(
    @SerialName("accepted_prediction_tokens")
    val acceptedPredictionTokens: Int? = null,
    @SerialName("audio_tokens")
    val audioTokens: Int? = null,
    @SerialName("reasoning_tokens")
    val reasoningTokens: Int? = null,
    @SerialName("rejected_prediction_tokens")
    val rejectedPredictionTokens: Int? = null
)

@Serializable
data class ChatCompletionResponse(
    val id: String? = null,
    @SerialName("object")
    val objectType: String? = null,
    val created: Long? = null,
    val model: String? = null,
    val choices: List<CompletionChoice> = emptyList(),
    val usage: UsageInfo? = null,
    @SerialName("system_fingerprint")
    val systemFingerprint: String? = null,
    @SerialName("user_tier")
    val userTier: String? = null,
    val citations: List<String>? = null
)

@Serializable
data class CompletionChoice(
    val index: Int? = null,
    val message: ChatMessage? = null,
    val delta: DeltaMessage? = null,
    @SerialName("finish_reason")
    val finishReason: String? = null,
    val logprobs: LogProbs? = null
)
