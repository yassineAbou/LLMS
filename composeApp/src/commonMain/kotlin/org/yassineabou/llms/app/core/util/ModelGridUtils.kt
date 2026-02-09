package org.yassineabou.llms.app.core.util

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.window.core.layout.WindowSizeClass
import kotlin.math.ceil

/**
 * Utility functions for model selection grid layout calculations
 */
object ModelGridUtils {

    fun getColumnCount(windowSizeClass: WindowSizeClass): Int {
        return when {
            windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_MEDIUM_LOWER_BOUND) -> 3
            else -> 2 // Compact width (phones)
        }
    }
}