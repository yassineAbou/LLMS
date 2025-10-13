package org.yassineabou.llms.feature.imagine.data.model

data class EstimatedTimerState(
    val remainingSeconds: Int,
    val progress: Float,
    val isTimerCompleted: Boolean
)