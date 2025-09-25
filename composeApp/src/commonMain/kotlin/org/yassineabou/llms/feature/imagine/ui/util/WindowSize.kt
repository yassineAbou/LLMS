package org.yassineabou.llms.feature.imagine.ui.util

import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
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