package org.yassineabou.llms.app.core.data.remote

import io.ktor.util.*
import kotlinx.coroutines.flow.Flow
import kotlinx.io.IOException
import org.yassineabou.llms.app.core.util.ImageMetadataUtil
import org.yassineabou.llms.feature.chat.data.model.ChatCompletionRequest
import org.yassineabou.llms.feature.chat.data.model.ChatMessage
import org.yassineabou.llms.feature.chat.data.model.TextModel
import org.yassineabou.llms.feature.imagine.model.GeneratedImageResult
import org.yassineabou.llms.feature.imagine.model.ImageModel
import org.yassineabou.llms.feature.imagine.model.ImageRouterGenerationRequest
import org.yassineabou.llms.feature.imagine.model.UrlExample

class AiRepository(private val aiApi: AiApi) {

    // Function now returns a Flow<String>
    fun streamChat(
        prompt: String, // Keep simple prompt input for ease of use
        textModel: TextModel, // Pass the TextModel
    ): Flow<String> {
        // All models use LLM7 endpoint
        val baseUrl = AiEndPoint.LLM7_BASE_URL
        val apiKey = AiEndPoint.LLM7_API_KEY

        // Create messages - simple user message for all models
        val messages = listOf(ChatMessage(role = "user", content = prompt))

        val request = ChatCompletionRequest(
            model = textModel.modelName,
            messages = messages,
            stream = true, // Ensure streaming is enabled
            maxTokens = 1000,
            temperature = 0.5
        )

        // Return the Flow directly from the API call
        return aiApi.streamChatCompletions(baseUrl, apiKey, request)
    }

    suspend fun generateImage(
        apiKey: String,
        model: ImageModel,
        prompt: String,
        additionalParams: Map<String, Any> = emptyMap()
    ): Result<GeneratedImageResult> {
        return try {
            val endpoint = AiEndPoint.IMAGE_ROUTER_URL
            val request = ImageRouterGenerationRequest(
                prompt = prompt,
                model = model.modelName,
                quality = additionalParams["quality"] as? String,
                response_format = "b64_json" // Always get base64 for consistency
            )

            val response = aiApi.generateImage(apiKey, endpoint, request)
            val imageData = response.data.firstOrNull()
                ?: throw IOException("No image data in response")

            val imageBytes = imageData.b64_json?.decodeBase64Bytes()
                ?: throw IOException("Missing base64 image data")

            // Detect MIME type and validate image
            val mimeType = ImageMetadataUtil.detectImageMimeType(imageBytes)
                ?: throw IOException("Invalid image data: Unrecognized format")

            val base64Image = imageBytes.encodeBase64()
            val dataUrl = "data:$mimeType;base64,$base64Image"

            Result.success(
                GeneratedImageResult(
                    urlExample = UrlExample(url = dataUrl, prompt = prompt),
                    imageBytes = imageBytes
                )
            )
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}