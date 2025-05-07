package org.yassineabou.playground.app.core.theme

import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class CustomColorScheme(
    val alwaysPink: Color,
    val alwaysBlue: Color,
    val alwaysOrange: Color,
    val askAnythingBgColor: Color
)

val LocalCustomColorScheme: ProvidableCompositionLocal<CustomColorScheme> =
    staticCompositionLocalOf {
        throw IllegalStateException("CustomColorScheme not provided")
    }