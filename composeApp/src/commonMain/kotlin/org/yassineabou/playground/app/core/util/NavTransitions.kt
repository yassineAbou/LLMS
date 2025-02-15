package org.yassineabou.playground.app.core.util

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically


object NavTransitions {

    fun slideUpIn(): AnimatedContentTransitionScope<*>.() -> EnterTransition {
        return {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Up,
                animationSpec = tween(durationMillis = 300)
            )
        }
    }

    fun slideDownOut(): AnimatedContentTransitionScope<*>.() -> ExitTransition {
        return {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Down,
                animationSpec = tween(durationMillis = 300)
            )
        }
    }

    fun slideDownIn(): AnimatedContentTransitionScope<*>.() -> EnterTransition {
        return {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Up,
                animationSpec = tween(durationMillis = 300)
            )
        }
    }

    fun slideUpOut(): AnimatedContentTransitionScope<*>.() -> ExitTransition {
        return {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Down,
                animationSpec = tween(durationMillis = 300)
            )
        }
    }


    fun slideLeftIn(): AnimatedContentTransitionScope<*>.() -> EnterTransition {
        return {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Start,
                animationSpec = tween(durationMillis = 300)
            )
        }
    }

    fun slideRightOut(): AnimatedContentTransitionScope<*>.() -> ExitTransition {
        return {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.End,
                animationSpec = tween(durationMillis = 300)
            )
        }
    }

    fun slideRightIn(): AnimatedContentTransitionScope<*>.() -> EnterTransition {
        return {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.End,
                animationSpec = tween(durationMillis = 300)
            )
        }
    }

    fun slideLeftOut(): AnimatedContentTransitionScope<*>.() -> ExitTransition {
        return {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Start,
                animationSpec = tween(durationMillis = 300)
            )
        }
    }

}