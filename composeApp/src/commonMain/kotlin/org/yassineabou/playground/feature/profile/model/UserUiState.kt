package org.yassineabou.playground.feature.profile.model

import llms.composeapp.generated.resources.Res
import llms.composeapp.generated.resources.profile_image
import org.jetbrains.compose.resources.DrawableResource

data class UserUiState(
    val id: String,
    val name: String,
    val thumbnailImage: DrawableResource?,
)

val prototypeUser = UserUiState(
    id = "0",
    name = "Prototype User",
    thumbnailImage = Res.drawable.profile_image,
)