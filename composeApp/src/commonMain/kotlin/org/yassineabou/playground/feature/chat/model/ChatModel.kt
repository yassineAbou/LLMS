package org.yassineabou.playground.feature.chat.model

import llms.composeapp.generated.resources.Res
import llms.composeapp.generated.resources.ic_ai21labs
import llms.composeapp.generated.resources.ic_alibaba_cloud
import llms.composeapp.generated.resources.ic_anthracite_org
import llms.composeapp.generated.resources.ic_cohereforai
import llms.composeapp.generated.resources.ic_deep_seek
import llms.composeapp.generated.resources.ic_google
import llms.composeapp.generated.resources.ic_hugging_face_h4
import llms.composeapp.generated.resources.ic_meta
import llms.composeapp.generated.resources.ic_tii
import org.jetbrains.compose.resources.DrawableResource
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid


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
    val id: String,
    var chatMessages: List<ChatMessage> = emptyList()
)

@OptIn(ExperimentalUuidApi::class)
fun generateFakeThreadData(numThreads: Int): List<ChatHistory> {
    return List(numThreads) { index ->
        ChatHistory(
            title = "Chat ${index + 1}", // Use "Chat" and a sequential number
            description = generateRandomDescription(),
            aiProvider = getRandomAIProvider(),
            id = Uuid.toString()
        )
    }
}

fun generateRandomDescription(): String {
    val sentences = listOf(
        "This is a sample sentence for the description.",
        "Another sentence to add some variety.",
        "A short and concise description.",
        "This is a longer sentence to test line wrapping.",
        "A very long sentence to demonstrate how the text will overflow if not handled correctly."
    )
    return "${sentences.random()}\n${sentences.random()}"
}

val aiProvidersMap = mapOf(
    "Hugging Face H4" to Res.drawable.ic_hugging_face_h4,
    "Meta" to Res.drawable.ic_meta,
    "Deep Seek" to Res.drawable.ic_deep_seek,
    "Alibaba Cloud" to Res.drawable.ic_alibaba_cloud,
    "CohereForAI" to Res.drawable.ic_cohereforai,
    "Google" to Res.drawable.ic_google,
    "AnthraciteOrg" to Res.drawable.ic_anthracite_org,
    "TII" to Res.drawable.ic_tii,
    "AI21 Lab" to Res.drawable.ic_ai21labs
)

fun getRandomAIProvider(): AIProvider {
    val (name, icon) = aiProvidersMap.entries.random()
    return AIProvider(name, icon)
}

val generatedChatHistoryList = generateFakeThreadData(10)

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