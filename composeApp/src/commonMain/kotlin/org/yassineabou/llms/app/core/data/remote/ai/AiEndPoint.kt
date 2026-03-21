package org.yassineabou.llms.app.core.data.remote.ai

object AiEndPoint {

    // Text Generation - Pollinations API

    const val POLLINATIONS_TEXT_MODELS_URL = "https://gen.pollinations.ai/text/models"
    const val POLLINATIONS_CHAT_URL = "https://gen.pollinations.ai/v1/chat/completions"
    const val STREAM_PREFIX = "data: "


    // Image Generation - Pollinations API
    const val POLLINATIONS_IMAGE_URL = "https://gen.pollinations.ai/image/"

    const val POLLINATIONS_IMAGE_MODELS_URL = "https://gen.pollinations.ai/image/models"

    // ═══════════════════════════════════════════════════════════════
    // 🌱 SIGN UP FOR YOUR FREE API KEY AT: https://enter.pollinations.ai
    // ═══════════════════════════════════════════════════════════════
    // Tier System:
    // 🦠 Spore  →  1 pollen/day  (Free signup)
    // 🌱 Seed   →  3 pollen/day  (Auto-upgrade)
    // 🌸 Flower → 10 pollen/day  (Publish an app)
    // 🍯 Nectar → 20 pollen/day  (Coming soon)
    // ═══════════════════════════════════════════════════════════════
    const val POLLINATIONS_API_KEY = "sk_5ww7ciDeA6m4X2Xjvi61YoTo2lVnh4Y3"
}