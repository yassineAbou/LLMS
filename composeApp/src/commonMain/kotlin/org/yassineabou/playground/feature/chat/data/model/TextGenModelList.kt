package org.yassineabou.playground.feature.chat.data.model

import llms.composeapp.generated.resources.Res
import llms.composeapp.generated.resources.ic_agentica
import llms.composeapp.generated.resources.ic_deepseek
import llms.composeapp.generated.resources.ic_gemma
import llms.composeapp.generated.resources.ic_kimi
import llms.composeapp.generated.resources.ic_llama
import llms.composeapp.generated.resources.ic_mistral
import llms.composeapp.generated.resources.ic_nvidia
import llms.composeapp.generated.resources.ic_open_r_one
import llms.composeapp.generated.resources.ic_qwen
import llms.composeapp.generated.resources.ic_reka
import llms.composeapp.generated.resources.ic_shisa

object TextGenModelList {

    val deepseek = listOf(
        TextModel(
            title = "DeepSeek V3 0324",
            chutesName = "deepseek-ai/DeepSeek-V3-0324",
            description = "DeepSeek-V3-0324 is a Chinese and English-capable open-source LLM that excels at reasoning, web development, Chinese writing, and function calling, with significant improvements over its predecessor in benchmark...",
            image = Res.drawable.ic_deepseek,
            provider = "DeepSeek"
        ),
        TextModel(
            title = "DeepSeek R1",
            chutesName = "deepseek-ai/DeepSeek-R1",
            description = "DeepSeek-R1 is a 671B parameter (37B activated) language model trained with reinforcement learning that excels at mathematical reasoning, coding, and complex problem-solving tasks, achieving performance comparable to...",
            image = Res.drawable.ic_deepseek,
            provider = "DeepSeek"
        ),
        TextModel(
            title = "DeepSeek V3",
            chutesName = "deepseek-ai/DeepSeek-V3",
            description = "DeepSeek-V3 is a 671B parameter MoE model (37B activated per token) that achieves state-of-the-art open-source performance across language, math and code tasks while maintaining reasonable compute requirements through...",
            image = Res.drawable.ic_deepseek,
            provider = "DeepSeek"
        )
    )

    val qwen = listOf(
        TextModel(
            title = "Qwen2.5 VL 32B Instruct",
            chutesName = "Qwen/Qwen2.5-VL-32B-Instruct",
            description = "Qwen2.5-VL-32B is a 32 billion parameter multimodal model that can understand images, videos, and text to perform tasks like visual analysis, mathematical reasoning, and UI interaction, with particularly strong performance...",
            image = Res.drawable.ic_qwen,
            provider = "Qwen"
        ),
        TextModel(
            title = "QwQ 32B ArliAI RpR V1",
            chutesName = "ArliAI/QwQ-32B-ArliAI-RpR-v1",
            description = "QwQ-32B-ArliAI-RpR-v1 is a 32 billion parameter language model fine-tuned for roleplay and creative writing that combines reasoning capabilities with creative storytelling to maintain coherence in long conversations.",
            image = Res.drawable.ic_qwen,
            provider = "Qwen"
        )
    )

    val gemma = listOf(
        TextModel(
            title = "Gemma 3 12b It",
            chutesName = "unsloth/gemma-3-12b-it",
            description = "Gemma 3 is a family of open-source multimodal models from Google (available in 1B, 4B, 12B, and 27B parameter sizes) that can process both text and images with a 128K context window, designed for tasks like question...",
            image = Res.drawable.ic_gemma,
            provider = "Gemma"
        ),
        TextModel(
            title = "Gemma 3 4b It",
            chutesName = "unsloth/gemma-3-4b-it",
            description = "Gemma 3 is an open-source multimodal model available in 1B, 4B, 12B, and 27B parameter sizes that can handle both text and images with a 128K context window, making it suitable for tasks like question answering...",
            image = Res.drawable.ic_gemma,
            provider = "Gemma"
        ),
        TextModel(
            title = "Gemma 3 1b It",
            chutesName = "unsloth/gemma-3-1b-it",
            description = "Gemma 3 is a new open-source multimodal LLM from Google DeepMind available in 1B, 4B, 12B and 27B parameter sizes that can process both text and images with a 128K context window, making it well-suited for tasks like...",
            image = Res.drawable.ic_gemma,
            provider = "Gemma"
        ),
    )

    val openR1 = listOf(
        TextModel(
            title = "OlympicCoder 32B",
            chutesName = "open-r1/OlympicCoder-32B",
            description = "32 billion parameter code generation model fine-tuned on competitive programming data that excels at solving complex algorithmic problems in programming competitions and coding challenges.",
            image = Res.drawable.ic_open_r_one,
            provider = "Open R1"
        ),
        TextModel(
            title = "OlympicCoder 7B",
            chutesName = "open-r1/OlympicCoder-7B",
            description = "A 7B parameter code generation model fine-tuned on competitive programming problems that performs well on IOI and LiveCodeBench benchmarks, useful for solving complex algorithmic coding challenges.",
            image = Res.drawable.ic_open_r_one,
            provider = "Open R1"
        )
    )

    val mistral = listOf(
        TextModel(
            title = "Dolphin3.0 R1 Mistral 24B",
            chutesName = "cognitivecomputations/Dolphin3.0-R1-Mistral-24B",
            description = "Dolphin 3.0 R1 Mistral 24B is a 24 billion parameter open-source language model optimized for general-purpose tasks like coding, math, and reasoning that gives users full control over the system prompt and alignment.",
            image = Res.drawable.ic_mistral,
            provider = "Mistral"
        ),
        TextModel(
            title = "Mistral Small 3.1 24B Instruct 2503",
            chutesName = "chutesai/Mistral-Small-3.1-24B-Instruct-2503",
            description = "Mistral Small 3.1 is a 24B parameter open-source language model with vision capabilities and 128k context that excels at reasoning, conversation, and programming tasks while being compact enough to run on consumer...",
            image = Res.drawable.ic_mistral,
            provider = "Mistral"
        ),
        TextModel(
            title = "Dolphin3.0 Mistral 24B",
            chutesName = "cognitivecomputations/Dolphin3.0-Mistral-24B",
            description = "Dolphin 3.0 Mistral 24B is a 24 billion parameter open-source language model designed for general-purpose tasks like coding, math, and function calling, with fully customizable system prompts that give users complete control...",
            image = Res.drawable.ic_mistral,
            provider = "Mistral"
        )
    )

    val llama = listOf(
        TextModel(
            title = "Llama 4 Maverick 17B 128E Instruct FP8",
            chutesName = "chutesai/Llama-4-Maverick-17B-128E-Instruct-FP8",
            description = "Meta's Llama 4 is a 17 billion parameter multimodal AI model (with up to 400B total parameters using mixture-of-experts architecture) that can understand both text and images while generating text responses, making it useful...",
            image = Res.drawable.ic_llama,
            provider = "Llama"
        ),
        TextModel(
            title = "Llama 4 Scout 17B 16E Instruct",
            chutesName = "chutesai/Llama-4-Scout-17B-16E-Instruct",
            description = "Llama 4 is a collection of multimodal 17B parameter models (with total parameters of 109B-400B using mixture-of-experts) that can understand images and text in 12 languages, generating text and code responses for commercial...",
            image = Res.drawable.ic_llama,
            provider = "Llama"
        ),
        TextModel(
            title = "Llama 3_1 Nemotron Ultra 253B V1",
            chutesName = "nvidia/Llama-3_1-Nemotron-Ultra-253B-v1",
            description = "Llama-3.1-Nemotron-Ultra-253B is a 253 billion parameter reasoning-focused language model optimized for efficiency that excels at math, coding, and general instruction-following tasks while running on a single 8xH100...",
            image = Res.drawable.ic_nvidia,
            provider = "Llama"
        ),
        TextModel(
            title = "Shisa V2 Llama3.3 70b",
            chutesName = "shisa-ai/shisa-v2-llama3.3-70b",
            description = "Shisa V2 is a family of bilingual Japanese/English language models ranging from 7B to 70B parameters, optimized for high-quality Japanese language capabilities while maintaining strong English performance, making them...",
            image = Res.drawable.ic_shisa,
            provider = "Llama"
        ),
        TextModel(
            title = "Llama 3_3 Nemotron Super 49B V1",
            chutesName = "nvidia/Llama-3_3-Nemotron-Super-49B-v1",
            description = "Llama-3.3-Nemotron-Super-49B-v1 is a 49 billion parameter reasoning-focused language model optimized for efficiency that excels at math, coding, and chat tasks while being deployable on a single GPU.",
            image = Res.drawable.ic_nvidia,
            provider = "Llama"
        ),
        TextModel(
            title = "Llama 3.1 Nemotron Nano 8B V1",
            chutesName = "nvidia/Llama-3.1-Nemotron-Nano-8B-v1",
            description = "Llama-3.1-Nemotron-Nano-8B-v1 is an 8 billion parameter LLM optimized for reasoning tasks that can run on a single RTX GPU, making it suitable for developers building AI agents, chatbots, and RAG systems who need a...",
            image = Res.drawable.ic_nvidia,
            provider = "Llama"
        ),
        TextModel(
            title = "DeepHermes 3 Llama 3 8B Preview",
            chutesName = "NousResearch/DeepHermes-3-Llama-3-8B-Preview",
            description = "DeepHermes 3 is an 8B parameter open-source LLM that uniquely combines traditional chat responses with long chain-of-thought reasoning capabilities, making it particularly useful for tasks requiring detailed problem-solving or analytical thinking...",
            image = Res.drawable.ic_shisa,
            provider = "Llama"
        )
    )

    val others = listOf(
        TextModel(
            title = "Kimi VL A3B Thinking",
            chutesName = "moonshotai/Kimi-VL-A3B-Thinking",
            description = "Kimi-VL is a 16B-parameter MoE vision-language model (though only 2.8B parameters are active during inference) that excels at multimodal reasoning, long-context understanding, and agent interactions across tasks like...",
            image = Res.drawable.ic_kimi,
            provider = "Others"
        ),
        TextModel(
            title = "DeepCoder 14B Preview",
            chutesName = "agentica-org/DeepCoder-14B-Preview",
            description = "DeepCoder-14B-Preview is a 14 billion parameter open-source code reasoning LLM fine-tuned with reinforcement learning that achieves competitive performance with OpenAI's o3-mini and can be used for advanced code...",
            image = Res.drawable.ic_agentica,
            provider = "Others"
        ),
        TextModel(
            title = "Reka Flash 3",
            chutesName = "RekaAI/reka-flash-3",
            description = "Reka Flash 3 is a 21B parameter open-source language model optimized for general reasoning tasks that offers competitive performance to proprietary models while being suitable for low-latency and on-device applications.",
            image = Res.drawable.ic_reka,
            provider = "Others"
        )
    )
}