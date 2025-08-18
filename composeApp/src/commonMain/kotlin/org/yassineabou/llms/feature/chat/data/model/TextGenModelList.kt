package org.yassineabou.llms.feature.chat.data.model

import llms.composeapp.generated.resources.*

object TextGenModelList {

    val allModels: List<TextModel> by lazy {
        otherModels + openaiModels + llamaModels + mistralModels
    }

    // Default fallback model
    val defaultModel: TextModel by lazy {
        // A robust default like gpt-4o-mini is a good choice
        openaiModels.first()
    }

    // Other/General models
    val otherModels = listOf(
        TextModel(
            title = "Claude 3.5 Haiku", // UPDATED
            provider = "llm7",
            modelName = "claude-3-5-haiku-20241022",
            description = "Anthropic's fast and compact model, ideal for responsive user-facing applications.",
            image = Res.drawable.ic_claude
        ),
        TextModel(
            title = "Gemini", // ADDED
            provider = "llm7",
            modelName = "gemini",
            description = "Google's powerful and multimodal model, suitable for a wide range of tasks.",
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
            description = "Alibaba's Qwen2.5 Coder model with 32B parameters specialized for programming tasks.",
            image = Res.drawable.ic_qwen
        ),
        TextModel(
            title = "Amazon Nova Fast", // ADDED
            provider = "llm7",
            modelName = "nova-fast",
            description = "A model optimized for speed and efficient responses.",
            image = Res.drawable.ic_nova
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
        TextModel(
            title = "Roblox RP", // ADDED
            provider = "llm7",
            modelName = "roblox-rp",
            description = "A model specialized for Roblox role-playing and scripting scenarios.",
            image = Res.drawable.ic_llm7
        )
    )

    // OpenAI Models
    val openaiModels = listOf(
        TextModel(
            title = "GPT-5 Nano", // ADDED
            provider = "llm7",
            modelName = "gpt-5-nano-2025-08-07",
            description = "Next-generation compact model from OpenAI for high-efficiency tasks.",
            image = Res.drawable.ic_openai
        ),
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

    // Llama Models
    val llamaModels = listOf(
        TextModel(
            title = "Llama 4 Scout 17B",
            provider = "llm7",
            modelName = "llama-4-scout-17b-16e-instruct",
            description = "Llama 4 Scout model with 17B parameters and 16 experts for instruction following.",
            image = Res.drawable.ic_llama
        ),
        TextModel(
            title = "Llama 3.1 8B Instruct",
            provider = "llm7",
            modelName = "llama-3.1-8b-instruct-fp8",
            description = "Llama 3.1 8B Instruct model with FP8 quantization for faster inference.",
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

    // Mistral Models
    val mistralModels = listOf(
        TextModel(
            title = "Codestral 2405",
            provider = "llm7",
            modelName = "codestral-2405",
            description = "Mistral's code-specialized model for code completion and generation.",
            image = Res.drawable.ic_mistral
        ),
        TextModel(
            title = "Codestral 2501",
            provider = "llm7",
            modelName = "codestral-2501",
            description = "Advanced version of Codestral with improved coding capabilities.",
            image = Res.drawable.ic_mistral
        ),
        TextModel(
            title = "Ministral 3B 2410",
            provider = "llm7",
            modelName = "ministral-3b-2410",
            description = "Compact 3B parameter model for efficient mobile and edge device deployment.",
            image = Res.drawable.ic_mistral
        ),
        TextModel(
            title = "Ministral 8B 2410",
            provider = "llm7",
            modelName = "ministral-8b-2410",
            description = "Balanced 8B parameter model offering good performance for most language tasks.",
            image = Res.drawable.ic_mistral
        ),
        TextModel(
            title = "Mistral Large 2402",
            provider = "llm7",
            modelName = "mistral-large-2402",
            description = "Large model with strong reasoning and coding capabilities (Feb 2024).",
            image = Res.drawable.ic_mistral
        ),
        TextModel(
            title = "Mistral Large 2407",
            provider = "llm7",
            modelName = "mistral-large-2407",
            description = "Large model with improved performance across multiple domains (Jul 2024).",
            image = Res.drawable.ic_mistral
        ),
        TextModel(
            title = "Mistral Large 2411",
            provider = "llm7",
            modelName = "mistral-large-2411",
            description = "Most recent Large model with SOTA performance (Nov 2024).",
            image = Res.drawable.ic_mistral
        ),
        TextModel(
            title = "Mistral Medium",
            provider = "llm7",
            modelName = "mistral-medium",
            description = "Balanced medium-sized model for efficient general-purpose language tasks.",
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
            description = "Mistral model variant with specialized capabilities for February 2025.",
            image = Res.drawable.ic_mistral
        ),
        TextModel(
            title = "Mistral Small 2402",
            provider = "llm7",
            modelName = "mistral-small-2402",
            description = "Compact model for fast responses on simple tasks (Feb 2024).",
            image = Res.drawable.ic_mistral
        ),
        TextModel(
            title = "Mistral Small 2409",
            provider = "llm7",
            modelName = "mistral-small-2409",
            description = "Small model with improved efficiency and performance (Sep 2024).",
            image = Res.drawable.ic_mistral
        ),
        TextModel(
            title = "Mistral Small 2501",
            provider = "llm7",
            modelName = "mistral-small-2501",
            description = "Small model with enhanced capabilities for language tasks (Jan 2025).",
            image = Res.drawable.ic_mistral
        ),
        TextModel(
            title = "Mistral Small 2503",
            provider = "llm7",
            modelName = "mistral-small-2503",
            description = "Small model with further optimizations and improvements (Mar 2025).",
            image = Res.drawable.ic_mistral
        ),
        TextModel(
            title = "Mistral Small 3.1 24B",
            provider = "llm7",
            modelName = "mistral-small-3.1-24b",
            description = "Larger Mistral Small variant with 24B parameters for more complex reasoning.",
            image = Res.drawable.ic_mistral
        ),
        TextModel(
            title = "Mistral Small 3.1 24B Instruct",
            provider = "llm7",
            modelName = "mistral-small-3.1-24b-instruct-2503",
            description = "Instruction-tuned version of Mistral Small 3.1 24B for following directions.",
            image = Res.drawable.ic_mistral
        ),
        TextModel(
            title = "Open Mistral 7B",
            provider = "llm7",
            modelName = "open-mistral-7b",
            description = "Open-source 7B model for general language tasks.",
            image = Res.drawable.ic_mistral
        ),
        TextModel(
            title = "Open Mistral Nemo",
            provider = "llm7",
            modelName = "open-mistral-nemo",
            description = "Open variant of Mistral Nemo with specialized capabilities.",
            image = Res.drawable.ic_mistral
        ),
        TextModel(
            title = "Open Mixtral 8x22B",
            provider = "llm7",
            modelName = "open-mixtral-8x22b",
            description = "Open-source Mixtral with 8x22B experts for advanced reasoning.",
            image = Res.drawable.ic_mistral
        ),
        TextModel(
            title = "Open Mixtral 8x7B",
            provider = "llm7",
            modelName = "open-mixtral-8x7b",
            description = "Open-source Mixtral with 8x7B experts for balanced performance.",
            image = Res.drawable.ic_mistral
        ),
        TextModel(
            title = "Pixtral 12B 2409",
            provider = "llm7",
            modelName = "pixtral-12b-2409",
            description = "Multimodal 12B model for image and text understanding (Sep 2024).",
            image = Res.drawable.ic_mistral
        ),
        TextModel(
            title = "Pixtral Large 2411",
            provider = "llm7",
            modelName = "pixtral-large-2411",
            description = "Advanced multimodal model for image and text tasks (Nov 2024).",
            image = Res.drawable.ic_mistral
        ),
    )
}