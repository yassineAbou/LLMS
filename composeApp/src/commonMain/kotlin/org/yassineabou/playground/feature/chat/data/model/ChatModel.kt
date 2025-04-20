package org.yassineabou.playground.feature.chat.data.model

import androidx.compose.ui.text.AnnotatedString
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

data class ChatMessageModel(
    val rawMessage: String,
    val renderedContent: AnnotatedString? = null,
    val isUser: Boolean,
    val renderState: RenderState
) {
    enum class RenderState {
        PENDING,    // Initial state for AI messages
        STREAMING,  // Receiving raw content
        STABLE,     // Final rendered state
        ERROR       // Rendering failed
    }

    val displayContent: AnnotatedString
        get() = when (renderState) {
            RenderState.STABLE -> renderedContent ?: AnnotatedString(rawMessage)
            else -> AnnotatedString(rawMessage)
        }
}

data class ChatHistory @OptIn(ExperimentalUuidApi::class) constructor(
    val title: String,
    var description: String,
    val textModel: TextModel,
    var isBookmarked: Boolean = false,
    var id: String = Uuid.toString(),
    var chatMessages: List<ChatMessageModel> = emptyList()
)

fun generateLongResponse(): String {
    val paragraphs = listOf(
        "This is currently a prototype designed to simulate interactions with an AI chatbot. The data you see here is fake and is being used to demonstrate the user interface and functionality of the system. The next step is to integrate with AI providers' APIs for real-time responses.",
        "The next phase of development involves integrating with AI providers' APIs, such as OpenAI, Hugging Face, or Google Cloud AI. This will enable the system to provide dynamic, context-aware answers based on actual AI models, significantly improving its accuracy and usefulness.",
        "Once integrated with AI providers' APIs, the system will be able to process user queries more effectively. This will allow it to deliver meaningful, context-aware responses, making interactions more natural and engaging for users.",
        "We are planning to introduce advanced features like multi-language support, natural language understanding, and personalized responses. These enhancements will make the chatbot more versatile and tailored to individual user needs.",
        "Scalability and performance are key priorities for us. We are working to ensure the system can handle a large number of users simultaneously while maintaining fast response times and reliable performance.",
        "Your feedback during this prototype phase is incredibly valuable. It will help us refine the system and ensure it meets user expectations as we move closer to launching the full version. Thank you for being part of this journey!"
    )
    return paragraphs.random() // Randomly select one paragraph
}