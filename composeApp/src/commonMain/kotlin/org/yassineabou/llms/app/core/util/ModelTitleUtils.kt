package org.yassineabou.llms.app.core.util

/**
 * Shared utility object for cleaning and formatting model titles.
 * Used by both TextModelMapper and ImageModelMapper.
 */
object ModelTitleUtils {

    // Vendor prefixes to remove from model titles
    private val VENDOR_PREFIXES = listOf(
        "Google ", "OpenAI ", "Anthropic ", "Amazon ", "xAI ",
        "Moonshot ", "Z.ai ", "MiniMax ", "Perplexity ", "ByteDance ",
        "BytePlus ", "Alibaba "
    )

    // Suffix patterns to detect from model names for differentiation
    private val SUFFIX_PATTERNS = listOf(
        "-search" to "Search",
        "-fast" to "Fast",
        "-large" to "Large",
        "-reasoning" to "Reasoning",
        "-coder" to "Coder",
        "-legacy" to "Legacy",
        "-pro" to "Pro",
        "-mini" to "Mini"
    )

    // Regex pattern for version detection (e.g., "2.5", "3", "4.5", "4o", "1.5")
    private val VERSION_REGEX = Regex("""\d+(?:\.\d+)?[a-z]?""", RegexOption.IGNORE_CASE)

    /**
     * Extracts base title from description (before " - ").
     * e.g., "Google Gemini 2.5 Flash Lite - Ultra Fast" → "Google Gemini 2.5 Flash Lite"
     */
    fun extractBaseTitle(description: String): String {
        return description.substringBefore(" - ").trim()
    }

    /**
     * Removes vendor prefix from title.
     * e.g., "Google Gemini 2.5 Flash Lite" → "Gemini 2.5 Flash Lite"
     */
    fun removeVendorPrefix(title: String): String {
        for (prefix in VENDOR_PREFIXES) {
            if (title.startsWith(prefix, ignoreCase = true)) {
                return title.removePrefix(prefix).trim()
            }
        }
        return title
    }

    /**
     * Extracts suffix from model name to differentiate duplicates.
     * e.g., "gemini-fast" → "Fast", "gemini-search" → "Search"
     */
    fun extractSuffix(modelName: String): String {
        for ((pattern, suffix) in SUFFIX_PATTERNS) {
            if (modelName.contains(pattern, ignoreCase = true)) {
                return suffix
            }
        }

        // Fallback: extract last part after hyphen and capitalize
        val lastPart = modelName.substringAfterLast("-", "")
        return if (lastPart.isNotEmpty()) {
            lastPart.replaceFirstChar { it.uppercase() }
        } else {
            ""
        }
    }

    /**
     * Creates simplified title for duplicate models.
     * Takes name + version + suffix, dropping variant names.
     * e.g., "Gemini 2.5 Flash Lite" + "Search" → "Gemini 2.5 Search"
     */
    fun createSimplifiedTitle(baseTitle: String, suffix: String): String {
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