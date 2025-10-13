package org.yassineabou.llms.feature.imagine.data.model

import kotlinx.serialization.Serializable

@Serializable
data class PollinationsImageRequest(
    val prompt: String,
    val model: String,
    val width: Int = 1024,
    val height: Int = 1024,
    val seed: Long? = null,
    val nologo: Boolean = false,
    val private: Boolean = false,
    val enhance: Boolean = false,
    val safe: Boolean = false,
    val referrer: String? = null
    // You can add the 'image' parameter here if you plan to support image-to-image
    // val image: String? = null
)
