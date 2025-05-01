package org.yassineabou.playground.feature.imagine.model

import androidx.compose.runtime.Immutable
import llms.composeapp.generated.resources.Res
import llms.composeapp.generated.resources.ic_bytedance
import llms.composeapp.generated.resources.ic_chroma
import llms.composeapp.generated.resources.ic_hi_dream
import llms.composeapp.generated.resources.ic_snapchat

@Immutable
object ImageGenModelList {

    val newImageModel = listOf(
        ImageModel(
            title = "Hidream",
            description = "No description provided",
            image = Res.drawable.ic_hi_dream,
            isNsfw = true,
            urlExamples = Flux_1_Compact_Url_Examples.shuffled()
        ),
        ImageModel(
            title = "Chroma",
            description = "No description provided",
            image = Res.drawable.ic_chroma,
            isNsfw = true,
            urlExamples = Flux_1_Compact_Url_Examples.shuffled()
        ),
        ImageModel(
            title = "StableFlow",
            description = "Training free image editing with FLUX.1 [dev]",
            image = Res.drawable.ic_snapchat,
            isNsfw = true,
            urlExamples = Flux_1_Compact_Url_Examples.shuffled()
        ),
        ImageModel(
            title = "Infiniteyou",
            description = "No description provided",
            image = Res.drawable.ic_bytedance,
            isNsfw = true,
            urlExamples = Flux_1_Compact_Url_Examples.shuffled()
        ),
    )

    val inspiration = listOf(
        Flux_1_Compact_Url_Examples,
        AlbedoBase_XL_Url_Examples,
        Blank_Canvas_XL_Url_Examples,
        DreamShaper_XL_Url_Examples,
        Pony_Realism_Url_Example,
        Ultraspice_Url_Examples,
        ICBINP_XL_Url_Examples,
        Swam_Pony_XL_Url_Examples,
        Juggernaut_XL_Url_Examples,
        AAM_XL_Url_Examples,
        Quiet_Goodnight_Xl_Url_Examples,
        WAI_ANI_NSFW_PONYXL_Url_Examples,
        WAI_CUTE_Pony_Url_Examples,
        AM_Pony_XL_Url_Examples,
        Illust_Diffusion_XL_Url_Examples,
        Blender_Mix_Pony_Url_Examples,
        DucHaiten_GameArt_Unreal_Pony_Url_Examples,
        TUNIX_Pony_Url_Examples,
        Fustercluck_Url_Examples,
        Cheyenne_Url_Examples,
        Nova_Furry_Pony_Url_Examples,
        Pony_Diffusion_XL_Url_Examples,
        Cyberrealistic_Pony_Url_Examples,
        NatViS_Url_Examples,
        Prefect_Pony_Url_Examples,
        Nova_Anime_XL_Url_Examples,
        Noob_V_Pencil_XL_Url_Examples,
        HolyMix_ILXL_URl_Examples
    )
        .flatten()
        .shuffled()



}