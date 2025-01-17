package org.yassineabou.playground.app.ui.util

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally

fun slideInFromRight() = slideInHorizontally(initialOffsetX = { it }, animationSpec = tween(300))
fun slideOutToLeft() = slideOutHorizontally(targetOffsetX = { -it }, animationSpec = tween(300))
fun slideInFromLeft() = slideInHorizontally(initialOffsetX = { -it }, animationSpec = tween(300))
fun slideOutToRight() = slideOutHorizontally(targetOffsetX = { it }, animationSpec = tween(300))