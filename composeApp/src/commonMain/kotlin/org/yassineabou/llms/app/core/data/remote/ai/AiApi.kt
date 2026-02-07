package org.yassineabou.llms.app.core.data.remote.ai

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.utils.io.*
import kotlinx.coroutines.channels.ProducerScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import org.yassineabou.llms.app.core.data.remote.ai.AiEndPoint.STREAM_PREFIX
import org.yassineabou.llms.feature.chat.data.model.ChatCompletionChunk
import org.yassineabou.llms.feature.chat.data.model.ChatCompletionRequest
import org.yassineabou.llms.feature.chat.data.model.ChatCompletionResponse
import org.yassineabou.llms.feature.imagine.data.model.PollinationsImageRequest


interface AiApi {
    suspend fun generateImage(request: PollinationsImageRequest): ByteArray
    fun streamChatCompletions(request: ChatCompletionRequest): Flow<String>

}


class KtorApi(
    private val client: HttpClient,
    private val json: Json
) : AiApi {

    override suspend fun generateImage(request: PollinationsImageRequest): ByteArray {
        val encodedPrompt = request.prompt.encodeURLPathPart()
        val url = AiEndPoint.POLLINATIONS_BASE_URL + encodedPrompt

        return client.get(url) {
            header(HttpHeaders.Authorization, "Bearer ${AiEndPoint.POLLINATIONS_API_KEY}")

            // Required parameters
            parameter("model", request.model)
            parameter("width", request.width)
            parameter("height", request.height)

            // Optional image parameters
            request.seed?.let { parameter("seed", it) }
            if (request.enhance) parameter("enhance", "true")
            request.negativePrompt?.let { parameter("negative_prompt", it) }
            if (request.safe) parameter("safe", "true")
            request.quality?.let { parameter("quality", it) }
            request.image?.let { parameter("image", it) }
            if (request.transparent) parameter("transparent", "true")

            timeout { requestTimeoutMillis = 300_000 }
        }.body()
    }

    override fun streamChatCompletions(request: ChatCompletionRequest): Flow<String> = channelFlow {
        client.preparePost(AiEndPoint.POLLINATIONS_CHAT_URL) {
            header(HttpHeaders.Authorization, "Bearer ${AiEndPoint.POLLINATIONS_API_KEY}")
            contentType(ContentType.Application.Json)
            accept(ContentType.Text.EventStream)
            setBody(request)
            timeout {
                requestTimeoutMillis = 120_000
            }
        }.execute { response ->
            if (!response.status.isSuccess()) {
                val errorBody = response.bodyAsText()
                throw Exception("API request failed: ${response.status} - $errorBody")
            }

            val channel = response.bodyAsChannel()

            while (!channel.isClosedForRead) {
                val line = channel.readLine() ?: break

                if (line.isBlank() || line.startsWith(":")) continue

                if (line.startsWith(STREAM_PREFIX)) {
                    val dataContent = line.removePrefix(STREAM_PREFIX).trim()

                    if (dataContent == "[DONE]" || dataContent.isEmpty()) break

                    processAndSend(dataContent)
                } else if (line.trim().startsWith("{")) {
                    processAndSend(line.trim())
                }
            }
        }
    }

    private suspend fun ProducerScope<String>.processAndSend(dataJson: String) {
        if (dataJson.equals("[DONE]", ignoreCase = true)) return

        try {
            val chunk = json.decodeFromString<ChatCompletionChunk>(dataJson)

            chunk.choices.forEach { choice ->
                choice.delta?.content?.let { content ->
                    val cleaned = cleanContent(content)
                    if (cleaned.isNotBlank()) send(cleaned)
                }

                choice.message?.content?.let { content ->
                    val cleaned = cleanContent(content)
                    if (cleaned.isNotBlank()) send(cleaned)
                }
            }
        } catch (e: SerializationException) {
            try {
                val response = json.decodeFromString<ChatCompletionResponse>(dataJson)

                response.choices.forEach { choice ->
                    choice.message?.content?.let { content ->
                        val cleaned = cleanContent(content)
                        if (cleaned.isNotBlank()) send(cleaned)
                    }
                }
            } catch (e2: Exception) {
                if (!dataJson.trim().startsWith("{")) {
                    val cleaned = cleanContent(dataJson)
                    if (cleaned.isNotBlank()) send(cleaned)
                }
            }
        }
    }

    private fun cleanContent(rawContent: String): String {
        return rawContent
            .replace(Regex("<reasoning>.*?</reasoning>\\s*"), "")
            .replace(Regex("(<sep>.*|◁.*?▷)"), "")
            .trim()
    }
}