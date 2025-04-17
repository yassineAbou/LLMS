package org.yassineabou.playground.feature.chat.data.network

import kotlinx.coroutines.flow.Flow
import org.yassineabou.playground.feature.chat.data.model.ChatCompletionRequest
import org.yassineabou.playground.feature.chat.data.model.ChatMessage

class ChutesAiRepository(private val chutesAiApi: ChutesAiApi) {

    // Function now returns a Flow<String>
    suspend fun streamChat(
        apiKey: String,
        prompt: String, // Keep simple prompt input for ease of use
        model: String, // Pass the model identifier
        // Or accept a list of ChatMessage for more complex conversations
        // messages: List<ChatMessage>,
        maxTokens: Int? = 1024,
        temperature: Double? = 0.7
    ): Flow<String> {
        // Create the request object
        // If using simple prompt, create the initial message list
        val messages = listOf(ChatMessage(role = "user", content = prompt))

        val request = ChatCompletionRequest(
            model = model,
            messages = messages,
            stream = true, // Ensure streaming is enabled
            maxTokens = maxTokens,
            temperature = temperature
            // Add any other parameters you need
        )

        // Return the Flow directly from the API call
        // Error handling can be done here or downstream when collecting the flow
        return chutesAiApi.streamChatCompletions(apiKey, request)

        // No polling needed anymore!
    }

}