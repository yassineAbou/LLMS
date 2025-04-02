package org.yassineabou.playground.feature.chat.model

import llms.composeapp.generated.resources.Res
import llms.composeapp.generated.resources.ic_alibaba_cloud
import llms.composeapp.generated.resources.ic_deep_seek
import llms.composeapp.generated.resources.ic_google
import llms.composeapp.generated.resources.ic_meta
import llms.composeapp.generated.resources.ic_mistral

object TextGenModelList {

    val deepSeek = listOf(
        TextModel(
            title = "DeepSeek-R1",
            provider = "DeepSeek",
            description = "DeepSeek-R1 is a new AI model that rivals OpenAI's GPT-4o in inference speed and performance. It is available on web, app, and API platforms for free access and benchmarking.",
            image = Res.drawable.ic_deep_seek
        ),
        TextModel(
            title = "DeepSeek-V3",
            provider = "DeepSeek",
            description = "DeepSeek-V3 is an open-source model that rivals the most advanced closed-source models in inference speed and performance. It can handle various tasks such as natural language, code, math, and more with high accuracy and efficiency.",
            image = Res.drawable.ic_deep_seek
        )
    )

    val alibabaCloud = listOf(
        TextModel(
            title = "QwQ-32B",
            provider = "Alibaba Cloud",
            description = "QwQ-32B-Preview is a research model that demonstrates AI reasoning capabilities, but has limitations in language mixing, recursive loops, and safety. It is based on transformers with RoPE, SwiGLU, and RMSNorm, and can generate texts from prompts.",
            image = Res.drawable.ic_alibaba_cloud
        ),
        TextModel(
            title = "Qwen2.5-72B",
            provider = "Alibaba Cloud",
            description = "Qwen2.5-72B is a causal language model with 72.7 billion parameters, pretrained on over 29 languages and various domains. It supports long-context, multilingual, and instruction-following generation, and can be fine-tuned for various tasks.",
            image = Res.drawable.ic_alibaba_cloud
        ),
        TextModel(
            title = "Qwen2.5-32B",
            provider = "Alibaba Cloud",
            description = "Qwen2.5-32B is a causal language model with 32.5 billion parameters, pretrained on over 29 languages and various domains. It supports long-context, multilingual, and instruction-following generation, and can be fine-tuned for various tasks.",
            image = Res.drawable.ic_alibaba_cloud
        ),
        TextModel(
            title = "Qwen2.5-14B",
            provider = "Alibaba Cloud",
            description = "Qwen2.5-14B is a causal language model with 14.7 billion parameters, pretrained on over 29 languages and various domains. It supports long-context, multilingual, and instruction-following generation, and can be fine-tuned for various tasks",
            image = Res.drawable.ic_alibaba_cloud
        ),
        TextModel(
            title = "Qwen2.5-7B",
            provider = "Alibaba Cloud",
            description = "Qwen2.5-7B is a causal language model with 7.61 billion parameters, pretrained on over 29 languages and various domains. It supports long-context, multilingual, and instruction-following generation, and can be fine-tuned for various tasks.",
            image = Res.drawable.ic_alibaba_cloud
        )
    )

    val google = listOf(
        TextModel(
            title = "Gemma-3-4B-It",
            provider = "Google",
            description = "Instruction-tuned 4B multimodal model with a 128K context window. Handles text/image input and generates text output across 140+ languages. Optimized for tasks like question answering and summarization while maintaining efficiency for deployment on consumer hardware.",
            image = Res.drawable.ic_google,
        ),
        TextModel(
            title = "Gemma-3-12B-It",
            provider = "Google",
            description = "Instruction-tuned 12B variant offering enhanced reasoning capabilities. Supports multimodal inputs (text/image) and multilingual output. Ideal for complex tasks like code generation and logical reasoning while retaining deployability on resource-constrained systems.",
            image = Res.drawable.ic_google,
        ),
        TextModel(
            title = "Gemma-3-27B-It",
            provider = "Google",
            description = "Largest instruction-tuned variant in the Gemma-3 family (27B parameters). Excels at advanced multimodal reasoning and long-context tasks (128K tokens). Supports 140+ languages and maintains usability on consumer-grade hardware despite its increased size.",
            image = Res.drawable.ic_google,
        ),
    )

    val mistral = listOf(
        TextModel(
            title = "Mistral-Small-3-24B",
            provider = "Mistral",
            description = "Mistral Small 3 ( 2501 ) sets a new benchmark in the \"small\" Large Language Models category below 70B, boasting 24B parameters and achieving state-of-the-art capabilities comparable to larger models! Check out our fine-tuned Instruct version Mistral-Small-24B-Instruct-2501",
            image = Res.drawable.ic_mistral,
        ),
        TextModel(
            title = "Mistral-Small-3-22B",
            provider = "Mistral",
            description = "Mistral-Small-Instruct-2409 is an instruct fine-tuned version with the following characteristics: 22B parameters; Vocabulary to 32768; Supports function calling; 32k sequence length",
            image = Res.drawable.ic_mistral,
        )
    )

    val meta = listOf(
        TextModel(
            title = "Llama-3.2-90B",
            provider = "Meta",
            description = "Flagship 90B vision-language model for complex image understanding and multilingual QA. Delivers top-tier performance in multimodal reasoning tasks.",
            image = Res.drawable.ic_meta,
        ),
        TextModel(
            title = "Llama-3.2-11B",
            provider = "Meta",
            description = "Multimodal 11B model processing text+images for visual recognition, reasoning, and captioning. Excels in vision-language tasks with SOTA benchmark results.",
            image = Res.drawable.ic_meta,
        ),
        TextModel(
            title = "Llama-3.2-3B",
            provider = "Meta",
            description = "Compact 3B parameter model optimized for multilingual dialogue (agentic retrieval, summarization). Outperforms open/closed models in benchmarks. Text-only version for conversational AI applications.",
            image = Res.drawable.ic_meta,
        ),
        TextModel(
            title = "Llama-3.2-1B",
            provider = "Meta",
            description = "Lightweight 1B version for efficient multilingual chat applications. Instruction-tuned for dialogue tasks with strong benchmark performance across language understanding metrics.",
            image = Res.drawable.ic_meta,
        ),
    )
}