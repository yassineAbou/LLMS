package org.yassineabou.playground.feature.chat.model

import llms.composeapp.generated.resources.Res
import llms.composeapp.generated.resources.ic_SicariusSicariiStuff
import llms.composeapp.generated.resources.ic_ai21labs
import llms.composeapp.generated.resources.ic_alibaba_cloud
import llms.composeapp.generated.resources.ic_anthracite_org
import llms.composeapp.generated.resources.ic_cohereforai
import llms.composeapp.generated.resources.ic_deep_seek
import llms.composeapp.generated.resources.ic_google
import llms.composeapp.generated.resources.ic_hugging_face_h4
import llms.composeapp.generated.resources.ic_meta
import llms.composeapp.generated.resources.ic_tii
import org.jetbrains.compose.resources.DrawableResource
import org.yassineabou.playground.feature.imagine.model.IdGenerator


data class TextModel(
    val id: Int = IdGenerator().generatedId(),
    val title: String,
    val image: DrawableResource,
    val description: String,
    val provider: String
)

val textGenModelList = listOf(
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
    ),
    TextModel(
        title = "Llama-3.1-8B",
        provider = "Meta",
        description = "The Meta Llama 3.1 collection of multilingual large language models (LLMs) is a collection of pretrained and instruction tuned generative models in 8B, 70B and 405B sizes (text in/text out). The Llama 3.1 instruction tuned text only models (8B, 70B, 405B) are optimized for multilingual dialogue use cases and outperform many of the available open source and closed chat models on common industry benchmarks.",
        image = Res.drawable.ic_meta,
    ),
    TextModel(
        title = "Llama-3.1-405B",
        provider = "Meta",
        description = "The Meta Llama 3.1 collection of multilingual large language models (LLMs) is a collection of pretrained and instruction tuned generative models in 8B, 70B and 405B sizes (text in/text out). The Llama 3.1 instruction tuned text only models (8B, 70B, 405B) are optimized for multilingual dialogue use cases and outperform many of the available open source and closed chat models on common industry benchmarks.",
        image = Res.drawable.ic_meta,
    ),
    TextModel(
        title = "Gemma-2-9B",
        provider = "Google",
        description = "Google’s Gemma 2 model is available in three sizes, 2B, 9B and 27B, featuring a brand new architecture designed for class leading performance and efficiency. At 27 billion parameters, Gemma 2 delivers performance surpassing models more than twice its size in benchmarks. This breakthrough efficiency sets a new standard in the open model landscape.",
        image = Res.drawable.ic_google,
    ),
    TextModel(
        title = "Gemma-2-27B",
        provider = "Google",
        description = "Google’s Gemma 2 model is available in three sizes, 2B, 9B and 27B, featuring a brand new architecture designed for class leading performance and efficiency. At 27 billion parameters, Gemma 2 delivers performance surpassing models more than twice its size in benchmarks. This breakthrough efficiency sets a new standard in the open model landscape.",
        image = Res.drawable.ic_google,
    ),
    TextModel(
        title = "Magnum-V4-9B",
        provider = "Anthracite Org",
        description = "This is a series of models designed to replicate the prose quality of the Claude 3 models, specifically Sonnet and Opus. This model is fine-tuned on top of gemma 2 9b (chatML'ified).",
        image = Res.drawable.ic_anthracite_org,
    ),
    TextModel(
        title = "Magnum-V4-12B",
        provider = "Anthracite Org",
        description = "This is a series of models designed to replicate the prose quality of the Claude 3 models, specifically Sonnet and Opus. This model is fine-tuned on top of mistralai/Mistral-Nemo-Instruct-2407.",
        image = Res.drawable.ic_anthracite_org,
    ),
    TextModel(
        title = "Magnum-V4-22B",
        provider = "Anthracite Org",
        description = "This is a series of models designed to replicate the prose quality of the Claude 3 models, specifically Sonnet and Opus. This model is fine-tuned on top of Mistral-Small-Instruct-2409.",
        image = Res.drawable.ic_anthracite_org,
    ),
    TextModel(
        title = "Magnum-V4-27B",
        provider = "Anthracite Org",
        description = "This is a series of models designed to replicate the prose quality of the Claude 3 models, specifically Sonnet and Opus. This model is fine-tuned on top of Gemma 27b (chatML'ified).",
        image = Res.drawable.ic_anthracite_org,
    ),
    TextModel(
        title = "Magnum-V4-72B",
        provider = "Anthracite Org",
        description = "This is a series of models designed to replicate the prose quality of the Claude 3 models, specifically Sonnet and Opus. experimental because trained on top of instruct; but turned out amazing; hence code named magnum-alter, the original model that kickstarted the v4 family. This model is fine-tuned on top of Qwen2.5-72B-Instruct.",
        image = Res.drawable.ic_anthracite_org
    ),
    TextModel(
        title = "Magnum-V4-123B",
        provider = "Anthracite Org",
        description = "This is a series of models designed to replicate the prose quality of the Claude 3 models, specifically Sonnet and Opus. This model is fine-tuned on top of mistralai/Mistral-Large-Instruct-2407.",
        image = Res.drawable.ic_anthracite_org
    ),
    TextModel(
        title = "Jamba-1.5-Mini",
        provider = "AI21 Lab",
        description = "The AI21 Jamba 1.5 family of models is state-of-the-art, hybrid SSM-Transformer instruction following foundation models. The Jamba models are the most powerful & efficient long-context models on the market, which deliver up to 2.5X faster inference than leading models of comparable sizes.",
        image = Res.drawable.ic_ai21labs
    ),
    TextModel(
        title = "Phi-3.5-Mini-128K-Uncensored",
        provider = "SicariusSicariiStuff",
        description = "This is the basic model, no additional data was used except my uncensoring protocol.",
        image = Res.drawable.ic_SicariusSicariiStuff
    ),
    TextModel(
        title = "Zephyr 141B-A39B",
        provider = "Hugging Face H4",
        description = "Zephyr is a series of language models that are trained to act as helpful assistants. Zephyr 141B-A39B is the latest model in the series, and is a fine-tuned version of mistral-community/Mixtral-8x22B-v0.1 that was trained using a novel alignment algorithm called Odds Ratio Preference Optimization (ORPO) with 7k instances for 1.3 hours on 4 nodes of 8 x H100s",
        image = Res.drawable.ic_hugging_face_h4
    ),
    TextModel(
        title = "Qwen1.5-72B-Chat",
        provider = "Alibaba Cloud",
        description = "Qwen1.5 is the beta version of Qwen2, a transformer-based decoder-only language model pretrained on a large amount of data. In comparison with the previous released Qwen",
        image = Res.drawable.ic_alibaba_cloud
    ),
    TextModel(
        title = "DeepSeek-Coder-V2",
        provider = "DeepSeek",
        description = "We present DeepSeek-Coder-V2, an open-source Mixture-of-Experts (MoE) code language model that achieves performance comparable to GPT4-Turbo in code-specific tasks. Specifically, DeepSeek-Coder-V2 is further pre-trained from an intermediate checkpoint of DeepSeek-V2 with additional 6 trillion tokens",
        image = Res.drawable.ic_deep_seek
    ),
    TextModel(
        title = "Falcon-180B",
        provider = "TII",
        description = "Falcon-180B is a 180B parameters causal decoder-only model built by TII and trained on 3,500B tokens of RefinedWeb enhanced with curated corpora. It is made available under the Falcon-180B TII License and Acceptable Use Policy.",
        image = Res.drawable.ic_tii
    ),
    TextModel(
        title = "Command R+ (104B)",
        provider = "CohereForAI",
        description = "C4AI Command R+ is an open weights research release of a 104B billion parameter model with highly advanced capabilities, this includes Retrieval Augmented Generation (RAG) and tool use to automate sophisticated tasks. The tool use in this model generation enables multi-step tool use which allows the model to combine multiple tools over multiple steps to accomplish difficult tasks. C4AI Command R+ is a multilingual model evaluated in 10 languages for performance: English, French, Spanish, Italian, German, Brazilian Portuguese, Japanese, Korean, Arabic, and Simplified Chinese. Command R+ is optimized for a variety of use cases including reasoning, summarization, and question answering.",
        image = Res.drawable.ic_cohereforai
    )

)
