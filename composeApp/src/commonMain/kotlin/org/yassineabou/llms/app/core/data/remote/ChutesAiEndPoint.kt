package org.yassineabou.llms.app.core.data.remote

import org.intellij.markdown.html.urlEncode
import org.yassineabou.llms.app.core.util.PlatformConfig
import org.yassineabou.llms.app.core.util.isWasm

object ChutesAiEndPoint {
    const val CHAT_BASE_URL = "https://llm.chutes.ai/v1/chat/completions"
    const val STREAM_PREFIX = "data: "
    const val IMAGE_BASE_URL = "https://chutes-"
    const val CORS_PROXY_URL = "https://corsmirror.com/v1?url= "
    val API_KEY = ""

    fun getImageEndpoint(modelName: String): String {
        val originalUrl = "${IMAGE_BASE_URL}${modelName}.chutes.ai/generate"
        return if (PlatformConfig.isWasm()) {
            "$CORS_PROXY_URL${urlEncode(originalUrl)}"
        } else {
            originalUrl
        }
    }
}