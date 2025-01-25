package org.yassineabou.playground.feature.Imagine.supportingPane

sealed class SupportingPaneScreen {
    data object GeneratedImages : SupportingPaneScreen()
    data object ImageProcessing : SupportingPaneScreen()
    data object FullScreenImage : SupportingPaneScreen()
}