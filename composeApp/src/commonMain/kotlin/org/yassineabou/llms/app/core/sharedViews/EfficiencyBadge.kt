package org.yassineabou.llms.app.core.sharedViews

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

/**
 * Displays efficiency badge for any model type
 */
@Composable
fun EfficiencyBadge(
    efficiency: String?,
    isSelected: Boolean
) {
    efficiency?.let { text ->
        val badgeColor = if (isSelected) {
            Color.White.copy(alpha = 0.8f)
        } else {
            MaterialTheme.colorScheme.onSurfaceVariant
        }

        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall,
            color = badgeColor,
            maxLines = 1
        )
    }
}

