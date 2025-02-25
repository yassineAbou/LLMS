package org.yassineabou.playground.feature.chat.model

import llms.composeapp.generated.resources.Res
import llms.composeapp.generated.resources.ic_alibaba_cloud
import llms.composeapp.generated.resources.ic_deep_seek
import llms.composeapp.generated.resources.ic_meta

object TextGenModelList {

    val deepSeek = listOf(
        TextModel(
            title = "DeepSeek-Coder-V2",
            provider = "DeepSeek",
            description = "We present DeepSeek-Coder-V2, an open-source Mixture-of-Experts (MoE) code language model that achieves performance comparable to GPT4-Turbo in code-specific tasks. Specifically, DeepSeek-Coder-V2 is further pre-trained from an intermediate checkpoint of DeepSeek-V2 with additional 6 trillion tokens",
            image = Res.drawable.ic_deep_seek
        )
    )

    val alibabaCloud = listOf(
        TextModel(
            title = "Qwen1.5-72B-Chat",
            provider = "Alibaba Cloud",
            description = "Qwen1.5 is the beta version of Qwen2, a transformer-based decoder-only language model pretrained on a large amount of data. In comparison with the previous released Qwen",
            image = Res.drawable.ic_alibaba_cloud
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