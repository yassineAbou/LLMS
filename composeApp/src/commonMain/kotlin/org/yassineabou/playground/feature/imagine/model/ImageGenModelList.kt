package org.yassineabou.playground.feature.imagine.model

import androidx.compose.runtime.Immutable

@Immutable
object ImageGenModelList {


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

    val generalist = listOf(
        ImageModel(
            title = "Flux.1 Compact",
            isNsfw = true,
            description = "FLUX.1 [schnell] is a 12 billion parameter rectified flow transformer capable of generating images from text descriptions. For more information, please read our blog post. https://blackforestlabs.ai/",
            urlExamples = Flux_1_Compact_Url_Examples.shuffled()
        ),
        ImageModel(
            title = "AlbedoBase XL",
            description = "SDXL Model that doesn't require a refiner.",
            urlExamples = AlbedoBase_XL_Url_Examples.shuffled()
        ),
        ImageModel(
            title = "Blank Canvas XL",
            isNsfw = true,
            description = "Another model from RCNZ, this one is a true generalist, able to do realism, anime, and cartoons",
            urlExamples = Blank_Canvas_XL_Url_Examples.shuffled()
        ),
        ImageModel(
            title = "DreamShaper XL",
            isNsfw = true,
            description = "DreamShaper is a general purpose SD model that aims at doing everything well, photos, art, anime, manga. It's designed to go against other general purpose models and pipelines like Midjourney and DALL-E.",
            urlExamples = DreamShaper_XL_Url_Examples.shuffled()
        )
    )


    val realistic = listOf(
        ImageModel(
            title = "Ultraspice",
            isNsfw = true,
            description = "Ultraspice is a highly realistic SDXL model.",
            urlExamples = Ultraspice_Url_Examples.shuffled()
        ),
        ImageModel(
            title = "NatViS",
            isNsfw = true,
            description = "Realistic finetune of SDXL, with an emphasis on natural language prompting. Can produce both SFW and NSFW images.",
            urlExamples = NatViS_Url_Examples.shuffled()
        ),
        ImageModel(
            title = "SwamPonyXL",
            isNsfw = true,
            description = "Realistic finetune of Pony Diffusion V6, with an emphasis on asian likeness.",
            urlExamples = Swam_Pony_XL_Url_Examples.shuffled()
        ),
        ImageModel(
            title = "CyberRealistic Pony",
            isNsfw = true,
            description = "Cyberrealistic Pony is a semi-realistic Pony model capable of SFW and NSFW portraits as well as scenery.",
            urlExamples = Cyberrealistic_Pony_Url_Examples.shuffled()
        ),
        ImageModel(
            title = "Pony Realism",
            isNsfw = true,
            description = "Pony Realism is a realistic Pony model that is good at faces for medium-close distances, skin detail, and prompt adherence",
            urlExamples = Pony_Realism_Url_Example.shuffled()
        ),
        ImageModel(
            title = "ICBINP XL",
            description = "The SDXL follow up to ICBINP, now with higher resolution and better realism",
            urlExamples = ICBINP_XL_Url_Examples.shuffled()
        ),
        ImageModel(
            title = "Juggernaut XL",
            isNsfw = true,
            description = "Very popular realistic model",
            urlExamples = Juggernaut_XL_Url_Examples.shuffled()
        )
    )


    val anime = listOf(
        ImageModel(
            title = "Nova Anime XL",
            isNsfw = true,
            description = "Anime 2d/2.5d model, emphasis on detailed fur, scales, feathers, etc.",
            urlExamples = Nova_Anime_XL_Url_Examples.shuffled()
        ),
        ImageModel(
            title = "HolyMix ILXL",
            isNsfw = true,
            description = "HolyMix Illustrious XL is a high-contrast, flat shading anime model. It supports multiple characters in the same image, as well as many more artist tags and characters as compared to Pony models.",
            urlExamples = HolyMix_ILXL_URl_Examples.shuffled()
        ),
        ImageModel(
            title = "noob v pencil XL",
            isNsfw = true,
            description = "Vibrant anime-style images. Based on NoobAI-XL",
            urlExamples = Noob_V_Pencil_XL_Url_Examples.shuffled()
        ),
        ImageModel(
            title = "Prefect Pony",
            isNsfw = true,
            description = "Anime Pony model with an emphasis on NSFW and LoRA support",
            urlExamples = Prefect_Pony_Url_Examples.shuffled()
        ),
        ImageModel(
            title = "AAM XL",
            description = "Anime screencap model, brought to you by the creator of DreamShaper",
            urlExamples = AAM_XL_Url_Examples.shuffled()
        ),
        ImageModel(
            title = "Quiet Goodnight XL",
            description = "SDXL Model for anime, bought to you from the maker of ICBINP",
            urlExamples = Quiet_Goodnight_Xl_Url_Examples.shuffled()
        ),
        ImageModel(
            title = "WAI-ANI-NSFW-PONYXL",
            isNsfw = true,
            description = "WAI-ANI-NSFW-PONYXL is a Pony finetuned for NSFW anime-style generations",
            urlExamples = WAI_ANI_NSFW_PONYXL_Url_Examples.shuffled()
        ),
        ImageModel(
            title = "WAI-CUTE Pony",
            isNsfw = true,
            description = "Anime Pony model, with an emphasis on cute female figures",
            urlExamples = WAI_CUTE_Pony_Url_Examples.shuffled()
        ),
        ImageModel(
            title = "AMPonyXL",
            isNsfw = true,
            description = "Anime model based on Pony Diffusion XL - remember to use the score prompts to get this to go properly",
            urlExamples = AM_Pony_XL_Url_Examples.shuffled()
        ),
        ImageModel(
            title = "Illust Diffusion XL",
            description = "Anime Illustration Design XL, or AIDXL, is a model dedicated to generating stylized anime illustrations. It has over 800 (with more and more updates) built-in illustration styles, which are triggered by specific trigger words (see Appendix A). Please use AIDXL negative embedding model to boost generation quality",
            urlExamples = Illust_Diffusion_XL_Url_Examples.shuffled()
        ),
    )

    val threeD = listOf(
        ImageModel(
            title = "BlenderMix Pony",
            isNsfw = true,
            description = "This PonyXL checkpoint should create a blender animation style look. Model was merged using my loras (Fugtrup, Nyl2, RadRoachHD) and AutismMix. I also added a tiny pinch of DucHaiten-GameArt to add some more consistency for 3d generations.",
            urlExamples = Blender_Mix_Pony_Url_Examples.shuffled()
        ),
        ImageModel(
            title = "DucHaiten GameArt (Unreal) Pony",
            isNsfw = true,
            description = "This is a 3D PonyXL model, based on the AAA game quality of Unreal Engine 5",
            urlExamples = DucHaiten_GameArt_Unreal_Pony_Url_Examples.shuffled()
        ),
        ImageModel(
            title = "TUNIX Pony",
            isNsfw = true,
            description = "Semi-realistic stylized PonyXL finetune",
            urlExamples = TUNIX_Pony_Url_Examples.shuffled()
        ),
        ImageModel(
            title = "Fustercluck",
            description = "SDXL Model for cartoony style. If it's not cartoony enough, you may need to add 'anime, cartoon' to the front of the positive prompt to push the image in the right direction",
            urlExamples = Fustercluck_Url_Examples.shuffled()
        )
    )


    val comic = listOf(
        ImageModel(
            title = "Cheyenne",
            isNsfw = true,
            description = "A model for European Comic lovers.",
            urlExamples = Cheyenne_Url_Examples.shuffled()
        )
    )

    val furry = listOf(
        ImageModel(
            title = "Nova Furry Pony",
            isNsfw = true,
            description = "Stylized 2d/2.5d furry/anthro model, emphasis on detailed fur, scales, feathers, etc.",
            urlExamples = Nova_Furry_Pony_Url_Examples.shuffled()
        ),
        ImageModel(
            title = "Pony Diffusion XL",
            isNsfw = true,
            description = "Pony Diffusion V6 is a versatile SDXL finetune capable of producing stunning SFW and NSFW visuals of various anthro, feral, or humanoids species and their interactions based on simple natural language prompts.",
            urlExamples = Pony_Diffusion_XL_Url_Examples.shuffled()
        )
    )

}