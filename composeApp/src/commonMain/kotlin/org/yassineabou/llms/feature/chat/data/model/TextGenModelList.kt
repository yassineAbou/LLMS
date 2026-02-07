package org.yassineabou.llms.feature.chat.data.model

import androidx.compose.runtime.Immutable

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
            modelName = "nova-fast",
            efficiency = "~66.7K/pollen"
        ),
        TextModel(
            title = "Gemini 2.5 Flash Lite",
            modelName = "gemini-fast",
            efficiency = "~3.6K/pollen"
        ),
        TextModel(
            title = "Mistral Small 3.2",
            modelName = "mistral",
            efficiency = "~3.2K/pollen"
        )
    )

    val highEfficiencyModels = listOf(
        TextModel(
            title = "Qwen3 Coder 30B",
            modelName = "qwen-coder",
            efficiency = "~1.4K/pollen"
        ),
        TextModel(
            title = "xAI Grok 4 Fast",
            modelName = "grok",
            efficiency = "~950/pollen"
        ),
        TextModel(
            title = "GPT-5 Mini",
            modelName = "openai",
            efficiency = "~800/pollen"
        ),
        TextModel(
            title = "Perplexity Sonar",
            modelName = "perplexity-fast",
            efficiency = "~750/pollen"
        ),
        TextModel(
            title = "GPT-5 Nano",
            modelName = "openai-fast",
            efficiency = "~650/pollen"
        )
    )

    val balancedModels = listOf(
        TextModel(
            title = "Gemini 3 Flash",
            modelName = "gemini",
            efficiency = "~400/pollen"
        ),
        TextModel(
            title = "DeepSeek V3.2",
            modelName = "deepseek",
            efficiency = "~300/pollen"
        ),
        TextModel(
            title = "Gemini 2.5 Pro",
            modelName = "gemini-legacy",
            efficiency = "~150/pollen"
        ),
        TextModel(
            title = "Perplexity Sonar Reasoning",
            modelName = "perplexity-reasoning",
            efficiency = "~150/pollen"
        ),
        TextModel(
            title = "Kimi K2 Thinking",
            modelName = "kimi",
            efficiency = "~100/pollen"
        ),
        TextModel(
            title = "GLM-4.7",
            modelName = "glm",
            efficiency = "~100/pollen"
        )
    )

    val premiumModels = listOf(
        TextModel(
            title = "GPT-5.2",
            modelName = "openai-large",
            efficiency = "~90/pollen"
        ),
        TextModel(
            title = "Claude Haiku 4.5",
            modelName = "claude-fast",
            efficiency = "~50/pollen"
        ),
        TextModel(
            title = "MiniMax M2.1",
            modelName = "minimax",
            efficiency = "~45/pollen"
        ),
        TextModel(
            title = "Claude Sonnet 4.5",
            modelName = "claude",
            efficiency = "~25/pollen"
        ),
        TextModel(
            title = "Gemini 3 Pro",
            modelName = "gemini-large",
            efficiency = "~25/pollen"
        ),
        TextModel(
            title = "Claude Opus 4.5",
            modelName = "claude-large",
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