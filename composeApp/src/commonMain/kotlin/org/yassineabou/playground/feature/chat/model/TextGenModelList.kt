package org.yassineabou.playground.feature.chat.model

import llms.composeapp.generated.resources.Res
import llms.composeapp.generated.resources.ic_alibaba_cloud
import llms.composeapp.generated.resources.ic_deep_seek
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
            title = "Llama-3.2-3B",
            provider = "Meta",
            description = "The Llama 3.2 collection of multilingual large language models (LLMs) is a collection of pretrained and instruction-tuned generative models in 1B and 3B sizes (text in/text out). The Llama 3.2 instruction-tuned text only models are optimized for multilingual dialogue use cases, including agentic retrieval and summarization tasks. They outperform many of the available open source and closed chat models on common industry benchmarks.",
            image = Res.drawable.ic_meta,
        ),
        TextModel(
            title = " Llama-3.2-1B",
            provider = "Meta",
            description = "The Meta Llama 3.2 collection of multilingual large language models (LLMs) is a collection of pretrained and instruction-tuned generative models in 1B and 3B sizes (text in/text out). The Llama 3.2 instruction-tuned text only models are optimized for multilingual dialogue use cases, including agentic retrieval and summarization tasks. They outperform many of the available open source and closed chat models on common industry benchmarks.",
            image = Res.drawable.ic_meta,
        ),
        TextModel(
            title = "Llama-3.2-11B",
            provider = "Meta",
            description = "The Llama 3.2-Vision collection of multimodal large language models (LLMs) is a collection of pretrained and instruction-tuned image reasoning generative models in 11B and 90B sizes (text + images in / text out). The Llama 3.2-Vision instruction-tuned models are optimized for visual recognition, image reasoning, captioning, and answering general questions about an image. The models outperform many of the available open source and closed multimodal models on common industry benchmarks.",
            image = Res.drawable.ic_meta,
        ),
        TextModel(
            title = "Llama-3.2-90B",
            provider = "Meta",
            description = "The Llama 3.2-Vision collection of multimodal large language models (LLMs) is a collection of pretrained and instruction-tuned image reasoning generative models in 11B and 90B sizes (text + images in / text out). The Llama 3.2-Vision instruction-tuned models are optimized for visual recognition, image reasoning, captioning, and answering general questions about an image. The models outperform many of the available open source and closed multimodal models on common industry benchmarks.",
            image = Res.drawable.ic_meta,
        )


    )
}