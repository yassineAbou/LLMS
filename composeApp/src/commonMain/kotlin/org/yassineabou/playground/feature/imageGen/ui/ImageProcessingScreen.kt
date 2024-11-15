package org.yassineabou.playground.feature.imageGen.ui

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import org.koin.compose.viewmodel.koinViewModel
import org.yassineabou.playground.app.ui.navigation.Screen
import org.yassineabou.playground.feature.imageGen.model.UrlExample
import org.yassineabou.playground.feature.imageGen.view.BackgroundIndicator
import kotlin.time.Duration.Companion.seconds

@Composable
fun ImageProcessingScreen(
    navController: NavController,
    imageGenViewModel: ImageGenViewModel = koinViewModel(),
    ) {
    var remainingSeconds by remember { mutableStateOf(10) }
    var progress by remember { mutableStateOf(0f) }

    LaunchedEffect(key1 = Unit) {
       for (i in 10 downTo 0) {
           remainingSeconds = i
           progress = 1f * (i / 10f)
           delay(1.seconds)
       }
        imageGenViewModel.addImage(
           UrlExample(url = "https://i.imgur.com/ivnreND.png", description = "We're going to work on generating images next. this is just a prototype with fake data")
        )
        navController.navigate("${Screen.FullScreenImage.route}/${0}")
    }

    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        EstimatedTimer(
            timeText = "EST: ${remainingSeconds}s",
            progress = progress,
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
            onClick = { navController.popBackStack() }
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
