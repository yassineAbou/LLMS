package org.yassineabou.llms.feature.you.ui.view

import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.FastOutSlowInEasing
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
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.translate

@Composable
fun CloudSyncAnimation(modifier: Modifier = Modifier) {
    // Get colors in composable context
    val primaryColor = MaterialTheme.colorScheme.primary
    val secondaryColor = MaterialTheme.colorScheme.secondary
    val backgroundColor = MaterialTheme.colorScheme.background
    val screenColor = MaterialTheme.colorScheme.surface  // Fixed: Moved out of Canvas

    val infiniteTransition = rememberInfiniteTransition()
    val cloudFloat by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 20f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    val arrowProgress by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1800, easing = CubicBezierEasing(0.4f, 0.0f, 0.2f, 1.0f))
        )
    )

    Canvas(modifier = modifier) {
        val width = size.width
        val height = size.height
        val centerX = width / 2
        val centerY = height / 2

        // Cloud drawing
        val cloudPath = Path().apply {
            moveTo(centerX - width * 0.25f, centerY)
            cubicTo(
                centerX - width * 0.3f, centerY - height * 0.15f,
                centerX - width * 0.15f, centerY - height * 0.2f,
                centerX, centerY - height * 0.15f
            )
            cubicTo(
                centerX + width * 0.1f, centerY - height * 0.25f,
                centerX + width * 0.25f, centerY - height * 0.15f,
                centerX + width * 0.25f, centerY
            )
            cubicTo(
                centerX + width * 0.3f, centerY + height * 0.1f,
                centerX + width * 0.15f, centerY + height * 0.2f,
                centerX, centerY + height * 0.15f
            )
            cubicTo(
                centerX - width * 0.15f, centerY + height * 0.2f,
                centerX - width * 0.3f, centerY + height * 0.1f,
                centerX - width * 0.25f, centerY
            )
            close()
        }

        // Draw cloud with gradient
        drawPath(
            path = cloudPath,
            brush = Brush.linearGradient(
                colors = listOf(
                    primaryColor.copy(alpha = 0.8f),
                    primaryColor.copy(alpha = 0.4f)
                ),
                start = Offset(centerX - width * 0.3f, centerY - height * 0.2f),
                end = Offset(centerX + width * 0.3f, centerY + height * 0.2f)
            ),
            alpha = 0.9f
        )

        // Devices
        val phoneWidth = width * 0.15f
        val leftPhoneX = width * 0.15f
        val rightPhoneX = width * 0.85f

        // Left phone
        drawRoundRect(
            color = secondaryColor,
            topLeft = Offset(leftPhoneX - phoneWidth/2, centerY - height*0.2f),
            size = Size(phoneWidth, height*0.4f),
            cornerRadius = CornerRadius(phoneWidth*0.2f, phoneWidth*0.2f)
        )

        // Right phone
        drawRoundRect(
            color = secondaryColor,
            topLeft = Offset(rightPhoneX - phoneWidth/2, centerY - height*0.2f),
            size = Size(phoneWidth, height*0.4f),
            cornerRadius = CornerRadius(phoneWidth*0.2f, phoneWidth*0.2f)
        )

        // Screen content
        val screenPadding = phoneWidth * 0.1f

        // Left screen
        drawRoundRect(
            color = screenColor,
            topLeft = Offset(leftPhoneX - phoneWidth/2 + screenPadding, centerY - height*0.2f + screenPadding),
            size = Size(phoneWidth - screenPadding*2, height*0.4f - screenPadding*2),
            cornerRadius = CornerRadius(phoneWidth*0.15f, phoneWidth*0.15f)
        )

        // Right screen
        drawRoundRect(
            color = screenColor,
            topLeft = Offset(rightPhoneX - phoneWidth/2 + screenPadding, centerY - height*0.2f + screenPadding),
            size = Size(phoneWidth - screenPadding*2, height*0.4f - screenPadding*2),
            cornerRadius = CornerRadius(phoneWidth*0.15f, phoneWidth*0.15f)
        )

        // Pet icon on screens
        val petSize = phoneWidth * 0.3f
        drawCircle(
            color = primaryColor,
            center = Offset(leftPhoneX, centerY),
            radius = petSize
        )
        drawCircle(
            color = primaryColor,
            center = Offset(rightPhoneX, centerY),
            radius = petSize
        )

        // Animated arrows
        val arrowSpacing = width * 0.15f
        val arrowCount = 3
        val arrowSize = width * 0.08f

        for (i in 0 until arrowCount) {
            val startX = leftPhoneX + arrowSpacing * i
            val endX = rightPhoneX - arrowSpacing * (arrowCount - 1 - i)
            val animatedX = lerp(startX, endX, arrowProgress)

            val yOffset = centerY + cloudFloat - height * 0.05f * i

            // Arrow body
            drawLine(
                color = primaryColor,
                start = Offset(startX, yOffset),
                end = Offset(animatedX, yOffset),
                strokeWidth = width * 0.01f,
                cap = StrokeCap.Round
            )

            // Arrow head
            if (arrowProgress > 0.2f) {
                drawPath(
                    path = Path().apply {
                        moveTo(animatedX, yOffset)
                        lineTo(animatedX - arrowSize * 0.7f, yOffset - arrowSize * 0.3f)
                        lineTo(animatedX - arrowSize * 0.7f, yOffset + arrowSize * 0.3f)
                        close()
                    },
                    color = primaryColor
                )
            }
        }

        // Floating effect
        translate(top = cloudFloat) {
            drawPath(
                path = cloudPath,
                color = backgroundColor.copy(alpha = 0.3f),
                alpha = 0.2f
            )
        }
    }
}

// Helper function
fun lerp(start: Float, stop: Float, fraction: Float): Float {
    return start + fraction * (stop - start)
}