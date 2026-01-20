package org.yassineabou.llms.feature.imagine.ui.util

import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.window.core.layout.WindowSizeClass


@Composable
fun rememberWindowSizeClass(): WindowSizeClass {
    return currentWindowAdaptiveInfo().windowSizeClass
}

@Composable
fun rememberIsLargeScreen(): Boolean {
    val windowSizeClass = rememberWindowSizeClass()
    return windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_EXPANDED_LOWER_BOUND)
}

/**
 * Calculates the number of columns based on window size class
 */
fun getColumnCount(windowSizeClass: WindowSizeClass): Int {
    return when {
        windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_EXTRA_LARGE_LOWER_BOUND) -> 6
        windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_LARGE_LOWER_BOUND) -> 5
        windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_EXPANDED_LOWER_BOUND) -> 4
        windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_MEDIUM_LOWER_BOUND) -> 3
        else -> 2 // Compact width (phones)
    }
}

/**
 * Calculates the minimum item width based on window size class
 */
fun getMinItemWidth(windowSizeClass: WindowSizeClass): Dp {
    return when {
        windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_EXPANDED_LOWER_BOUND) -> 180.dp
        windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_MEDIUM_LOWER_BOUND) -> 160.dp
        else -> 150.dp
    }
}

/**
 * Calculates the item height based on window size class
 */
fun getItemHeight(windowSizeClass: WindowSizeClass): Int {
    return when {
        windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_EXPANDED_LOWER_BOUND) -> 130
        windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_MEDIUM_LOWER_BOUND) -> 135
        else -> 140
    }
}
