package org.yassineabou.playground.app.core.data

object ChutesAiEndPoint {
    const val CHAT_BASE_URL = "https://llm.chutes.ai/v1/chat/completions"
    const val STREAM_PREFIX = "data: "
    const val IMAGE_BASE_URL = "https://chutes-"
    val API_KEY = ""

    fun getImageEndpoint(modelName: String): String {
        return "${IMAGE_BASE_URL}${modelName}.chutes.ai/generate"
    }
}