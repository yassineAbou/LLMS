package org.yassineabou.llms.feature.chat.data.model

import llms.composeapp.generated.resources.*

object TextGenModelList {

    val allModels: List<TextModel> by lazy {
        otherModels + openaiModels + mistralModels + deepseekModels + llamaModels
    }

    // Default fallback model — choose one that exists in the JSON
    val defaultModel: TextModel by lazy {
        openaiModels.firstOrNull { it.modelName == "gpt-5-nano-2025-08-07" }
            ?: allModels.first() // fallback to first if not found
    }

    // DeepSeek Models
    val deepseekModels = listOf(
        TextModel(
            title = "DeepSeek V3.1",
            provider = "llm7",
            modelName = "deepseek-v3.1",
            description = "Latest DeepSeek V3.1 model for general-purpose tasks.",
            image = Res.drawable.ic_deepseek
        ),
        TextModel(
            title = "DeepSeek Reasoning",
            provider = "llm7",
            modelName = "deepseek-reasoning",
            description = "DeepSeek model specialized for advanced reasoning tasks.",
            image = Res.drawable.ic_deepseek
        )
    )

    // OpenAI Models (now includes GPT-5 variants)
    val openaiModels = listOf(
        TextModel(
            title = "GPT-5 Nano",
            provider = "llm7",
            modelName = "gpt-5-nano-2025-08-07",
            description = "Ultra-lightweight GPT-5 Nano model for fast inference.",
            image = Res.drawable.ic_openai
        ),
        TextModel(
            title = "GPT-5 Mini",
            provider = "llm7",
            modelName = "gpt-5-mini",
            description = "Compact and efficient GPT-5 variant with multimodal support.",
            image = Res.drawable.ic_openai
        ),
        TextModel(
            title = "GPT-5 Chat",
            provider = "llm7",
            modelName = "gpt-5-chat",
            description = "GPT-5 optimized for conversational and chat-based interactions.",
            image = Res.drawable.ic_openai
        ),
        TextModel(
            title = "GPT-O4 Mini",
            provider = "llm7",
            modelName = "gpt-o4-mini-2025-04-16",
            description = "Optimized GPT-O4 Mini model for common tasks with good performance and efficiency.",
            image = Res.drawable.ic_openai
        )
    )

    // Mistral Models
    val mistralModels = listOf(
        TextModel(
            title = "Mistral Small 3.1 24B Instruct",
            provider = "llm7",
            modelName = "mistral-small-3.1-24b-instruct-2503",
            description = "Instruction-tuned Mistral Small 3.1 24B.",
            image = Res.drawable.ic_mistral
        ),
        TextModel(
            title = "Codestral 2405",
            provider = "mistral",
            modelName = "codestral-2405",
            description = "Mistral's coding-focused model.",
            image = Res.drawable.ic_mistral
        ),
        TextModel(
            title = "Codestral 2501",
            provider = "mistral",
            modelName = "codestral-2501",
            description = "Updated Codestral model for coding tasks.",
            image = Res.drawable.ic_mistral
        )
    )

    // Llama Models (L3 variants)
    val llamaModels = listOf(
        TextModel(
            title = "L3.3 MS Nevoria 70B",
            provider = "llm7",
            modelName = "l3.3-ms-nevoria-70b",
            description = "Large 70B parameter model optimized for creative and complex tasks.",
            image = Res.drawable.ic_llama
        ),
        TextModel(
            title = "L3 70B Euryale v2.1",
            provider = "llm7",
            modelName = "l3-70b-euryale-v2.1",
            description = "Enhanced 70B model with improved reasoning and conversation abilities.",
            image = Res.drawable.ic_llama
        ),
        TextModel(
            title = "L3 8B Stheno v3.2",
            provider = "llm7",
            modelName = "l3-8b-stheno-v3.2",
            description = "Compact 8B model optimized for efficient deployment with good performance.",
            image = Res.drawable.ic_llama
        )
    )

    // Other/General models
    val otherModels = listOf(
        TextModel(
            title = "Gemini 2.5 Flash Lite",
            provider = "llm7",
            modelName = "gemini-2.5-flash-lite",
            description = "Google's lightweight and fast multimodal model for responsive applications.",
            image = Res.drawable.ic_gemini
        ),
        TextModel(
            title = "GLM 4.5 Flash",
            provider = "glm",
            modelName = "glm-4.5-flash",
            description = "GLM's fast multimodal model.",
            image = Res.drawable.ic_glm
        ),
        TextModel(
            title = "Gemini Search",
            provider = "llm7",
            modelName = "gemini-search",
            description = "Google's multimodal model optimized for search and information retrieval.",
            image = Res.drawable.ic_gemini
        ),
        TextModel(
            title = "Gemma 2 2B IT",
            provider = "llm7",
            modelName = "gemma-2-2b-it",
            description = "Google's compact 2B instruction-tuned model for efficient deployment.",
            image = Res.drawable.ic_gemini
        ),
        TextModel(
            title = "Qwen2.5 Coder 32B Instruct",
            provider = "llm7",
            modelName = "qwen2.5-coder-32b-instruct",
            description = "Alibaba's Qwen2.5 Coder model with 32B parameters specialized for programming tasks.",
            image = Res.drawable.ic_qwen
        )
    )
}