package org.yassineabou.llms.feature.imagine.ui.supportingPane

sealed class SupportingPaneScreen {
    data object GeneratedImages : SupportingPaneScreen()
    data object ImageGenerationLoading : SupportingPaneScreen()
    data object FullScreenImage : SupportingPaneScreen()
}