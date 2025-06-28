package org.yassineabou.llms.app.core.data.remote

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
import org.yassineabou.llms.app.core.data.remote.AiEndPoint.CHUTES_BASE_URL
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
    fun streamChatCompletions(apiKey: String, request: ChatCompletionRequest): Flow<String>
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


    override fun streamChatCompletions(apiKey: String, request: ChatCompletionRequest): Flow<String> = flow {
        try {
            client.preparePost(CHUTES_BASE_URL) {
                header(HttpHeaders.Authorization, "Bearer $apiKey")
                contentType(ContentType.Application.Json)
                accept(ContentType.Text.EventStream)
                setBody(request)
            }.execute { response ->
                if (!response.status.isSuccess()) {
                    val errorBody = response.bodyAsText()
                    //Logger.e {("API request failed: ${response.status} - $errorBody") }
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
                                //Logger.e  { "Failed to parse unexpected line format: $line" }
                            }
                        }
                    }
                }
            }
        } catch (e: Exception) {
           //Logger.e { "Global exception during streaming" }
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
                    //Logger.e { "Stream completed with reason: ${choice.finishReason}" }
                    return
                }
            }
        } catch (e: SerializationException) {
            //Logger.e { "Serialization error parsing chunk: $dataJson" }
        } catch (e: Exception) {
            //Logger.e { "Unexpected error processing chunk" }
        }
    }
}