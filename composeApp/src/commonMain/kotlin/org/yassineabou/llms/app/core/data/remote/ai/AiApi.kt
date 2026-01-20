package org.yassineabou.llms.app.core.data.remote.ai

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.timeout
import io.ktor.client.request.accept
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.request.preparePost
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsChannel
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.http.encodeURLPathPart
import io.ktor.http.isSuccess
import io.ktor.utils.io.readUTF8Line
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import org.yassineabou.llms.app.core.data.remote.ai.AiEndPoint.STREAM_PREFIX
import org.yassineabou.llms.feature.chat.data.model.ChatCompletionChunk
import org.yassineabou.llms.feature.chat.data.model.ChatCompletionRequest
import org.yassineabou.llms.feature.chat.data.model.ChatCompletionResponse
import org.yassineabou.llms.feature.imagine.data.model.PollinationsImageRequest


interface AiApi {
    suspend fun generateImage(request: PollinationsImageRequest): ByteArray


    fun streamChatCompletions(baseUrl: String, apiKey: String, request: ChatCompletionRequest): Flow<String>
}

class KtorApi(
    private val client: HttpClient,
    private val json: Json
) : AiApi {

    override suspend fun generateImage(request: PollinationsImageRequest): ByteArray {
        val encodedPrompt = request.prompt.encodeURLPathPart()
        val url = AiEndPoint.POLLINATIONS_BASE_URL + encodedPrompt

        return client.get(url) {
            // Authentication via Bearer token
            header(HttpHeaders.Authorization, "Bearer ${AiEndPoint.POLLINATIONS_API_KEY}")

            // Required parameters
            parameter("model", request.model)
            parameter("width", request.width)
            parameter("height", request.height)

            // Optional parameters
            request.seed?.let { parameter("seed", it) }
            if (request.enhance) parameter("enhance", "true")
            request.negativePrompt?.let { parameter("negative_prompt", it) }
            if (request.safe) parameter("safe", "true")
            request.quality?.let { parameter("quality", it) }
            request.image?.let { parameter("image", it) }
            if (request.transparent) parameter("transparent", "true")

            timeout {
                requestTimeoutMillis = 300_000 // 5 minutes
            }
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
                    //Logger.e { "API request failed: ${response.status} - $errorBody" }
                    throw Exception("API request failed: ${response.status} - $errorBody")
                }

                val channel = response.bodyAsChannel()
                val eventBuffer = StringBuilder()
                val rawJsonBuffer = StringBuilder()

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
                            if (line.isNotBlank()) {
                                rawJsonBuffer.append(line.trim())
                            }
                        }
                    }
                }

                if (eventBuffer.isNotEmpty()) {
                    processChunk(eventBuffer.toString())
                }
                if (rawJsonBuffer.isNotEmpty()) {
                    processChunk(rawJsonBuffer.toString())
                }
            }
        } catch (e: Exception) {
            //Logger.e { "Global exception during streaming: ${e.message}" }
            throw e
        }
    }

    private suspend fun FlowCollector<String>.processChunk(dataJson: String) {
        if (dataJson.equals("[DONE]", ignoreCase = true)) {
            return
        }

        try {
            // --- PRIMARY LOGIC: Try to parse as a standard streaming chunk ---
            val chunk = json.decodeFromString<ChatCompletionChunk>(dataJson)
            chunk.choices.forEach { choice ->
                choice.delta.content?.let { content ->
                    // Your cleaning logic is fine, let's keep it
                    val cleanedContent = cleanContent(content)
                    if (cleanedContent.isNotBlank()) {
                        emit(cleanedContent)
                    }
                }
            }
        } catch (e: SerializationException) {
            // --- FALLBACK LOGIC: If it's not a chunk, try parsing as a complete response ---
            try {
                val completeResponse = json.decodeFromString<ChatCompletionResponse>(dataJson)
                completeResponse.choices.forEach { choice ->
                    // This is the raw content from the full response
                    val rawContent = choice.message.content

                    // --- NEW: Add a check here ---
                    // Try to parse the rawContent itself as another ChatCompletionResponse
                    // This handles cases where the model wraps its response in a JSON string.
                    try {
                        val nestedResponse = json.decodeFromString<ChatCompletionResponse>(rawContent)
                        val nestedContent = nestedResponse.choices.firstOrNull()?.message?.content ?: ""
                        val cleanedContent = cleanContent(nestedContent)
                        if (cleanedContent.isNotBlank()) {
                            emit(cleanedContent)
                        }
                    } catch (nestedException: Exception) {
                        // If it's not nested JSON, treat it as plain text. THIS IS THE NORMAL PATH.
                        val cleanedContent = cleanContent(rawContent)
                        if (cleanedContent.isNotBlank()) {
                            emit(cleanedContent)
                        }
                    }
                }
            } catch (e2: Exception) {
                // If both parsing attempts fail, it might be malformed.
                // As a last resort, emit the raw string if it doesn't look like JSON.
                if (!dataJson.trim().startsWith("{")) {
                    val cleanedContent = cleanContent(dataJson)
                    if (cleanedContent.isNotBlank()) {
                        emit(cleanedContent)
                    }
                }
                // Logger.e { "Failed to parse response chunk as stream or complete object: $dataJson" }
            }
        } catch (e: Exception) {
            // Logger.e { "Unexpected error processing chunk: ${e.message}" }
        }
    }

    // Helper function to avoid repetition
    private fun cleanContent(rawContent: String): String {
        return rawContent
            .replace(Regex("<reasoning>.*?</reasoning>\\s*"), "")
            .replace(Regex("(<sep>.*|◁.*?▷)"), "")
            .trim()
    }
}