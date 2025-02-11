package org.yassineabou.playground.app.core.util

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch

/**
 * Returns a Modifier that enables horizontal drag scrolling for desktop and WASM platforms.
 *
 * @param scrollState The LazyListState of the scrollable component.
 * @param isEnabled Whether drag scrolling is enabled (default is true).
 */
@Composable
fun Modifier.draggableScrollModifier(
    scrollState: LazyListState,
    isEnabled: Boolean = true
): Modifier {
    val coroutineScope = rememberCoroutineScope()
    val isDesktopOrWasm = PlatformConfig.isDesktop() or PlatformConfig.isWasm()

    return if (isEnabled && isDesktopOrWasm) {
        this.draggable(
            orientation = Orientation.Horizontal,
            state = rememberDraggableState { delta ->
                coroutineScope.launch {
                    scrollState.scrollBy(-delta) // Scroll horizontally based on drag delta
                }
            }
        )
    } else {
        this // Return the original Modifier if not enabled or not on desktop/WASM
    }
}