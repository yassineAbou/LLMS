package org.yassineabou.llms.app.core.util

import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically

object Animations {

    fun slideFadeIn() = fadeIn() + slideInVertically { it }
    fun slideFadeOut() = fadeOut() + slideOutVertically { it }
    fun fadeInExpand() = fadeIn(animationSpec = tween(300)) + expandVertically(animationSpec = tween(300))
    fun fadeOutShrink() = fadeOut(animationSpec = tween(300)) + shrinkVertically(animationSpec = tween(300))

}