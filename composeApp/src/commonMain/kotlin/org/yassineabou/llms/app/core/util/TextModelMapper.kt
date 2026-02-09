package org.yassineabou.llms.app.core.util

import org.yassineabou.llms.feature.chat.data.model.TextModel
import org.yassineabou.llms.feature.chat.data.model.TextModelResponse


object TextModelMapper {

    private val VENDOR_PREFIXES = listOf(
        "Google ", "OpenAI ", "Anthropic ", "Amazon ", "xAI ",
        "Moonshot ", "Z.ai ", "MiniMax ", "Perplexity "
    )

    private val SUFFIX_PATTERNS = listOf(
        "-search" to "Search",
        "-fast" to "Fast",
        "-large" to "Large",
        "-reasoning" to "Reasoning",
        "-coder" to "Coder",
        "-legacy" to "Legacy"
    )

    private val VERSION_REGEX = Regex("""\d+(?:\.\d+)?[a-z]?""", RegexOption.IGNORE_CASE)


    fun mapToTextModels(responses: List<TextModelResponse>): List<TextModel> {

        val filteredModels = responses.asSequence()
            .filter { model -> isValidTextModel(model) }
            .sortedBy { it.pricing!!.completionTextTokens }
            .toList()

        val baseTitleCounts = countBaseTitles(filteredModels)

        return filteredModels.map { model ->
            TextModel(
                title = generateTitle(model, baseTitleCounts),
                modelName = model.name,
                description = model.description
            )
        }
    }

    private fun isValidTextModel(model: TextModelResponse): Boolean {
        return !model.paidOnly &&
                !model.isSpecialized &&
                model.pricing?.completionTextTokens != null &&
                "audio" !in model.outputModalities
    }

    private fun countBaseTitles(models: List<TextModelResponse>): Map<String, Int> {
        return models
            .groupingBy { extractBaseTitle(it.description) }
            .eachCount()
    }

    private fun generateTitle(
        model: TextModelResponse,
        baseTitleCounts: Map<String, Int>
    ): String {
        val rawBaseTitle = extractBaseTitle(model.description)
        val cleanBaseTitle = removeVendorPrefix(rawBaseTitle)
        val isDuplicate = (baseTitleCounts[rawBaseTitle] ?: 0) > 1

        return if (isDuplicate) {
            val suffix = extractSuffix(model.name)
            createSimplifiedTitle(cleanBaseTitle, suffix)
        } else {
            cleanBaseTitle
        }
    }

    private fun extractBaseTitle(description: String): String {
        return description.substringBefore(" - ").trim()
    }

    private fun removeVendorPrefix(title: String): String {
        for (prefix in VENDOR_PREFIXES) {
            if (title.startsWith(prefix, ignoreCase = true)) {
                return title.removePrefix(prefix).trim()
            }
        }
        return title
    }


    private fun extractSuffix(modelName: String): String {
        for ((pattern, suffix) in SUFFIX_PATTERNS) {
            if (modelName.contains(pattern, ignoreCase = true)) {
                return suffix
            }
        }

        val lastPart = modelName.substringAfterLast("-", "")
        return if (lastPart.isNotEmpty()) {
            lastPart.replaceFirstChar { it.uppercase() }
        } else {
            ""
        }
    }

    private fun createSimplifiedTitle(baseTitle: String, suffix: String): String {
        val words = baseTitle.split(" ")

        val versionIndex = words.indexOfFirst { word ->
            word.matches(VERSION_REGEX)
        }

        return if (versionIndex >= 0) {
            val nameWithVersion = words.take(versionIndex + 1).joinToString(" ")
            "$nameWithVersion $suffix"
        } else {
            "$baseTitle $suffix"
        }
    }
}