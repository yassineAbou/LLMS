package org.yassineabou.playground.feature.Imagine.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import org.yassineabou.playground.app.ui.theme.colorSchemeCustom
import org.yassineabou.playground.feature.Imagine.supportingPane.rememberIsLargeScreen

@Composable
fun NoContentMessage(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String,
    buttonText: String,
    onButtonClick: () -> Unit
) {
    val isLargeScreen = rememberIsLargeScreen()

    AnimatedGradientBackground(modifier = modifier) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Animated Icon with rotation
            RotatingIcon()

            Spacer(modifier = Modifier.height(24.dp))

            // Title with Fade-In Animation
            AnimatedVisibility(
                visible = true,
                enter = fadeIn() + slideInVertically { it / 2 },
                exit = fadeOut() + slideOutVertically { it / 2 }
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.displaySmall,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Subtitle with Typing Animation
            TypingText(text = subtitle)

            Spacer(modifier = Modifier.height(32.dp))

            // Generate Button with Bounce Animation
            if (!isLargeScreen) {
                BouncingButton(
                    onClick = onButtonClick,
                    text = buttonText
                )
            }
        }
    }
}

@Composable
fun AnimatedGradientBackground(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val infiniteTransition = rememberInfiniteTransition()
    val gradientOffset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        MaterialTheme.colorSchemeCustom.alwaysBlue,
                        MaterialTheme.colorSchemeCustom.alwaysBlue.copy(alpha = 0.8f),
                        MaterialTheme.colorSchemeCustom.alwaysBlue.copy(alpha = 0.6f)
                    ),
                    start = Offset(0f, gradientOffset),
                    end = Offset(1f, gradientOffset + 1f)
                )
            )
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}

@Composable
private fun RotatingIcon() {
    val infiniteTransition = rememberInfiniteTransition()
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing)
        )
    )

    Icon(
        imageVector = Icons.Default.AutoAwesome,
        contentDescription = "Generate Images",
        modifier = Modifier
            .size(120.dp)
            .graphicsLayer {
                rotationZ = rotation
            },
        tint = Color.White
    )
}

@Composable
private fun TypingText(text: String) {
    var animatedText by remember { mutableStateOf("") }
    LaunchedEffect(Unit) {
        text.forEachIndexed { index, _ ->
            animatedText = text.take(index + 1)
            delay(50) // Adjust typing speed
        }
    }

    Text(
        text = animatedText,
        style = MaterialTheme.typography.bodyLarge,
        color = Color.White.copy(alpha = 0.9f),
        textAlign = TextAlign.Center,
        modifier = Modifier.padding(horizontal = 24.dp)
    )
}

@Composable
private fun BouncingButton(
    onClick: () -> Unit,
    text: String
) {
    val infiniteTransition = rememberInfiniteTransition()
    val buttonScale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
            contentColor = MaterialTheme.colorSchemeCustom.alwaysBlue
        ),
        modifier = Modifier
            .graphicsLayer {
                scaleX = buttonScale
                scaleY = buttonScale
            }
            .height(48.dp)
            .width(200.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.Bold
        )
    }
}