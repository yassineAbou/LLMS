package org.yassineabou.llms.feature.chat.data.model

import androidx.compose.runtime.Immutable
import llms.composeapp.generated.resources.*

@Immutable
data class TextModelSection(
    val title: String,
    val subtitle: String,
    val models: List<TextModel>
)

@Immutable
object TextGenModelList {

    val defaultModel: TextModel by lazy { ultraEfficientModels.first() }

    val allModels: List<TextModel> by lazy {
        ultraEfficientModels + highEfficiencyModels + balancedModels + premiumModels
    }

    val ultraEfficientModels = listOf(
        TextModel(
            title = "Amazon Nova Micro",
            provider = "amazon",
            modelName = "nova-fast",
            description = "Ultra Fast & Ultra Cheap",
            image = Res.drawable.ic_llm7,
            efficiency = "~66.7K/pollen"
        ),
        TextModel(
            title = "Gemini 2.5 Flash Lite",
            provider = "google",
            modelName = "gemini-fast",
            description = "Ultra Fast & Cost-Effective",
            image = Res.drawable.ic_gemini,
            efficiency = "~3.6K/pollen"
        ),
        TextModel(
            title = "Mistral Small 3.2",
            provider = "mistral",
            modelName = "mistral",
            description = "Efficient 24B Mistral model",
            image = Res.drawable.ic_mistral,
            efficiency = "~3.2K/pollen"
        )
    )

    val highEfficiencyModels = listOf(
        TextModel(
            title = "Qwen3 Coder 30B",
            provider = "qwen",
            modelName = "qwen-coder",
            description = "Specialized for Code Generation",
            image = Res.drawable.ic_qwen,
            efficiency = "~1.4K/pollen"
        ),
        TextModel(
            title = "xAI Grok 4 Fast",
            provider = "xai",
            modelName = "grok",
            description = "High Speed & Real-Time",
            image = Res.drawable.ic_grok,
            efficiency = "~950/pollen"
        ),
        TextModel(
            title = "GPT-5 Mini",
            provider = "openai",
            modelName = "openai",
            description = "Fast & Balanced",
            image = Res.drawable.ic_openai,
            efficiency = "~800/pollen"
        ),
        TextModel(
            title = "Perplexity Sonar",
            provider = "perplexity",
            modelName = "perplexity-fast",
            description = "Fast with Web Search",
            image = Res.drawable.ic_perplexity,
            efficiency = "~750/pollen"
        ),
        TextModel(
            title = "GPT-5 Nano",
            provider = "openai",
            modelName = "openai-fast",
            description = "Ultra Fast & Affordable",
            image = Res.drawable.ic_openai,
            efficiency = "~650/pollen"
        )
    )

    val balancedModels = listOf(
        TextModel(
            title = "Gemini 3 Flash",
            provider = "google",
            modelName = "gemini",
            description = "Pro-Grade Reasoning at Flash Speed",
            image = Res.drawable.ic_gemini,
            efficiency = "~400/pollen"
        ),
        TextModel(
            title = "DeepSeek V3.2",
            provider = "deepseek",
            modelName = "deepseek",
            description = "Efficient Reasoning & Agentic AI",
            image = Res.drawable.ic_deepseek,
            efficiency = "~300/pollen"
        ),
        TextModel(
            title = "Gemini 2.5 Pro",
            provider = "google",
            modelName = "gemini-legacy",
            description = "Stable Reasoning with 1M Context",
            image = Res.drawable.ic_gemini,
            efficiency = "~150/pollen"
        ),
        TextModel(
            title = "Perplexity Sonar Reasoning",
            provider = "perplexity",
            modelName = "perplexity-reasoning",
            description = "Advanced Reasoning with Web Search",
            image = Res.drawable.ic_perplexity,
            efficiency = "~150/pollen"
        ),
        TextModel(
            title = "Kimi K2 Thinking",
            provider = "moonshot",
            modelName = "kimi",
            description = "Deep Reasoning & Tool Orchestration",
            image = Res.drawable.ic_kimi,
            efficiency = "~100/pollen"
        ),
        TextModel(
            title = "GLM-4.7",
            provider = "zhipu",
            modelName = "glm",
            description = "Coding, Reasoning & Agentic Workflows",
            image = Res.drawable.ic_glm,
            efficiency = "~100/pollen"
        )
    )

    val premiumModels = listOf(
        TextModel(
            title = "GPT-5.2",
            provider = "openai",
            modelName = "openai-large",
            description = "Most Powerful & Intelligent ⭐",
            image = Res.drawable.ic_openai,
            efficiency = "~90/pollen"
        ),
        TextModel(
            title = "Claude Haiku 4.5",
            provider = "anthropic",
            modelName = "claude-fast",
            description = "Fast & Intelligent",
            image = Res.drawable.ic_claude,
            efficiency = "~50/pollen"
        ),
        TextModel(
            title = "MiniMax M2.1",
            provider = "minimax",
            modelName = "minimax",
            description = "Multi-Language & Agent Workflows",
            image = Res.drawable.ic_minmax,
            efficiency = "~45/pollen"
        ),
        TextModel(
            title = "Claude Sonnet 4.5",
            provider = "anthropic",
            modelName = "claude",
            description = "Most Capable & Balanced",
            image = Res.drawable.ic_claude,
            efficiency = "~25/pollen"
        ),
        TextModel(
            title = "Gemini 3 Pro",
            provider = "google",
            modelName = "gemini-large",
            description = "Most Intelligent with 1M Context",
            image = Res.drawable.ic_gemini,
            efficiency = "~25/pollen"
        ),
        TextModel(
            title = "Claude Opus 4.5",
            provider = "anthropic",
            modelName = "claude-large",
            description = "Most Intelligent Model ⭐",
            image = Res.drawable.ic_claude,
            efficiency = "~20/pollen"
        )
    )

    val groupedModels: List<TextModelSection> by lazy {
        listOf(
            TextModelSection(
                title = "🔥 Best Value",
                subtitle = "3,000+ responses per pollen",
                models = ultraEfficientModels
            ),
            TextModelSection(
                title = "⚡ High Efficiency",
                subtitle = "500-3,000 responses per pollen",
                models = highEfficiencyModels
            ),
            TextModelSection(
                title = "💬 Balanced",
                subtitle = "100-500 responses per pollen",
                models = balancedModels
            ),
            TextModelSection(
                title = "⭐ Premium",
                subtitle = "Highest quality",
                models = premiumModels
            )
        )
    }
}