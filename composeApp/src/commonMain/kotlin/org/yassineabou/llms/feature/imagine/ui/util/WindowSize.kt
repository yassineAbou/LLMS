package org.yassineabou.llms.feature.imagine.ui.util

import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun rememberWindowSizeClass(): WindowSizeClass {
    return calculateWindowSizeClass()
}

@Composable
fun rememberIsLargeScreen(): Boolean {
    val windowSizeClass = rememberWindowSizeClass()
    return windowSizeClass.widthSizeClass > WindowWidthSizeClass.Medium
}