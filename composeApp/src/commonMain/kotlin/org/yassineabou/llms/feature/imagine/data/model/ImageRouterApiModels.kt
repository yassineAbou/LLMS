package org.yassineabou.llms.feature.imagine.data.model

import kotlinx.serialization.Serializable

data class PollinationsImageRequest(
    val prompt: String,
    val model: String = "zimage",
    val width: Int = 1024,
    val height: Int = 1024,
    val seed: Int? = null,
    val enhance: Boolean = false,
    val negativePrompt: String? = null,
    val safe: Boolean = false,
    val quality: String? = null,        // gptimage only: "low", "medium", "high", "hd"
    val image: String? = null,          // Reference image URL for image-to-image
    val transparent: Boolean = false,   // gptimage only: transparent background
)