package org.yassineabou.llms.app.core.navigation

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavKey

@Composable
fun NavigateToImagineOnScreenExpansion(
    navigator: Navigator,
    currentRoute: NavKey,
    threshold: Dp = 840.dp,
    onNavigate: () -> Unit
) {
    val isCurrentDestination = navigator.state.currentRoute == currentRoute

    BoxWithConstraints {
        val currentWidth = maxWidth
        LaunchedEffect(currentWidth) {
            if (currentWidth > threshold && isCurrentDestination) {
                onNavigate()
            }
        }
    }
}