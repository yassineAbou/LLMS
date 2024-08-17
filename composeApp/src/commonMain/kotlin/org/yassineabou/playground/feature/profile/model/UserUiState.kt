package org.yassineabou.playground.feature.profile.model

data class UserUiState(
    val id: String,
    val name: String,
    val thumbnailUrl: String?,
)

val prototypeUser = UserUiState(
    id = "0",
    name = "Prototype User",
    thumbnailUrl = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/cc242d6c-f960-4274-aa1d-f22a71e705ef/anim=false,width=450/2DD7DD35D931B2293D7B08F39CC371.jpeg",
)