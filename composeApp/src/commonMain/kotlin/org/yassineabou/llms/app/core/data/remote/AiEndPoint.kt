package org.yassineabou.llms.app.core.data.remote

object AiEndPoint {
    const val TOGETHER_BASE_URL = "https://api.together.xyz/v1/chat/completions"
    const val GEMINI_BASE_URL = "https://generativelanguage.googleapis.com/v1beta/chat/completions"
    const val STREAM_PREFIX = "data: "
    const val IMAGE_ROUTER_URL = "https://api.imagerouter.io/v1/openai/images/generations"

    const val TOGETHER_API_KEY = "98481aab9db09618ad52986d8e4bcabac6ae94f7efeb290cd4cd681488753872"
    const val GEMINI_API_KEY = "AIzaSyAqjI1uttWF5n3N-S40ca9T9OaTcGSxAl8"
    const val IMAGE_ROUTER_API_KEY = "74c3d825a7123e6e4f3eae1d6138becf395918c4008cbe3bc7f542b59dec5a5b"
}