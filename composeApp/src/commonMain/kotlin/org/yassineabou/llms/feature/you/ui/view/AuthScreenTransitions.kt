package org.yassineabou.llms.feature.you.ui.view

// TransitionSpecs.kt
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.ui.Alignment

object AuthScreenTransition {

    // For main screen login state transitions
    val heroContentEnter: EnterTransition
        get() = fadeIn(animationSpec = tween(300)) + expandVertically(
            expandFrom = Alignment.Top,
            animationSpec = tween(500)
        )

    val heroContentExit: ExitTransition
        get() = fadeOut(animationSpec = tween(300)) + shrinkVertically(
            shrinkTowards = Alignment.Top,
            animationSpec = tween(500)
        )


}
