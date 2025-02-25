package org.yassineabou.playground.feature.chat.model

import llms.composeapp.generated.resources.Res
import llms.composeapp.generated.resources.ic_alibaba_cloud
import llms.composeapp.generated.resources.ic_deep_seek
import llms.composeapp.generated.resources.ic_meta
import org.jetbrains.compose.resources.DrawableResource


data class AIProvider(
    val name: String,
    val icon: DrawableResource
)

data class ChatMessage(
    val message: String,
    val isUser: Boolean
)

data class ChatHistory(
    val title: String,
    var description: String,
    val aiProvider: AIProvider,
    var isBookmarked: Boolean = false,
    var id: String,
    var chatMessages: List<ChatMessage> = emptyList()
)

val aiProvidersMap = mapOf(
    "Deep Seek" to Res.drawable.ic_deep_seek,
    "Alibaba Cloud" to Res.drawable.ic_alibaba_cloud,
    "Meta" to Res.drawable.ic_meta,
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