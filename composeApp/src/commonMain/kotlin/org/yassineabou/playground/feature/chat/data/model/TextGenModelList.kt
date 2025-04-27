package org.yassineabou.playground.feature.chat.data.model

import llms.composeapp.generated.resources.Res
import llms.composeapp.generated.resources.ic_agentica
import llms.composeapp.generated.resources.ic_deepseek
import llms.composeapp.generated.resources.ic_glm
import llms.composeapp.generated.resources.ic_llama
import llms.composeapp.generated.resources.ic_microsoft
import llms.composeapp.generated.resources.ic_mistral
import llms.composeapp.generated.resources.ic_nvidia
import llms.composeapp.generated.resources.ic_qwen
import llms.composeapp.generated.resources.ic_shisa


val textGenModelList = listOf(
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
            title = "Mistral Small 3.1 24B Instruct 2503",
            chutesName = "chutesai/Mistral-Small-3.1-24B-Instruct-2503",
            description = "Mistral Small 3.1 is a 24B parameter open-source language model with vision capabilities and 128k context that excels at reasoning, conversation, and programming tasks while being compact enough to run on consumer...",
            image = Res.drawable.ic_mistral
        ),
        TextModel(
            title = "DeepSeek V3",
            chutesName = "deepseek-ai/DeepSeek-V3",
            description = "DeepSeek-V3 is a 671B parameter MoE model (37B activated per token) that achieves state-of-the-art open-source performance across language, math and code tasks while maintaining reasonable compute requirements through...",
            image = Res.drawable.ic_deepseek
        ),
        TextModel(
            title = "Llama 4 Maverick 17B 128E Instruct FP8",
            chutesName = "chutesai/Llama-4-Maverick-17B-128E-Instruct-FP8",
            description = "Meta's Llama 4 is a 17 billion parameter multimodal AI model (with up to 400B total parameters using mixture-of-experts architecture) that can understand both text and images while generating text responses, making it useful...",
            image = Res.drawable.ic_llama
        ),
        TextModel(
            title = "Qwen2.5 VL 32B Instruct",
            chutesName = "Qwen/Qwen2.5-VL-32B-Instruct",
            description = "Qwen2.5-VL-32B is a 32 billion parameter multimodal model that can understand images, videos, and text to perform tasks like visual analysis, mathematical reasoning, and UI interaction, with particularly strong performance...",
            image = Res.drawable.ic_qwen
        ),
        TextModel(
            title = "MAI DS R1 FP8",
            chutesName = "microsoft/MAI-DS-R1-FP8",
            description = "MAI-DS-R1 is a 671B parameter post-trained version of DeepSeek-R1 that maintains strong reasoning capabilities while being more responsive on previously blocked topics, making it particularly useful for general language tasks...",
            image = Res.drawable.ic_microsoft
        ),
        TextModel(
            title = "Dolphin3.0 R1 Mistral 24B",
            chutesName = "cognitivecomputations/Dolphin3.0-R1-Mistral-24B",
            description = "Dolphin 3.0 R1 Mistral 24B is a 24 billion parameter open-source language model optimized for general-purpose tasks like coding, math, and reasoning that gives users full control over the system prompt and alignment.",
            image = Res.drawable.ic_mistral
        ),
        TextModel(
            title = "Llama 4 Scout 17B 16E Instruct",
            chutesName = "chutesai/Llama-4-Scout-17B-16E-Instruct",
            description = "Llama 4 is a collection of multimodal 17B parameter models (with total parameters of 109B-400B using mixture-of-experts) that can understand images and text in 12 languages, generating text and code responses for commercial...",
            image = Res.drawable.ic_llama
        ),
        TextModel(
            title = "DeepCoder 14B Preview",
            chutesName = "agentica-org/DeepCoder-14B-Preview",
            description = "DeepCoder-14B-Preview is a 14 billion parameter open-source code reasoning LLM fine-tuned with reinforcement learning that achieves competitive performance with OpenAI's o3-mini and can be used for advanced code...",
            image = Res.drawable.ic_agentica
        ),
        TextModel(
            title = "Llama 3_1 Nemotron Ultra 253B V1",
            chutesName = "nvidia/Llama-3_1-Nemotron-Ultra-253B-v1",
            description = "Llama-3.1-Nemotron-Ultra-253B is a 253 billion parameter reasoning-focused language model optimized for efficiency that excels at math, coding, and general instruction-following tasks while running on a single 8xH100...",
            image = Res.drawable.ic_nvidia
        ),
        TextModel(
            title = "GLM 4 32B 0414",
            chutesName = "THUDM/GLM-4-32B-0414",
            description = "GLM-4-32B-0414 is a 32 billion parameter open-source language model that performs well at tasks like code generation, artistic creation, function calling and complex reasoning, achieving performance comparable to GPT-...",
            image = Res.drawable.ic_glm
        ),
        TextModel(
            title = "Dolphin3.0 Mistral 24B",
            chutesName = "cognitivecomputations/Dolphin3.0-Mistral-24B",
            description = "Dolphin 3.0 Mistral 24B is a 24 billion parameter open-source language model designed for general-purpose tasks like coding, math, and function calling, with fully customizable system prompts that give users complete control...",
            image = Res.drawable.ic_mistral
        ),
        TextModel(
            title = "QwQ 32B ArliAI RpR V1",
            chutesName = "ArliAI/QwQ-32B-ArliAI-RpR-v1",
            description = "QwQ-32B-ArliAI-RpR-v1 is a 32 billion parameter language model fine-tuned for roleplay and creative writing that combines reasoning capabilities with creative storytelling to maintain coherence in long conversations.",
            image = Res.drawable.ic_qwen
        ),
        TextModel(
            title = "Shisa V2 Llama3.3 70b",
            chutesName = "shisa-ai/shisa-v2-llama3.3-70b",
            description = "Shisa V2 is a family of bilingual Japanese/English language models ranging from 7B to 70B parameters, optimized for high-quality Japanese language capabilities while maintaining strong English performance, making them...",
            image = Res.drawable.ic_shisa
        ),
        TextModel(
            title = "GLM Z1 32B 0414",
            chutesName = "THUDM/GLM-Z1-32B-0414",
            description = "GLM-4-Z1-32B-0414 is a 32 billion parameter open-source language model specialized in mathematical reasoning, code generation, and complex problem-solving through its \"deep thinking\" capabilities that allow it to break down...",
            image = Res.drawable.ic_glm
        ),
        TextModel(
            title = "Llama 3.1 405B FP8",
            chutesName = "chutesai/Llama-3.1-405B-FP8",
            description = "Meta's Llama 3.1 is a collection of open source language models (8B, 70B, and 405B parameters) designed for multilingual dialogue, featuring strong performance across benchmarks and supporting commercial use in 8...",
            image = Res.drawable.ic_shisa,
        ),
)