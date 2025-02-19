package org.yassineabou.playground.feature.imagine.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import org.yassineabou.playground.app.core.navigation.Screen
import org.yassineabou.playground.app.core.util.PaneOrScreenNavigator
import org.yassineabou.playground.feature.imagine.ui.supportingPane.SupportingPaneNavigator
import org.yassineabou.playground.feature.imagine.ui.supportingPane.SupportingPaneScreen
import org.yassineabou.playground.feature.imagine.ui.supportingPane.rememberIsLargeScreen
import org.yassineabou.playground.feature.imagine.ui.view.BackgroundIndicator

@Composable
fun ImageCreationTimerScreen(
    navController: NavController,
    imageGenViewModel: ImageGenViewModel,
    supportingPaneNavigator: SupportingPaneNavigator? = null,
    modifier: Modifier = Modifier
) {
    // Observe timer state from the ViewModel
    val timerState by imageGenViewModel.estimatedTimerState.collectAsStateWithLifecycle()
    val listGeneratedPhotos by imageGenViewModel.listGeneratedPhotos.collectAsStateWithLifecycle()
    val isLargeScreen = rememberIsLargeScreen()

    // Trigger navigation when the timer completes
    LaunchedEffect(key1 = timerState.isTimerCompleted) {
        if (timerState.isTimerCompleted and listGeneratedPhotos.isNotEmpty()) {
            if (isLargeScreen) {
                supportingPaneNavigator?.navigate(SupportingPaneScreen.FullScreenImage)
            } else {
                navController.navigate(Screen.FullScreenImage.route)
            }
            // Reset the timer state
            imageGenViewModel.resetEstimatedTimer()
        }
    }

    Box(
        modifier = modifier.fillMaxSize(),
    ) {
        EstimatedTimer(
            timeText = "EST: ${timerState.remainingSeconds}s",
            progress = timerState.progress,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(bottom = 200.dp)
                .size(325.dp)
        )

        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.error
            ),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 113.dp),
            onClick = {
                // Stop the timer and reset the state
                imageGenViewModel.stopEstimatedTimer()

                PaneOrScreenNavigator.navigateBack(
                    supportingPaneNavigator = supportingPaneNavigator,
                    navController = navController,
                    isLargeScreen = isLargeScreen
                )
            }
        ) {
            Text(
                text = "Cancel",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
            )
        }
    }
}

@Composable
private fun EstimatedTimer(
    modifier: Modifier = Modifier,
    timeText: String,
    progress: Float,
) {
    Box(modifier = modifier) {
        BackgroundIndicator(
            progress = progress,
            modifier = modifier
                .fillMaxSize()
                .scale(scaleX = 1f, scaleY = 1f),
            strokeWidth = 12.dp,
        )
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = timeText,
            style = MaterialTheme.typography.displayLarge,
            fontWeight = FontWeight.Light,
        )
    }
}
