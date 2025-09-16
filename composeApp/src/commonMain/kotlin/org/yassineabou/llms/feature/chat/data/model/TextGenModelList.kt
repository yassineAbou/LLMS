package org.yassineabou.llms.feature.chat.data.model

import llms.composeapp.generated.resources.*

object TextGenModelList {

    val allModels: List<TextModel> by lazy {
        otherModels + openaiModels + mistralModels + deepseekModels + llamaModels
    }

    // Default fallback model
    val defaultModel: TextModel by lazy {
        // Using gpt-4o-nano as default
        openaiModels.first()
    }

    // DeepSeek Models
    val deepseekModels = listOf(
        TextModel(
            title = "DeepSeek R1 0528",
            provider = "llm7",
            modelName = "deepseek-r1-0528",
            description = "DeepSeek R1 model variant optimized for complex reasoning, math, and programming tasks.",
            image = Res.drawable.ic_deepseek
        ),
        TextModel(
            title = "DeepSeek R1",
            provider = "llm7",
            modelName = "deepseek-r1",
            description = "DeepSeek's advanced reasoning model for complex problem-solving.",
            image = Res.drawable.ic_deepseek
        ),
        TextModel(
            title = "DeepSeek V3 0324",
            provider = "llm7",
            modelName = "deepseek-v3-0324",
            description = "DeepSeek V3 model with enhanced capabilities for diverse tasks.",
            image = Res.drawable.ic_deepseek
        ),
        TextModel(
            title = "DeepSeek AI R1 0528",
            provider = "llm7",
            modelName = "deepseek-ai/DeepSeek-R1-0528",
            description = "DeepSeek AI's R1 model optimized for reasoning and analysis.",
            image = Res.drawable.ic_deepseek
        )
    )

    // OpenAI Models
    val openaiModels = listOf(
        TextModel(
            title = "GPT-4.1 Nano",
            provider = "llm7",
            modelName = "gpt-4.1-nano-2025-04-14",
            description = "Compact GPT-4.1 Nano model for efficient inference with good reasoning capabilities.",
            image = Res.drawable.ic_openai
        ),
        TextModel(
            title = "GPT-4o Mini",
            provider = "llm7",
            modelName = "gpt-4o-mini-2024-07-18",
            description = "Compact GPT-4o model optimized for efficient inference with strong capabilities.",
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
            description = "Instruction-tuned version of Mistral Small 3.1 24B for following directions.",
            image = Res.drawable.ic_mistral
        ),
        TextModel(
            title = "Mistral Small 2503",
            provider = "llm7",
            modelName = "mistral-small-2503",
            description = "Small model with optimizations and improvements for March 2025.",
            image = Res.drawable.ic_mistral
        ),
        TextModel(
            title = "Open Mixtral 8x7B",
            provider = "llm7",
            modelName = "open-mixtral-8x7b",
            description = "Open-source Mixtral with 8x7B experts for balanced performance.",
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
            title = "Midnight Rose 70B v2.0.3",
            provider = "llm7",
            modelName = "midnight-rose-70b-v2.0.3",
            description = "Specialized 70B model for creative writing and roleplay scenarios.",
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
        ),
        TextModel(
            title = "Amazon Nova Fast",
            provider = "llm7",
            modelName = "nova-fast",
            description = "Amazon's model optimized for speed and efficient responses.",
            image = Res.drawable.ic_nova
        ),
        TextModel(
            title = "Roblox RP",
            provider = "llm7",
            modelName = "roblox-rp",
            description = "Model specialized for Roblox role-playing and scripting scenarios.",
            image = Res.drawable.ic_llm7
        ),
        TextModel(
            title = "Bidara",
            provider = "llm7",
            modelName = "bidara",
            description = "Bidara model optimized for general language understanding and generation tasks.",
            image = Res.drawable.ic_llm7
        ),
        TextModel(
            title = "Mirexa",
            provider = "llm7",
            modelName = "mirexa",
            description = "Mirexa model designed for creative writing and narrative generation tasks.",
            image = Res.drawable.ic_mirexa
        ),
    )
}