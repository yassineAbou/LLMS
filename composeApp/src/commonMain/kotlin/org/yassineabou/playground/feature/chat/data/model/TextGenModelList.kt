package org.yassineabou.playground.feature.chat.data.model

import llms.composeapp.generated.resources.Res
import llms.composeapp.generated.resources.ic_gemma

object TextGenModelList {

    val gemma = listOf(
        TextModel(
            title = "Fallen-Gemma3-27B",
            name = "koboldcpp/TheDrummer_Fallen-Gemma3-27B-v1-Q4_K_L",
            description = "Fallen Gemma3 27B v1 is an evil tune of Gemma 3 27B but it is not a complete decensor. Evil tunes knock out the positivity and may enjoy torturing you and humanity. Vision still works and it has something to say about the crap you feed it.",
            image = Res.drawable.ic_gemma
        ),
        TextModel(
            title = "Fallen_Amoral_Gemma3-12B",
            name = "koboldcpp/Fallen_Amoral_Gemma3-12B-v1.Q8_0 ",
            description = "Is a 12 billion parameter language model based on the Gemma architecture. It's fine-tuned with a \"Fallen_Amoral\" focus, suggesting suitability for darker themes or less restricted creative writing and roleplay.",
            image = Res.drawable.ic_gemma
        ),
        TextModel(
            title = "Google_Gemma-3-1B",
            name = "koboldcpp/google_gemma-3-1b-it-Q4_K_M or koboldcpp/gemma-3-1b-it",
            description = "Lightweight 1B version for efficient multilingual chat applications. Instruction-tuned for dialogue tasks with strong benchmark performance across language understanding metrics.",
            image = Res.drawable.ic_gemma
        )
    )
}