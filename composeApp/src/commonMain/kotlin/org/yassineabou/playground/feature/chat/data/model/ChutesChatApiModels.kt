package org.yassineabou.playground.feature.chat.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChatMessage(
    val role: String,
    val content: String
)

@Serializable
data class ChatCompletionRequest(
    val model: String,
    val messages: List<ChatMessage>,
    val stream: Boolean = true,
    @SerialName("max_tokens")
    val maxTokens: Int? = 1000,
    val temperature: Double? = 0.5
)

@Serializable
data class ChatCompletionChunk(
    val id: String,
    @SerialName("object")
    val objectOne: String,
    val created: Long,
    val model: String,
    val choices: List<StreamChoice>,
    val usage: UsageInfo? = null
)

@Serializable
data class StreamChoice(
    val index: Int,
    @SerialName("message")
    val delta: DeltaMessage,
    val logprobs: Nothing? = null,
    @SerialName("finish_reason")
    val finishReason: String? = null,
    @SerialName("matched_stop")
    val matchedStop: Int? = null
)

@Serializable
data class DeltaMessage(
    val role: String? = null,
    val content: String? = null,
    @SerialName("reasoning_content")
    val reasoningContent: String? = null,
    @SerialName("tool_calls")
    val toolCalls: List<String>? = null
)

@Serializable
data class UsageInfo(
    @SerialName("prompt_tokens")
    val promptTokens: Int,
    @SerialName("total_tokens")
    val totalTokens: Int,
    @SerialName("completion_tokens")
    val completionTokens: Int?,
    @SerialName("prompt_tokens_details")
    val promptTokensDetails: Nothing? = null
)