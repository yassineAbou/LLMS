package org.yassineabou.llms.feature.chat.data.model

import llms.composeapp.generated.resources.*

object TextGenModelList {

    val allModels: List<TextModel> by lazy {
        otherModels + openaiModels + llamaModels + mistralModels
    }

    // Default fallback model
    val defaultModel: TextModel by lazy {
       otherModels.first() // Or any default you prefer
    }

    // Other Gemini models
    val otherModels = listOf(
        TextModel(
            title = "Gemini 2.0 Flash",
            provider = "gemini",
            modelName = "gemini-2.0-flash",
            description = "Google's efficient flash model with 250 free messages per day, suitable for fast responses and roleplay.",
            image = Res.drawable.ic_gemini
        ),
        TextModel(
            title = "DeepSeek R1 0528",
            provider = "llm7",
            modelName = "deepseek-r1-0528",
            description = "DeepSeek R1 model variant optimized for complex reasoning, math, and programming tasks.",
            image = Res.drawable.ic_deepseek
        ),
        TextModel(
            title = "Qwen2.5 Coder 32B Instruct",
            provider = "llm7",
            modelName = "qwen2.5-coder-32b-instruct",
            description = "Alibaba's Qwen2.5 Coder model with 32B parameters specialized for programming tasks and instruction following.",
            image = Res.drawable.ic_qwen
        ),
        TextModel(
            title = "GLM",
            provider = "llm7",
            modelName = "glm",
            description = "General Language Model with balanced capabilities across various language understanding tasks.",
            image = Res.drawable.ic_z
        ),
        TextModel(
            title = "Grok-3 Mini High",
            provider = "llm7",
            modelName = "grok-3-mini-high",
            description = "High-performance variant of Grok-3 Mini optimized for reasoning and conversational tasks.",
            image = Res.drawable.ic_grok
        ),
        TextModel(
            title = "Elixposearch",
            provider = "llm7",
            modelName = "elixposearch",
            description = "Specialized search model optimized for information retrieval and semantic search tasks.",
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

    //OpenaiModels
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
            description = "Optimized GPT-4o Mini model for common tasks with good performance and efficiency.",
            image = Res.drawable.ic_openai
        ),
        TextModel(
            title = "Bidara",
            provider = "llm7",
            modelName = "bidara",
            description = "Bidara model optimized for general language understanding and generation tasks.",
            image = Res.drawable.ic_biadara
        ),
    )

    val llamaModels = listOf(
        TextModel(
            title = "Llama 3.1 8B Instruct",
            provider = "llm7",
            modelName = "llama-3.1-8b-instruct-fp8",
            description = "Llama 3.1 8B Instruct model with FP8 quantization for faster inference while maintaining performance.",
            image = Res.drawable.ic_llama
        ),
        TextModel(
            title = "Llama 4 Scout 17B 16E Instruct",
            provider = "llm7",
            modelName = "llama-4-scout-17b-16e-instruct",
            description = "Llama 4 Scout model with 17B parameters and 16 experts for specialized instruction following.",
            image = Res.drawable.ic_llama
        ),
        TextModel(
            title = "Llama Fast Roblox",
            provider = "llm7",
            modelName = "llama-fast-roblox",
            description = "Specialized Llama model optimized for Roblox game development and scripting tasks.",
            image = Res.drawable.ic_llama
        ),
    )

    val mistralModels = listOf(
        TextModel(
            title = "Mistral Small 3.1 24B",
            provider = "llm7",
            modelName = "mistral-small-3.1-24b",
            description = "Larger Mistral Small variant with 24B parameters for more complex reasoning tasks.",
            image = Res.drawable.ic_mistral
        ),
        TextModel(
            title = "Mistral Small 3.1 24B Instruct",
            provider = "llm7",
            modelName = "mistral-small-3.1-24b-instruct-2503",
            description = "Instruction-tuned version of Mistral Small 3.1 24B model optimized for following user directions.",
            image = Res.drawable.ic_mistral
        ),
        TextModel(
            title = "Mistral Small 2402",
            provider = "llm7",
            modelName = "mistral-small-2402",
            description = "Compact Mistral model from February 2024 optimized for fast response times on simple tasks.",
            image = Res.drawable.ic_mistral
        ),
        TextModel(
            title = "Mistral Small 2409",
            provider = "llm7",
            modelName = "mistral-small-2409",
            description = "Mistral Small model from September 2024 with improved efficiency and performance.",
            image = Res.drawable.ic_mistral
        ),
        TextModel(
            title = "Mistral Small 2501",
            provider = "llm7",
            modelName = "mistral-small-2501",
            description = "January 2025 version of Mistral Small model with enhanced capabilities for common language tasks.",
            image = Res.drawable.ic_mistral
        ),
        TextModel(
            title = "Mistral Small 2503",
            provider = "llm7",
            modelName = "mistral-small-2503",
            description = "March 2025 version of Mistral Small model with further optimizations and improvements.",
            image = Res.drawable.ic_mistral
        ),
        TextModel(
            title = "Codestral 2405",
            provider = "llm7",
            modelName = "codestral-2405",
            description = "Mistral's code-specialized model trained on programming languages for code completion and generation.",
            image = Res.drawable.ic_mistral
        ),
        TextModel(
            title = "Codestral 2501",
            provider = "llm7",
            modelName = "codestral-2501",
            description = "Advanced version of Codestral with improved coding capabilities and support for newer programming paradigms.",
            image = Res.drawable.ic_mistral
        ),
        TextModel(
            title = "Ministral 3B 2410",
            provider = "llm7",
            modelName = "ministral-3b-2410",
            description = "Compact Mistral model with 3B parameters for efficient mobile and edge device deployment.",
            image = Res.drawable.ic_mistral
        ),
        TextModel(
            title = "Ministral 8B 2410",
            provider = "llm7",
            modelName = "ministral-8b-2410",
            description = "Balanced Mistral model with 8B parameters offering good performance for most language tasks.",
            image = Res.drawable.ic_mistral
        ),
        TextModel(
            title = "Mistral Large 2402",
            provider = "llm7",
            modelName = "mistral-large-2402",
            description = "Mistral Large model from February 2024 with strong reasoning and coding capabilities.",
            image = Res.drawable.ic_mistral
        ),
        TextModel(
            title = "Mistral Large 2407",
            provider = "llm7",
            modelName = "mistral-large-2407",
            description = "Mistral Large model from July 2024 with improved performance across multiple domains.",
            image = Res.drawable.ic_mistral
        ),
        TextModel(
            title = "Mistral Large 2411",
            provider = "llm7",
            modelName = "mistral-large-2411",
            description = "Most recent Mistral Large model with state-of-the-art performance for complex reasoning tasks.",
            image = Res.drawable.ic_mistral
        ),
        TextModel(
            title = "Mistral Medium",
            provider = "llm7",
            modelName = "mistral-medium",
            description = "Balanced Mistral model with medium-sized parameters for efficient general-purpose language tasks.",
            image = Res.drawable.ic_mistral
        ),
        TextModel(
            title = "Mistral Nemo Roblox",
            provider = "llm7",
            modelName = "mistral-nemo-roblox",
            description = "Specialized Mistral model optimized for Roblox game development and scripting.",
            image = Res.drawable.ic_mistral
        ),
        TextModel(
            title = "Mistral Saba 2502",
            provider = "llm7",
            modelName = "mistral-saba-2502",
            description = "Mistral model variant with specialized capabilities for February 2025 tasks and requirements.",
            image = Res.drawable.ic_mistral
        ),
        TextModel(
            title = "Open Mistral 7B",
            provider = "llm7",
            modelName = "open-mistral-7b",
            description = "Open-source version of Mistral 7B model with standard capabilities for general language tasks.",
            image = Res.drawable.ic_mistral
        ),
        TextModel(
            title = "Open Mistral Nemo",
            provider = "llm7",
            modelName = "open-mistral-nemo",
            description = "Open variant of Mistral Nemo model with specialized capabilities for specific domains.",
            image = Res.drawable.ic_mistral
        ),
        TextModel(
            title = "Open Mixtral 8x22B",
            provider = "llm7",
            modelName = "open-mixtral-8x22b",
            description = "Open-source version of Mixtral with 8 experts of 22B parameters each for advanced reasoning.",
            image = Res.drawable.ic_mistral
        ),
        TextModel(
            title = "Open Mixtral 8x7B",
            provider = "llm7",
            modelName = "open-mixtral-8x7b",
            description = "Open-source version of Mixtral with 8 experts of 7B parameters each for balanced performance.",
            image = Res.drawable.ic_mistral
        ),
        TextModel(
            title = "Pixtral 12B 2409",
            provider = "llm7",
            modelName = "pixtral-12b-2409",
            description = "Multimodal model with 12B parameters optimized for September 2024 image and text understanding.",
            image = Res.drawable.ic_mistral
        ),
        TextModel(
            title = "Pixtral Large 2411",
            provider = "llm7",
            modelName = "pixtral-large-2411",
            description = "Advanced multimodal model with large capacity for November 2024 image and text understanding tasks.",
            image = Res.drawable.ic_mistral
        ),
    )
}