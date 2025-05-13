package org.yassineabou.playground.feature.chat.data.model

import llms.composeapp.generated.resources.Res
import llms.composeapp.generated.resources.ic_agentica
import llms.composeapp.generated.resources.ic_deepseek
import llms.composeapp.generated.resources.ic_dolphin
import llms.composeapp.generated.resources.ic_glm
import llms.composeapp.generated.resources.ic_llama
import llms.composeapp.generated.resources.ic_microsoft
import llms.composeapp.generated.resources.ic_mistral
import llms.composeapp.generated.resources.ic_nvidia
import llms.composeapp.generated.resources.ic_qwen
import llms.composeapp.generated.resources.ic_shisa

object TextGenModelList {

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
        ),
        TextModel(
            title = "Qwen2.5 VL 32B Instruct",
            chutesName = "Qwen/Qwen2.5-VL-32B-Instruct",
            description = "Qwen2.5-VL-32B-Instruct Latest Updates: In addition to the original formula, we have further enhanced Qwen2.5-VL-32B's mathematical and problem-solving abilities through reinforcement learning...",
            image = Res.drawable.ic_qwen
        ),
    )

    // DeepSeek models
    val deepSeek = listOf(
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
            title = "DeepSeek Prover V2 671B",
            chutesName = "deepseek-ai/DeepSeek-Prover-V2-671B",
            description = "DeepSeek-Prover-V2 is an open-source large language model available in 7B and 671B parameter versions that specializes in formal mathematical theorem proving in Lean 4, capable of breaking down complex mathematical...",
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
            title = "Dolphin3.0 R1 Mistral 24B",
            chutesName = "cognitivecomputations/Dolphin3.0-R1-Mistral-24B",
            description = "Dolphin 3.0 R1 Mistral 24B is a 24 billion parameter open-source language model optimized for general-purpose tasks like coding, math, and reasoning that gives users full control over the system prompt and alignment.",
            image = Res.drawable.ic_dolphin
        ),
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
        ),
    )

    // Others models
    val others = listOf(
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
            title = "GLM Z1 32B 0414",
            chutesName = "THUDM/GLM-Z1-32B-0414",
            description = "GLM-4-Z1-32B-0414 is a 32 billion parameter open-source language model specialized in mathematical reasoning, code generation, and complex problem-solving through its \"deep thinking\" capabilities that allow it to break down...",
            image = Res.drawable.ic_glm
        ),
        TextModel(
            title = "DeepCoder 14B Preview",
            chutesName = "agentica-org/DeepCoder-14B-Preview",
            description = "DeepCoder-14B-Preview is a 14 billion parameter open-source code reasoning LLM fine-tuned with reinforcement learning that achieves competitive performance with OpenAI's o3-mini and can be used for advanced code...",
            image = Res.drawable.ic_agentica
        )
    )
}