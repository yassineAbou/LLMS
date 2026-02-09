package org.yassineabou.llms.feature.chat.data.model

import androidx.compose.runtime.Immutable
import org.yassineabou.llms.feature.imagine.data.model.IdGenerator


@Immutable
data class TextModel(
    val id: Int = IdGenerator().generatedId(),
    val title: String,
    val modelName: String,
    val description: String? = null
){
    companion object {

        val DEFAULT = TextModel(
            title = "Amazon Nova Micro",
            modelName = "nova-fast",
            description = "Amazon Nova Micro - Ultra Fast & Ultra Cheap"
        )
    }
}

