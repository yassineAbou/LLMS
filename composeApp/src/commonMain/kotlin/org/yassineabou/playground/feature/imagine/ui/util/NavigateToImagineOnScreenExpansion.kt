package org.yassineabou.playground.feature.imagine.ui.util

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun NavigateToImagineOnScreenExpansion(
    navController: NavController,
    targetRoute: String,
    threshold: Dp = 840.dp,
    onNavigate: () -> Unit
) {
    val currentDestination by navController.currentBackStackEntryAsState()
    val isCurrentDestination = remember(currentDestination) {
        currentDestination?.destination?.route == targetRoute
    }

    BoxWithConstraints {
        val currentWidth = maxWidth
        LaunchedEffect(currentWidth) {
            if (currentWidth > threshold && isCurrentDestination) {
                onNavigate()
            }
        }
    }
}