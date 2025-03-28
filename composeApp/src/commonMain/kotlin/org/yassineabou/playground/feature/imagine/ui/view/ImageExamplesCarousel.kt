package org.yassineabou.playground.feature.imagine.ui.view

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.github.panpf.sketch.AsyncImage
import kotlinx.coroutines.delay
import org.yassineabou.playground.feature.imagine.model.UrlExample
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

@Composable
fun ImageExamplesCarousel(
    imageUrlExamples: List<UrlExample>,
    delayTime: Duration = 5.seconds
) {
    var currentExampleIndex by remember { mutableStateOf(0) }
    // Automatically cycle through images every 3 seconds
    LaunchedEffect(key1 = true) {
        while (true) {
            delay(delayTime)
            currentExampleIndex = (currentExampleIndex + 1) % imageUrlExamples.size
        }
    }

    // Use AnimatedContent for smooth transitions
    AnimatedContent(
        targetState = currentExampleIndex,
        transitionSpec = {
            // Slide in from the right and slide out to the left
            slideInHorizontally { width -> width } togetherWith slideOutHorizontally { width -> -width }
        },
        label = "Image Carousel"
    ) { index ->
        val currentExample = imageUrlExamples[index]
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
        ) {
            AsyncImage(
                uri = currentExample.url,
                contentDescription = "Image URL Example",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds,
            )
        }
    }
}
