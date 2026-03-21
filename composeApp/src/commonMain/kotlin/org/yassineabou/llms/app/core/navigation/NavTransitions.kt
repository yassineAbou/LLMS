package org.yassineabou.llms.app.core.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.navigation3.ui.NavDisplay

object NavTransitions {

    fun slideVertical() =
        NavDisplay.transitionSpec {
            slideInVertically(
                initialOffsetY = { it },
                animationSpec = tween(300)
            ) togetherWith slideOutVertically(
                targetOffsetY = { -it },
                animationSpec = tween(300)
            )
        } + NavDisplay.popTransitionSpec {
            slideInVertically(
                initialOffsetY = { -it },
                animationSpec = tween(300)
            ) togetherWith slideOutVertically(
                targetOffsetY = { it },
                animationSpec = tween(300)
            )
        } + NavDisplay.predictivePopTransitionSpec {
            slideInVertically(
                initialOffsetY = { -it },
                animationSpec = tween(300)
            ) togetherWith slideOutVertically(
                targetOffsetY = { it },
                animationSpec = tween(300)
            )
        }

    fun slideHorizontal() =
        NavDisplay.transitionSpec {
            slideInHorizontally(
                initialOffsetX = { it },
                animationSpec = tween(300)
            ) togetherWith slideOutHorizontally(
                targetOffsetX = { -it },
                animationSpec = tween(300)
            )
        } + NavDisplay.popTransitionSpec {
            slideInHorizontally(
                initialOffsetX = { -it },
                animationSpec = tween(300)
            ) togetherWith slideOutHorizontally(
                targetOffsetX = { it },
                animationSpec = tween(300)
            )
        } + NavDisplay.predictivePopTransitionSpec {
            slideInHorizontally(
                initialOffsetX = { -it },
                animationSpec = tween(300)
            ) togetherWith slideOutHorizontally(
                targetOffsetX = { it },
                animationSpec = tween(300)
            )
        }
}