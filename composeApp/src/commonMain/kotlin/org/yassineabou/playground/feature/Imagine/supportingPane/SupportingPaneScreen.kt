package org.yassineabou.playground.feature.Imagine.supportingPane

sealed class SupportingPaneScreen {
    object GeneratedImages : SupportingPaneScreen()
    object ImageProcessing : SupportingPaneScreen()
    data class FullScreenImage(val index: Int) : SupportingPaneScreen()
}