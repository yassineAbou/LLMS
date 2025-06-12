package org.yassineabou.llms.feature.chat.data.model

import llms.composeapp.generated.resources.Res
import llms.composeapp.generated.resources.ic_agentica
import llms.composeapp.generated.resources.ic_byte_dance
import llms.composeapp.generated.resources.ic_deepseek
import llms.composeapp.generated.resources.ic_dolphin
import llms.composeapp.generated.resources.ic_glm
import llms.composeapp.generated.resources.ic_llama
import llms.composeapp.generated.resources.ic_microsoft
import llms.composeapp.generated.resources.ic_mistral
import llms.composeapp.generated.resources.ic_nvidia
import llms.composeapp.generated.resources.ic_qwen
import llms.composeapp.generated.resources.ic_sales_force
import llms.composeapp.generated.resources.ic_shisa

object TextGenModelList {

    val allModels: List<TextModel> by lazy {
        qwen + deepSeek + mistral + llama + others
    }

    // Default fallback model
    val defaultModel: TextModel by lazy {
        qwen.first() // Or any default you prefer
    }

    // Qwen models
    val qwen = listOf(
        TextModel(
            title = "Qwen3 235B A22B",
            chutesName = "Qwen/Qwen3-235B-A22B",
            description = "Qwen3 is the latest generation of large language models in Qwen series, offering a comprehensive suite of dense and mixture-of-experts (MoE) models. Built upon extensive training, Qwen3 delivers groundbreaking advancements in reasoning, instruction-following, agent capabilities, and multilingual support.",
            image = Res.drawable.ic_qwen
        ),
        TextModel(
            title = "Qwen3 30B A3B",
            chutesName = "Qwen/Qwen3-30B-A3B",
            description = "Qwen3-30B-A3B is a 30.5B parameter large language model with A3B architecture that excels at reasoning, instruction following, and agent capabilities across 100+ languages, featuring unique switchable thinking/non-...",
            image = Res.drawable.ic_qwen
        ),
        TextModel(
            title = "Qwen3 32B",
            chutesName = "Qwen/Qwen3-32B",
            description = "Qwen3-32B is a 32.8 billion parameter language model that excels at reasoning, coding, and multilingual tasks with the unique ability to switch between thinking and non-thinking modes for optimal performance across different use...",
            image = Res.drawable.ic_qwen
        ),
        TextModel(
            title = "Qwen3 14B",
            chutesName = "Qwen/Qwen3-14B",
            description = "Qwen3-14B is a 14.8 billion parameter open-source language model that can seamlessly switch between thinking and non-thinking modes, making it effective for both complex reasoning tasks and general conversation while...",
            image = Res.drawable.ic_qwen
        ),
        TextModel(
            title = "Qwen3 8B",
            chutesName = "Qwen/Qwen3-8B",
            description = "Qwen3-8B is an 8.2 billion parameter open source language model that can switch between analytical \"thinking\" and conversational modes, making it suitable for both complex reasoning tasks and general dialogue while...",
            image = Res.drawable.ic_qwen
        ),
        TextModel(
            title = "QwQ 32B ArliAI RpR V1",
            chutesName = "ArliAI/QwQ-32B-ArliAI-RpR-v1",
            description = "QwQ-32B-ArliAI-RpR-v1 is a 32 billion parameter language model fine-tuned for roleplay and creative writing that combines reasoning capabilities with creative storytelling to maintain coherence in long conversations.",
            image = Res.drawable.ic_qwen
        )
    )

    // DeepSeek models
    val deepSeek = listOf(
        TextModel(
            title = "DeepSeek R1 0528",
            chutesName = "deepseek-ai/DeepSeek-R1-0528",
            description = "DeepSeek-R1-0528 is an open-source large language model specializing in complex reasoning, mathematics, and programming tasks, with significant improvements over its predecessor in areas like AIME test performance (87.5%...",
            image = Res.drawable.ic_deepseek
        ),
        TextModel(
            title = "DeepSeek V3 0324",
            chutesName = "deepseek-ai/DeepSeek-V3-0324",
            description = "DeepSeek-V3-0324 is a Chinese and English-capable open-source LLM that excels at reasoning, web development, Chinese writing, and function calling, with significant improvements over its predecessor in benchmark...",
            image = Res.drawable.ic_deepseek
        ),
        TextModel(
            title = "DeepSeek R1",
            chutesName = "deepseek-ai/DeepSeek-R1",
            description = "DeepSeek-R1 is a 671B parameter (37B activated) language model trained with reinforcement learning that excels at mathematical reasoning, coding, and complex problem-solving tasks, achieving performance comparable to...",
            image = Res.drawable.ic_deepseek
        ),
        TextModel(
            title = "DeepSeek R1T Chimera",
            chutesName = "tngtech/DeepSeek-R1T-Chimera",
            description = "DeepSeek-R1T-Chimera is an open-source merged model combining DeepSeek-R1's intelligence and DeepSeek-V3's token efficiency to create a versatile language model suitable for general-purpose text generation and analysis...",
            image = Res.drawable.ic_deepseek
        ),
        TextModel(
            title = "DeepSeek V3",
            chutesName = "deepseek-ai/DeepSeek-V3",
            description = "DeepSeek-V3 is a 671B parameter MoE model (37B activated per token) that achieves state-of-the-art open-source performance across language, math and code tasks while maintaining reasonable compute requirements through...",
            image = Res.drawable.ic_deepseek
        ),
        TextModel(
            title = "DeepSeek R1 Zero",
            chutesName = "deepseek-ai/DeepSeek-R1-Zero",
            description = "DeepSeek-R1 is a 671B parameter (37B activated) reasoning-focused language model trained via reinforcement learning that excels at mathematical, coding, and general reasoning tasks, with performance comparable to OpenAI's models...",
            image = Res.drawable.ic_deepseek
        ),
        TextModel(
            title = "DeepSeek V3 Base",
            chutesName = "deepseek-ai/DeepSeek-V3-Base",
            description = "DeepSeek-V3 is a 671B parameter MoE language model (37B active parameters per token) that excels at coding, math, and reasoning tasks while maintaining strong performance across general knowledge and multilingual...",
            image = Res.drawable.ic_deepseek
        )
    )

    // Mistral models
    val mistral = listOf(
        TextModel(
            title = "Mistral Small 3.1 24B Instruct 2503",
            chutesName = "chutesai/Mistral-Small-3.1-24B-Instruct-2503",
            description = "Model Card for Mistral-Small-3.1-24B-Instruct-2503 Building upon Mistral Small 3 (2501), Mistral Small 3.1 (2503) adds state-of-the-art vision understanding and enhances long context capabilities up to 128k tokens without compromising text performance...",
            image = Res.drawable.ic_mistral
        ),
        TextModel(
            title = "Dolphin3.0 Mistral 24B",
            chutesName = "cognitivecomputations/Dolphin3.0-Mistral-24B",
            description = "Dolphin 3.0 Mistral 24B is a 24 billion parameter open-source language model designed for general-purpose tasks like coding, math, and function calling, with fully customizable system prompts that give users complete control...",
            image = Res.drawable.ic_dolphin
        )
    )

    // Llama models
    val llama = listOf(
        TextModel(
            title = "Llama 4 Maverick 17B 128E Instruct FP8",
            chutesName = "chutesai/Llama-4-Maverick-17B-128E-Instruct-FP8",
            description = "Meta's Llama 4 is a 17 billion parameter multimodal AI model (with up to 400B total parameters using mixture-of-experts architecture) that can understand both text and images while generating text responses, making it useful...",
            image = Res.drawable.ic_llama
        ),
        TextModel(
            title = "Llama 4 Scout 17B 16E Instruct",
            chutesName = "chutesai/Llama-4-Scout-17B-16E-Instruct",
            description = "Llama 4 is a collection of multimodal 17B parameter models (with total parameters of 109B-400B using mixture-of-experts) that can understand images and text in 12 languages, generating text and code responses for commercial...",
            image = Res.drawable.ic_llama
        ),
        TextModel(
            title = "Shisa V2 Llama3.3 70b",
            chutesName = "shisa-ai/shisa-v2-llama3.3-70b",
            description = "Shisa V2 is a family of bilingual Japanese/English language models ranging from 7B to 70B parameters, optimized for high-quality Japanese language capabilities while maintaining strong English performance, making them...",
            image = Res.drawable.ic_shisa
        ),
        TextModel(
            title = "Llama 3_1 Nemotron Ultra 253B V1",
            chutesName = "nvidia/Llama-3_1-Nemotron-Ultra-253B-v1",
            description = "Llama-3.1-Nemotron-Ultra-253B is a 253 billion parameter reasoning-focused language model optimized for efficiency that excels at math, coding, and general instruction-following tasks while running on a single 8xH100...",
            image = Res.drawable.ic_nvidia
        )
    )

    // Others models
    val others = listOf(
        TextModel(
            title = "TEMPLAR I",
            chutesName = "tplr/TEMPLAR-I",
            description = "The model information appears incomplete - the model card is empty except for showing it uses the MIT license, so I cannot provide an accurate summary of its capabilities, parameter count, or use cases.",
            image = Res.drawable.ic_microsoft
        ),
        TextModel(
            title = "MAI DS R1 FP8",
            chutesName = "microsoft/MAI-DS-R1-FP8",
            description = "MAI-DS-R1 is a 671B parameter post-trained version of DeepSeek-R1 that maintains strong reasoning capabilities while being more responsive on previously blocked topics, making it particularly useful for general language tasks...",
            image = Res.drawable.ic_microsoft
        ),
        TextModel(
            title = "GLM 4 32B 0414",
            chutesName = "THUDM/GLM-4-32B-0414",
            description = "GLM-4-32B-0414 is a 32 billion parameter open-source language model that performs well at tasks like code generation, artistic creation, function calling and complex reasoning, achieving performance comparable to GPT-...",
            image = Res.drawable.ic_glm
        ),
        TextModel(
            title = "DeepCoder 14B Preview",
            chutesName = "agentica-org/DeepCoder-14B-Preview",
            description = "DeepCoder-14B-Preview is a 14 billion parameter open-source code reasoning LLM fine-tuned with reinforcement learning that achieves competitive performance with OpenAI's o3-mini and can be used for advanced code...",
            image = Res.drawable.ic_agentica
        ),
        TextModel(
            title = "Seed Coder 8B Reasoning Bf16",
            chutesName = "ByteDance-Seed/Seed-Coder-8B-Reasoning-bf16",
            description = "Seed-Coder-8B-Reasoning is an 8 billion parameter code-focused language model optimized for programming tasks and reasoning capabilities, particularly useful for competitive programming and complex coding challenges.",
            image = Res.drawable.ic_byte_dance
        ),
        TextModel(
            title = "Xgen Small 9B Instruct R",
            chutesName = "Salesforce/xgen-small-9B-instruct-r",
            description = "xGen-small is a research-focused LLM available in 4B and 9B parameter sizes that excels at long-context tasks (up to 128k tokens) and shows strong performance on math, science, and coding benchmarks compared to similar-sized models...",
            image = Res.drawable.ic_sales_force
        ),
    )
}