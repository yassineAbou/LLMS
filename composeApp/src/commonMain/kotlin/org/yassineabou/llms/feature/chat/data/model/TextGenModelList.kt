package org.yassineabou.llms.feature.chat.data.model

import llms.composeapp.generated.resources.*

object TextGenModelList {

    val allModels: List<TextModel> by lazy {
        togetherFreeModels + geminiModels
    }

    // Default fallback model
    val defaultModel: TextModel by lazy {
       geminiModels.first() // Or any default you prefer
    }

    // Together.ai free models
    val togetherFreeModels = listOf(
        TextModel(
            title = "Llama 3.3 70B Instruct Turbo",
            provider = "together",
            modelName = "meta-llama/Llama-3.3-70B-Instruct-Turbo-Free",
            description = "A free turbo version of Llama 3.3 70B optimized for instruction-following tasks with high performance.",
            image = Res.drawable.ic_llama
        ),
        TextModel(
            title = "Exaone 3.5 32B Instruct",
            provider = "together",
            modelName = "lgai/exaone-3-5-32b-instruct",
            description = "A 32B parameter model from LGAI designed for instructive and reasoning tasks.",
            image = Res.drawable.ic_lg_ai
        ),
        TextModel(
            title = "Exaone Deep 32B",
            provider = "together",
            modelName = "lgai/exaone-deep-32b",
            description = "Deep version of Exaone 32B for advanced language understanding and generation.",
            image = Res.drawable.ic_lg_ai
        ),
        TextModel(
            title = "DeepSeek R1 Distill Llama 70B Free",
            provider = "together",
            modelName = "deepseek-ai/DeepSeek-R1-Distill-Llama-70B-free",
            description = "Free distilled version of DeepSeek R1 based on Llama 70B for reasoning and coding tasks.",
            image = Res.drawable.ic_deepseek
        ),
        TextModel(
            title = "Llama Vision Free",
            provider = "together",
            modelName = "meta-llama/Llama-Vision-Free",
            description = "Free vision-capable Llama model that supports multimodal text and image understanding.",
            image = Res.drawable.ic_llama
        ),
        TextModel(
            title = "AFM 4.5B Preview",
            provider = "together",
            modelName = "arcee-ai/AFM-4.5B-Preview",
            description = "Preview of Arcee AI's 4.5B parameter model for general-purpose language tasks.",
            image = Res.drawable.ic_arcee_ai
        ),
        TextModel(
            title = "DeepSeek R1 0528",
            provider = "together",
            modelName = "deepseek-ai/DeepSeek-R1-0528",
            description = "DeepSeek R1 model variant optimized for complex reasoning, math, and programming.",
            image = Res.drawable.ic_deepseek
        )
    )

    // Google Gemini models
    val geminiModels = listOf(
        TextModel(
            title = "Gemini 2.0 Flash",
            provider = "gemini",
            modelName = "gemini-2.0-flash",
            description = "Google's efficient flash model with 250 free messages per day, suitable for fast responses and roleplay.",
            image = Res.drawable.ic_gemini
        )
    )
}