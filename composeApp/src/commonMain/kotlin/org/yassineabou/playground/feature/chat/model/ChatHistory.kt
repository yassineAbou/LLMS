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

data class ChatHistory(
    val title: String,
    val description: String,
    val aiProvider: AIProvider,
    var isBookmarked: Boolean = false,
    val id: String
)

@OptIn(ExperimentalUuidApi::class)
fun generateFakeThreadData(numThreads: Int): List<ChatHistory> {
    return List(numThreads) {
        ChatHistory(
            title = "Thread ${Uuid.random().toString().substring(0, 8)}",
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