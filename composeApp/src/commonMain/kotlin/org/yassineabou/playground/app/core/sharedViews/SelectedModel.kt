package org.yassineabou.playground.app.core.sharedViews

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun SelectedModel(
    title: String,
    image: DrawableResource,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(image),
            contentDescription = "Text Model",
            modifier = Modifier.size(25.dp)
        )
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = Color.White
        )
        Icon(
            imageVector = Icons.Filled.KeyboardArrowDown,
            contentDescription = "Select text model",
            tint = Color.White
        )
    }
}