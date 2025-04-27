package org.yassineabou.playground.feature.chat.data.model

sealed class TextGenerationState {
    data class Loading(val id:  Int) : TextGenerationState()
    data object Success : TextGenerationState()
    data class Failure(val message: String = "The current content is empty, please regenerate") : TextGenerationState()
}