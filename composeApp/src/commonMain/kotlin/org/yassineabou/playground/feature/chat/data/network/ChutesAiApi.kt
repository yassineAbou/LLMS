package org.yassineabou.playground.feature.chat.data.network

import co.touchlab.kermit.Logger
import io.ktor.client.HttpClient
import io.ktor.client.request.accept
import io.ktor.client.request.header
import io.ktor.client.request.preparePost
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsChannel
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import io.ktor.utils.io.readUTF8Line
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import org.yassineabou.playground.feature.chat.data.model.ChatCompletionChunk
import org.yassineabou.playground.feature.chat.data.model.ChatCompletionRequest


interface ChutesAiApi {
    fun streamChatCompletions(apiKey: String, request: ChatCompletionRequest): Flow<String>
}

class KtorChutesApi(
    private val client: HttpClient,
    private val json: Json
) : ChutesAiApi {

    companion object {
        private const val CHUTES_API_URL = "https://llm.chutes.ai/v1/chat/completions"
        private const val STREAM_PREFIX = "data: "
    }

    override fun streamChatCompletions(apiKey: String, request: ChatCompletionRequest): Flow<String> = flow {
        try {
            client.preparePost(CHUTES_API_URL) {
                header(HttpHeaders.Authorization, "Bearer $apiKey")
                contentType(ContentType.Application.Json)
                accept(ContentType.Text.EventStream)
                setBody(request)
            }.execute { response ->
                if (!response.status.isSuccess()) {
                    val errorBody = response.bodyAsText()
                    Logger.e {("API request failed: ${response.status} - $errorBody") }
                    throw Exception("API request failed: ${response.status} - $errorBody")
                }

                val channel = response.bodyAsChannel()
                val eventBuffer = StringBuilder()

                while (!channel.isClosedForRead) {
                    val line = channel.readUTF8Line() ?: break

                    when {
                        line.startsWith(STREAM_PREFIX) -> {
                            eventBuffer.append(line.removePrefix(STREAM_PREFIX).trim())
                        }
                        line.isBlank() -> {
                            if (eventBuffer.isNotEmpty()) {
                                processChunk(eventBuffer.toString())
                                eventBuffer.clear()
                            }
                        }
                        line.startsWith(":") -> Unit // Ignore comments
                        else -> {
                            try {
                                // Handle cases where server sends raw JSON without SSE formatting [[7]]
                                processChunk(line)
                            } catch (e: Exception) {
                                Logger.e  { "Failed to parse unexpected line format: $line" }
                            }
                        }
                    }
                }
            }
        } catch (e: Exception) {
           Logger.e { "Global exception during streaming" }
            throw e // Re-throw after logging [[3]]
        }
    }

    private suspend fun FlowCollector<String>.processChunk(dataJson: String) {
        try {
            val chunk = json.decodeFromString<ChatCompletionChunk>(dataJson)
            chunk.choices.forEach { choice ->
                choice.delta.content?.let { rawContent ->
                    val cleanedContent = rawContent
                        .replace(Regex("<reasoning>.*?</reasoning>\\s*"), "") // Remove reasoning blocks
                        .replace(Regex("(<sep>.*|◁.*?▷)"), "") // Remove other tags
                        .trim()

                    if (cleanedContent.isNotBlank()) {
                        emit(cleanedContent)
                    }
                }
                if (choice.finishReason != null) {
                    Logger.e { "Stream completed with reason: ${choice.finishReason}" }
                    return
                }
            }
        } catch (e: SerializationException) {
            Logger.e { "Serialization error parsing chunk: $dataJson" }
        } catch (e: Exception) {
            Logger.e { "Unexpected error processing chunk" }
        }
    }
}