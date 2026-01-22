package org.yassineabou.llms.app.core.util

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.window.core.layout.WindowSizeClass
import kotlin.math.ceil

/**
 * Utility functions for model selection grid layout calculations
 */
object ModelGridUtils {

    private const val SPACING_BETWEEN_ROWS = 12

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

    /**
     * Calculate the estimated grid height based on item count and window size
     */
    fun calculateGridHeight(itemCount: Int, windowSizeClass: WindowSizeClass): Dp {
        val columnCount = getColumnCount(windowSizeClass)
        val itemHeight = getItemHeight(windowSizeClass)
        val rowCount = ceil(itemCount.toFloat() / columnCount).toInt()
        return (rowCount * itemHeight + (rowCount - 1) * SPACING_BETWEEN_ROWS).dp
    }
}