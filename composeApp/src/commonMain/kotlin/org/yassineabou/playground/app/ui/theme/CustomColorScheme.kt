package org.yassineabou.playground.app.ui.theme

import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class CustomColorScheme(
    val alwaysPink: Color
)

val LocalCustomColorScheme: ProvidableCompositionLocal<CustomColorScheme> = staticCompositionLocalOf {
    throw IllegalStateException("CustomColorScheme not provided")
}