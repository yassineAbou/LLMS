package org.yassineabou.llms.feature.you.ui.view

import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin

@Composable
fun VerifiedUserAnimation(modifier: Modifier = Modifier) {
    // Get colors in composable context
    val primaryColor = MaterialTheme.colorScheme.primary
    val secondaryColor = MaterialTheme.colorScheme.secondary
    val tertiaryColor = MaterialTheme.colorScheme.tertiary
    val onSecondary = MaterialTheme.colorScheme.onSecondary  // Fixed: Moved out of Canvas

    val infiniteTransition = rememberInfiniteTransition()

    val circlePulse by infiniteTransition.animateFloat(
        initialValue = 0.95f,
        targetValue = 1.05f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    val checkProgress by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = CubicBezierEasing(0.4f, 0.0f, 0.2f, 1.0f))
        )
    )

    val starCount = 5
    val starAlphas = List(starCount) { i ->
        infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = 1200,
                    delayMillis = i * 200,
                    easing = LinearEasing
                ),
                repeatMode = RepeatMode.Restart
            )
        )
    }

    Canvas(modifier = modifier) {
        val width = size.width
        val height = size.height
        val center = Offset(width / 2, height / 2)
        val circleRadius = min(width, height) * 0.3f * circlePulse

        // Gradient background circle - FIXED PARENTHESES
        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(primaryColor.copy(alpha = 0.3f), primaryColor.copy(alpha = 0.1f)),
                center = center,
                radius = circleRadius * 1.2f
            ),
            center = center,
            radius = circleRadius * 1.2f
        )

        // Main circle
        drawCircle(
            color = primaryColor,
            radius = circleRadius,
            center = center,
            style = Stroke(width = width * 0.02f)
        )

        // User icon
        drawCircle(
            color = secondaryColor,
            radius = circleRadius * 0.7f,
            center = center
        )

        // Head
        drawCircle(
            color = onSecondary,
            radius = circleRadius * 0.25f,
            center = Offset(center.x, center.y - circleRadius * 0.1f)
        )

        // Body
        drawArc(
            color = onSecondary,
            startAngle = 0f,
            sweepAngle = 180f,
            useCenter = true,
            topLeft = Offset(center.x - circleRadius * 0.3f, center.y - circleRadius * 0.1f),
            size = Size(circleRadius * 0.6f, circleRadius * 0.7f)
        )

        // Checkmark path
        val checkPath = Path().apply {
            moveTo(center.x - circleRadius * 0.15f, center.y + circleRadius * 0.05f)
            lineTo(center.x - circleRadius * 0.05f, center.y + circleRadius * 0.2f)
            lineTo(center.x + circleRadius * 0.25f, center.y - circleRadius * 0.1f)
        }

        // Animated checkmark
        if (checkProgress > 0) {
            drawPath(
                path = checkPath,
                color = primaryColor,
                style = Stroke(
                    width = width * 0.03f,
                    cap = StrokeCap.Round,
                    join = StrokeJoin.Round
                ),
                alpha = checkProgress
            )
        }

        // Star particles
        for (i in 0 until starCount) {
            val angle = (360f / starCount) * i
            val radians = angle * PI / 180.0
            val distance = circleRadius * 1.5f
            val x = center.x + (cos(radians) * distance).toFloat()
            val y = center.y + (sin(radians) * distance).toFloat()
            val alpha = starAlphas[i].value

            drawCircle(
                color = tertiaryColor,
                radius = width * 0.02f,
                center = Offset(x, y),
                alpha = alpha
            )

            // Star points
            for (j in 0 until 4) {
                val pointAngle = angle + 90f * j
                val pointRadians = pointAngle * PI / 180.0
                val pointX = x + (cos(pointRadians) * width * 0.03f).toFloat()
                val pointY = y + (sin(pointRadians) * width * 0.03f).toFloat()

                drawLine(
                    color = tertiaryColor,
                    start = Offset(x, y),
                    end = Offset(pointX, pointY),
                    strokeWidth = width * 0.005f,
                    alpha = alpha,
                    cap = StrokeCap.Round
                )
            }
        }
    }
}