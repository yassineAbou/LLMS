package org.yassineabou.llms.app.core.data.remote

import co.touchlab.kermit.Logger
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.utils.io.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import org.yassineabou.llms.app.core.data.remote.AiEndPoint.STREAM_PREFIX
import org.yassineabou.llms.feature.chat.data.model.ChatCompletionChunk
import org.yassineabou.llms.feature.chat.data.model.ChatCompletionRequest
import org.yassineabou.llms.feature.imagine.model.ImageRouterGenerationRequest
import org.yassineabou.llms.feature.imagine.model.ImageRouterGenerationResponse


interface AiApi {
    suspend fun generateImage(
        apiKey: String,
        endpoint: String,
        request: ImageRouterGenerationRequest
    ): ImageRouterGenerationResponse
    fun streamChatCompletions(baseUrl: String, apiKey: String, request: ChatCompletionRequest): Flow<String>
}

class KtorApi(
    private val client: HttpClient,
    private val json: Json
) : AiApi {

    override suspend fun generateImage(
        apiKey: String,
        endpoint: String,
        request: ImageRouterGenerationRequest
    ): ImageRouterGenerationResponse {
        return client.post(endpoint) {
            header(HttpHeaders.Authorization, "Bearer $apiKey")
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()
    }

    override fun streamChatCompletions(baseUrl: String, apiKey: String, request: ChatCompletionRequest): Flow<String> = flow {
        try {
            client.preparePost(baseUrl) {
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
                val rawJsonBuffer = StringBuilder() // Buffer for non-SSE JSON

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
                            // This is not a standard SSE line.
                            // Assume it's part of a raw JSON object at the end.
                            if (line.isNotBlank()) {
                                rawJsonBuffer.append(line.trim())
                            }
                        }
                    }
                }

                // After the loop, process any remaining data in the buffers.
                if (eventBuffer.isNotEmpty()) {
                    processChunk(eventBuffer.toString())
                }
                if (rawJsonBuffer.isNotEmpty()) {
                    processChunk(rawJsonBuffer.toString())
                }
            }
        } catch (e: Exception) {
            //Logger.e { "Global exception during streaming" }
            throw e // Re-throw after logging [[3]]
        }
    }

    private suspend fun FlowCollector<String>.processChunk(dataJson: String) {
        // Handle the [DONE] signal which some APIs send.
        if (dataJson.equals("[DONE]", ignoreCase = true)) {
            Logger.e { "Stream completed with [DONE] signal." }
            return
        }

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
                    //Logger.e { "Stream completed with reason: ${choice.finishReason}" }
                    // Note: The original code had a 'return' here. I've removed it
                    // to allow processing of a final message that might contain both content and a finish_reason.
                }
            }
        } catch (e: SerializationException) {
            // With the stream parsing fix, this log should no longer show partial JSON.
            // It will now show the full JSON if parsing still fails for other reasons.
            //Logger.e { "Serialization error parsing chunk: $dataJson" }
        } catch (e: Exception) {
            //Logger.e { "Unexpected error processing chunk" }
        }
    }
}