package org.yassineabou.llms.app.core.data.remote.ai

import kotlinx.coroutines.flow.Flow
import kotlinx.io.IOException
import org.yassineabou.llms.app.core.util.ImageMetadataUtil
import org.yassineabou.llms.app.core.util.TextModelMapper
import org.yassineabou.llms.feature.chat.data.model.ChatCompletionRequest
import org.yassineabou.llms.feature.chat.data.model.ChatMessage
import org.yassineabou.llms.feature.chat.data.model.TextModel
import org.yassineabou.llms.feature.imagine.data.model.GeneratedImageResult
import org.yassineabou.llms.feature.imagine.data.model.PollinationsImageRequest
import org.yassineabou.llms.feature.imagine.data.model.UrlExample
import kotlin.io.encoding.Base64

class AiRepository(private val aiApi: AiApi) {

    fun streamChat(
        prompt: String,
        textModel: TextModel,
        conversationHistory: List<ChatMessage> = emptyList(),
        systemPrompt: String? = null
    ): Flow<String> {
        val messages = buildList {
            systemPrompt?.let {
                add(ChatMessage(role = "system", content = it))
            }

            addAll(conversationHistory)

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
        request: PollinationsImageRequest
    ): Result<GeneratedImageResult> {
        return try {
            val imageBytes = aiApi.generateImage(request)

            val mimeType = ImageMetadataUtil.detectImageMimeType(imageBytes)
                ?: throw IOException("Invalid image data: Unrecognized format")

            val base64Image = Base64.encode(imageBytes)
            val dataUrl = "data:$mimeType;base64,$base64Image"

            Result.success(
                GeneratedImageResult(
                    urlExample = UrlExample(url = dataUrl, prompt = request.prompt),
                    imageBytes = imageBytes
                )
            )
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getTextModels(): Result<List<TextModel>> {
        return try {
            val response = aiApi.getTextModels()
            val models = TextModelMapper.mapToTextModels(response)
            Result.success(models)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}