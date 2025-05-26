package org.yassineabou.llms.feature.imagine.model

data class EstimatedTimerState(
    val remainingSeconds: Int,
    val progress: Float,
    val isTimerCompleted: Boolean
)