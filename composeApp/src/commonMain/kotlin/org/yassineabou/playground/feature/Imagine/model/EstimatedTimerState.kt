package org.yassineabou.playground.feature.Imagine.model

data class EstimatedTimerState(
    val remainingSeconds: Int,
    val progress: Float,
    val isTimerCompleted: Boolean
)