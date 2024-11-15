package org.yassineabou.playground.app.ui.view

import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun GoToFirst(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    onClick: () -> Unit,
) {
    FloatingActionButton(
        modifier = modifier,
        elevation = FloatingActionButtonDefaults.elevation(8.dp),
        onClick = onClick
    ) {
        Icon(
            imageVector = icon,
            contentDescription = "Scroll to start"
        )
    }
}