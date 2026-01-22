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

    /**
     * All text models organized by cost efficiency (most responses per pollen first)
     */
    val allModels: List<TextModel> by lazy {
        ultraEfficientModels + highEfficiencyModels + balancedModels + premiumModels
    }

    // Default model - best value
    val defaultModel: TextModel by lazy {
        ultraEfficientModels.first()
    }

    /**
     * Ultra Efficient Models: 3,000+ responses per pollen
     */
    val ultraEfficientModels = listOf(
        TextModel(
            title = "Amazon Nova Micro",
            provider = "amazon",
            modelName = "amazon-nova-micro",
            description = "Ultra-efficient text generation. ~66.7K responses/pollen 🔥",
            image = Res.drawable.ic_llm7,
            efficiency = "~66.7K/pollen"
        ),
        TextModel(
            title = "Gemini 2.5 Flash Lite",
            provider = "google",
            modelName = "gemini-2.5-flash-lite",
            description = "Lightweight and fast Gemini. ~3.6K responses/pollen",
            image = Res.drawable.ic_gemini,
            efficiency = "~3.6K/pollen"
        ),
        TextModel(
            title = "Mistral Small 3.2",
            provider = "mistral",
            modelName = "mistral",
            description = "Efficient 24B Mistral model. ~3.2K responses/pollen",
            image = Res.drawable.ic_mistral,
            efficiency = "~3.2K/pollen"
        )
    )

    /**
     * High Efficiency Models: 500-3,000 responses per pollen
     */
    val highEfficiencyModels = listOf(
        TextModel(
            title = "Qwen3 Coder 30B",
            provider = "qwen",
            modelName = "qwen-coder",
            description = "Coding-focused 30B model. ~1.4K responses/pollen",
            image = Res.drawable.ic_qwen,
            efficiency = "~1.4K/pollen"
        ),
        TextModel(
            title = "xAI Grok 4 Fast",
            provider = "xai",
            modelName = "grok-4-fast",
            description = "Fast reasoning model from xAI. ~950 responses/pollen",
            image = Res.drawable.ic_grok,
            efficiency = "~950/pollen"
        ),
        TextModel(
            title = "GPT-5 Mini",
            provider = "openai",
            modelName = "openai",
            description = "Compact GPT-5 variant. ~800 responses/pollen",
            image = Res.drawable.ic_openai,
            efficiency = "~800/pollen"
        ),
        TextModel(
            title = "Perplexity Sonar",
            provider = "perplexity",
            modelName = "perplexity-sonar",
            description = "Search-enhanced responses. ~750 responses/pollen",
            image = Res.drawable.ic_perplexity,
            efficiency = "~750/pollen"
        ),
        TextModel(
            title = "GPT-5 Nano",
            provider = "openai",
            modelName = "openai-fast",
            description = "Fastest GPT-5 variant. ~650 responses/pollen",
            image = Res.drawable.ic_openai,
            efficiency = "~650/pollen"
        )
    )

    /**
     * Balanced Models: 100-500 responses per pollen
     */
    val balancedModels = listOf(
        TextModel(
            title = "Gemini 3 Flash",
            provider = "google",
            modelName = "gemini-3-flash",
            description = "Fast multimodal Gemini. ~400 responses/pollen",
            image = Res.drawable.ic_gemini,
            efficiency = "~400/pollen"
        ),
        TextModel(
            title = "DeepSeek V3.2",
            provider = "deepseek",
            modelName = "deepseek-v3",
            description = "Advanced reasoning model. ~300 responses/pollen",
            image = Res.drawable.ic_deepseek,
            efficiency = "~300/pollen"
        ),
        TextModel(
            title = "Gemini 2.5 Pro",
            provider = "google",
            modelName = "gemini-2.5-pro",
            description = "Premium Gemini model. ~150 responses/pollen",
            image = Res.drawable.ic_gemini,
            efficiency = "~150/pollen"
        ),
        TextModel(
            title = "Perplexity Sonar Reasoning",
            provider = "perplexity",
            modelName = "perplexity-sonar-reasoning",
            description = "Enhanced reasoning with search. ~150 responses/pollen",
            image = Res.drawable.ic_perplexity,
            efficiency = "~150/pollen"
        ),
        TextModel(
            title = "Kimi K2 Thinking",
            provider = "moonshot",
            modelName = "kimi-k2-thinking",
            description = "Reasoning-focused model. ~100 responses/pollen",
            image = Res.drawable.ic_kimi,
            efficiency = "~100/pollen"
        ),
        TextModel(
            title = "GLM-4.7",
            provider = "zhipu",
            modelName = "glm-4.7",
            description = "Latest GLM model. ~100 responses/pollen",
            image = Res.drawable.ic_glm,
            efficiency = "~100/pollen"
        )
    )

    /**
     * Premium Models: Highest quality, fewer responses per pollen
     */
    val premiumModels = listOf(
        TextModel(
            title = "GPT-5.2",
            provider = "openai",
            modelName = "openai-large",
            description = "Most capable GPT model. ~90 responses/pollen ⭐",
            image = Res.drawable.ic_openai,
            efficiency = "~90/pollen"
        ),
        TextModel(
            title = "Claude Haiku 4.5",
            provider = "anthropic",
            modelName = "claude-haiku-4.5",
            description = "Fast Claude variant. ~50 responses/pollen",
            image = Res.drawable.ic_claude,
            efficiency = "~50/pollen"
        ),
        TextModel(
            title = "MiniMax M2.1",
            provider = "minimax",
            modelName = "minimax-m2.1",
            description = "Balanced quality model. ~45 responses/pollen",
            image = Res.drawable.ic_minmax,
            efficiency = "~45/pollen"
        ),
        TextModel(
            title = "Claude Sonnet 4.5",
            provider = "anthropic",
            modelName = "claude-sonnet-4.5",
            description = "Balanced Claude model. ~25 responses/pollen",
            image = Res.drawable.ic_claude,
            efficiency = "~25/pollen"
        ),
        TextModel(
            title = "Gemini 3 Pro",
            provider = "google",
            modelName = "gemini-3-pro",
            description = "Premium Gemini model. ~25 responses/pollen",
            image = Res.drawable.ic_gemini,
            efficiency = "~25/pollen"
        ),
        TextModel(
            title = "Claude Opus 4.5",
            provider = "anthropic",
            modelName = "claude-opus-4.5",
            description = "Most powerful Claude. ~20 responses/pollen ⭐",
            image = Res.drawable.ic_claude,
            efficiency = "~20/pollen"
        )
    )

    /**
     * Grouped models for UI display with section headers
     */
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