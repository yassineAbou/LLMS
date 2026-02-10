package org.yassineabou.llms.app.core.util

import org.yassineabou.llms.feature.imagine.data.model.ImageModel
import org.yassineabou.llms.feature.imagine.data.model.ImageModelResponse

/**
 * Utility object for mapping API image model responses to clean ImageModel objects.
 * Handles title extraction, vendor prefix removal, and duplicate differentiation.
 */
object ImageModelMapper {

    /**
     * Filters and maps API responses to ImageModel list.
     * - Excludes paid models
     * - Excludes video output models (only image output)
     * - Excludes models without image pricing info
     * - Sorts by price (cheapest first based on completionImageTokens)
     * - Cleans titles and handles duplicates
     */
    fun mapToImageModels(responses: List<ImageModelResponse>): List<ImageModel> {
        val filteredModels = responses.asSequence()
            .filter { model -> isValidImageModel(model) }
            .sortedBy { it.pricing!!.completionImageTokens }
            .toList()

        val baseTitleCounts = countBaseTitles(filteredModels)

        return filteredModels.map { model ->
            ImageModel(
                title = generateTitle(model.description, model.name, baseTitleCounts),
                description = model.description,
                modelName = model.name
            )
        }
    }

    /**
     * Validates if a model should be included in the available models list.
     * - Excludes paid_only models
     * - Excludes video output models
     * - Requires completionImageTokens pricing
     */
    private fun isValidImageModel(model: ImageModelResponse): Boolean {
        return !model.paidOnly &&
                model.outputModalities.contains("image") &&
                !model.outputModalities.contains("video") &&
                model.pricing?.completionImageTokens != null
    }

    private fun countBaseTitles(models: List<ImageModelResponse>): Map<String, Int> {
        return models
            .groupingBy { ModelTitleUtils.extractBaseTitle(it.description) }
            .eachCount()
    }

    /**
     * Generates a clean, unique title for a model using shared ModelTitleUtils.
     */
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