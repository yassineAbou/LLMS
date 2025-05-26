package org.yassineabou.llms.feature.imagine.model

import kotlinx.serialization.Serializable

@Serializable
data class ImageGenerationRequest(
    val prompt: String,
    val seed: Int? = null,
    val steps: Int? = null,
    val guidance_scale: Float? = null,
    val cfg: Float? = null,
    val edit_prompts: List<String>? = null,
    val id_image_b64: String? = null,
    val control_image_b64: String? = null,
    val infusenet_guidance_start: Float? = null,
    val infusenet_guidance_end: Float? = null,
    val infusenet_conditioning_scale: Float? = null
)

