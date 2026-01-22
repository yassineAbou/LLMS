package org.yassineabou.llms.app.core.data.remote.ai

object AiEndPoint {

    // Text Generation - Pollinations API
    const val POLLINATIONS_TEXT_URL = "https://gen.pollinations.ai/v1/chat/completions"
    const val STREAM_PREFIX = "data: "

    // Image Generation - Updated to new API
    const val POLLINATIONS_BASE_URL = "https://gen.pollinations.ai/image/"
    const val POLLINATIONS_API_KEY = "sk_GXXF93rGhMEHICwRCCSaOGtB6kR15loC"
}