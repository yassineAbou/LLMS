package org.yassineabou.llms.app.core.data.remote.ai

import io.ktor.util.encodeBase64
import kotlinx.coroutines.flow.Flow
import kotlinx.io.IOException
import org.yassineabou.llms.app.core.util.ImageMetadataUtil
import org.yassineabou.llms.feature.chat.data.model.ChatCompletionRequest
import org.yassineabou.llms.feature.chat.data.model.ChatMessage
import org.yassineabou.llms.feature.chat.data.model.TextModel
import org.yassineabou.llms.feature.imagine.data.model.GeneratedImageResult
import org.yassineabou.llms.feature.imagine.data.model.PollinationsImageRequest
import org.yassineabou.llms.feature.imagine.data.model.UrlExample

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
        request: PollinationsImageRequest // The function now accepts the new data class
    ): Result<GeneratedImageResult> {
        return try {
            // The API call is now much cleaner
            val imageBytes = aiApi.generateImage(request)

            // The rest of the logic remains the same
            val mimeType = ImageMetadataUtil.detectImageMimeType(imageBytes)
                ?: throw IOException("Invalid image data: Unrecognized format")

            val base64Image = imageBytes.encodeBase64()
            val dataUrl = "data:$mimeType;base64,$base64Image"

            Result.success(
                GeneratedImageResult(
                    // Use the prompt from the original request object
                    urlExample = UrlExample(url = dataUrl, prompt = request.prompt),
                    imageBytes = imageBytes
                )
            )
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}