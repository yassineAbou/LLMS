package org.yassineabou.playground.app.ui.util

import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally

fun slideInFromRight() = slideInHorizontally(initialOffsetX = { it }, animationSpec = tween(300))
fun slideOutToLeft() = slideOutHorizontally(targetOffsetX = { -it }, animationSpec = tween(300))
fun slideInFromLeft() = slideInHorizontally(initialOffsetX = { -it }, animationSpec = tween(300))
fun slideOutToRight() = slideOutHorizontally(targetOffsetX = { it }, animationSpec = tween(300))
fun fadeInAndExpand() = fadeIn(animationSpec = tween(300)) + expandVertically(animationSpec = tween(300))
fun fadeOutAndShrink() = fadeOut(animationSpec = tween(300)) + shrinkVertically(animationSpec = tween(300))