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

    /**
    * Stream chat completions using Pollinations API
    */
    fun streamChat(
        prompt: String,
        textModel: TextModel,
        conversationHistory: List<ChatMessage> = emptyList(),
        systemPrompt: String? = null
    ): Flow<String> {
        // Build messages list
        val messages = buildList {
            // Add system prompt if provided
            systemPrompt?.let {
                add(ChatMessage(role = "system", content = it))
            }

            // Add conversation history
            addAll(conversationHistory)

            // Add current user message
            add(ChatMessage(role = "user", content = prompt))
        }

        val request = ChatCompletionRequest(
            model = textModel.modelName,
            messages = messages,
            stream = true,
            maxTokens = 2000,
            temperature = 0.7
        )

        return aiApi.streamChatCompletions(request)
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