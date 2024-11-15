package org.yassineabou.playground.feature.profile.model

data class UserUiState(
    val id: String,
    val name: String,
    val thumbnailUrl: String?,
)

val prototypeUser = UserUiState(
    id = "0",
    name = "Prototype User",
    thumbnailUrl = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/cc242d6c-f960-4274-aa1d-f22a71e705ef/original=true,quality=90/9173928.jpeg",
)