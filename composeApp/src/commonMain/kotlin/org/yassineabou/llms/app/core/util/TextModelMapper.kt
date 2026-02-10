package org.yassineabou.llms.app.core.util

import org.yassineabou.llms.feature.chat.data.model.TextModel
import org.yassineabou.llms.feature.chat.data.model.TextModelResponse


/**
 * Utility object for mapping API text model responses to clean TextModel objects.
 * Handles title extraction, vendor prefix removal, and duplicate differentiation.
 */
object TextModelMapper {

    /**
     * Filters and maps API responses to TextModel list.
     * - Excludes paid, specialized, audio output, and models without pricing
     * - Sorts by price (cheapest first)
     * - Cleans titles and handles duplicates
     */
    fun mapToTextModels(responses: List<TextModelResponse>): List<TextModel> {
        val filteredModels = responses.asSequence()
            .filter { model -> isValidTextModel(model) }
            .sortedBy { it.pricing!!.completionTextTokens }
            .toList()

        val baseTitleCounts = countBaseTitles(filteredModels)

        return filteredModels.map { model ->
            TextModel(
                title = generateTitle(model.description, model.name, baseTitleCounts),
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
            .groupingBy { ModelTitleUtils.extractBaseTitle(it.description) }
            .eachCount()
    }


    private fun generateTitle(
        description: String,
        modelName: String,
        baseTitleCounts: Map<String, Int>
    ): String {
        val rawBaseTitle = ModelTitleUtils.extractBaseTitle(description)
        val cleanBaseTitle = ModelTitleUtils.removeVendorPrefix(rawBaseTitle)
        val isDuplicate = (baseTitleCounts[rawBaseTitle] ?: 0) > 1

        return if (isDuplicate) {
            val suffix = ModelTitleUtils.extractSuffix(modelName)
            ModelTitleUtils.createSimplifiedTitle(cleanBaseTitle, suffix)
        } else {
            cleanBaseTitle
        }
    }
}