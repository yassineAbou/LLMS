package org.yassineabou.playground.app.core.data

sealed class GenerationState {
    data class Loading(val id:  Int) : GenerationState()
    data object Success : GenerationState()
    data object Cancelled : GenerationState()
    data class Failure(val message: String = "The current content is empty, please regenerate") : GenerationState()
}