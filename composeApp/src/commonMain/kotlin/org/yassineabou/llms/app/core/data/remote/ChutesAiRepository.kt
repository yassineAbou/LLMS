package org.yassineabou.llms.app.core.data.remote

import io.ktor.util.encodeBase64
import kotlinx.coroutines.flow.Flow
import kotlinx.io.IOException
import org.yassineabou.llms.app.core.util.ImageMetadataUtil
import org.yassineabou.llms.feature.chat.data.model.ChatCompletionRequest
import org.yassineabou.llms.feature.chat.data.model.ChatMessage
import org.yassineabou.llms.feature.imagine.model.ImageGenerationRequest
import org.yassineabou.llms.feature.imagine.model.ImageModel
import org.yassineabou.llms.feature.imagine.model.UrlExample

class ChutesAiRepository(private val chutesAiApi: ChutesAiApi) {

    // Function now returns a Flow<String>
    fun streamChat(
        apiKey: String,
        prompt: String, // Keep simple prompt input for ease of use
        model: String, // Pass the model identifier
    ): Flow<String> {
        // Create the request object
        // If using simple prompt, create the initial message list
        val messages = listOf(ChatMessage(role = "user", content = prompt))

        val request = ChatCompletionRequest(
            model = model,
            messages = messages,
            stream = true, // Ensure streaming is enabled
            // Add any other parameters you need
        )

        // Return the Flow directly from the API call
        // Error handling can be done here or downstream when collecting the flow
        return chutesAiApi.streamChatCompletions(apiKey, request)

        // No polling needed anymore!
    }

    suspend fun generateImage(
        apiKey: String,
        model: ImageModel,
        prompt: String,
        additionalParams: Map<String, Any> = emptyMap()
    ): Result<UrlExample> {
        return try {
            val endpoint = ChutesAiEndPoint.getImageEndpoint(model.chutesName)
            val request = createImageRequest(model, prompt, additionalParams)
            val imageBytes = chutesAiApi.generateImage(apiKey, endpoint, request)

            // Detect MIME type and validate image
            val mimeType = ImageMetadataUtil.detectImageMimeType(imageBytes)
                ?: throw IOException("Invalid image data: Unrecognized format")

            val base64Image = imageBytes.encodeBase64()
            val dataUrl = "data:$mimeType;base64,$base64Image"

            Result.success(
                UrlExample(
                    url = dataUrl, // Use the generated URL
                    prompt = prompt
                )
            )

        } catch (e: Exception) {
            Result.failure(e)
        }
    }


    private fun createImageRequest(
        model: ImageModel,
        prompt: String,
        params: Map<String, Any>
    ): ImageGenerationRequest {
        val allParams = model.defaultParams.toMutableMap().apply {
            putAll(params)
        }

        return ImageGenerationRequest(
            prompt = prompt,
            seed = allParams["seed"] as? Int,
            steps = allParams["steps"] as? Int,
            guidance_scale = allParams["guidance_scale"] as? Float,
            cfg = allParams["cfg"] as? Float,
            edit_prompts = when (allParams["edit_prompts"]) {
                is List<*> -> (allParams["edit_prompts"] as List<*>).mapNotNull { it as? String }
                else -> null
            },
            id_image_b64 = allParams["id_image_b64"] as? String,
            control_image_b64 = allParams["control_image_b64"] as? String,
            infusenet_guidance_start = allParams["infusenet_guidance_start"] as? Float,
            infusenet_guidance_end = allParams["infusenet_guidance_end"] as? Float,
            infusenet_conditioning_scale = allParams["infusenet_conditioning_scale"] as? Float
        )
    }
}