package org.yassineabou.llms.feature.chat.data.model

import llms.composeapp.generated.resources.*

object TextGenModelList {

    val allModels: List<TextModel> by lazy {
        openaiModels + mistralModels + otherModels
    }

    // Default fallback model
    val defaultModel: TextModel by lazy {
        otherModels.firstOrNull { it.modelName == "GLM-4.6V-Flash" }
            ?: allModels.first()
    }

    // OpenAI Models (Free Tier)
    val openaiModels = listOf(
        TextModel(
            title = "GPT-5 Mini",
            provider = "llm7",
            modelName = "gpt-5-mini",
            description = "Compact and efficient GPT-5 variant with multimodal support.",
            image = Res.drawable.ic_openai
        ),
        TextModel(
            title = "GPT-4.1 Nano",
            provider = "llm7",
            modelName = "gpt-4.1-nano-2025-04-14",
            description = "Lightweight GPT-4.1 variant for fast, efficient text generation.",
            image = Res.drawable.ic_openai
        ),
        TextModel(
            title = "GPT OSS 20B",
            provider = "p",
            modelName = "gpt-oss-20b",
            description = "Open-source 20B parameter GPT model for text generation.",
            image = Res.drawable.ic_openai
        )
    )

    // Mistral Models (Free Tier)
    val mistralModels = listOf(
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
        ),
        TextModel(
            title = "Ministral 3B",
            provider = "mistral",
            modelName = "ministral-3b-2512",
            description = "Compact 3B model with multimodal capabilities.",
            image = Res.drawable.ic_mistral
        )
    )

    // Other Models (Free Tier)
    val otherModels = listOf(
        TextModel(
            title = "GLM 4.6V Flash",
            provider = "glm",
            modelName = "GLM-4.6V-Flash",
            description = "GLM's vision-enabled multimodal model with enhanced capabilities.",
            image = Res.drawable.ic_glm
        ),
        TextModel(
            title = "GLM 4.5 Flash",
            provider = "glm",
            modelName = "glm-4.5-flash",
            description = "GLM's fast multimodal model.",
            image = Res.drawable.ic_glm
        ),
        TextModel(
            title = "Gemini 2.5 Flash Lite",
            provider = "gemini",
            modelName = "gemini-2.5-flash-lite",
            description = "Lightweight and fast Gemini model with multimodal support.",
            image = Res.drawable.ic_gemini
        ),
        TextModel(
            title = "Llama 3.1 8B Instruct",
            provider = "llm7",
            modelName = "llama-3.1-8B-instruct",
            description = "Compact 8B model optimized for efficient deployment with good performance.",
            image = Res.drawable.ic_llama
        ),
        TextModel(
            title = "Bidara",
            provider = "bidara",
            modelName = "bidara",
            description = "General-purpose text generation model.",
            image = Res.drawable.ic_llm7
        )
    )
}