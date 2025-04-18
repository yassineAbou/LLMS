package org.yassineabou.playground.feature.chat.data.network

sealed class TextGenerationState {
    data class Loading(val id:  Int) : TextGenerationState()
    data class Success(val text: String) : TextGenerationState()
    data class Failure(val message: String = "The current content is empty, please regenerate") : TextGenerationState()
}