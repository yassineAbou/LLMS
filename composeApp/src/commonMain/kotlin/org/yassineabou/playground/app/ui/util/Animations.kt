package org.yassineabou.playground.app.ui.util

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically

// Extracted animation spec
fun slideFadeIn() = fadeIn() + slideInVertically { it }
fun slideFadeOut() = fadeOut() + slideOutVertically { it }