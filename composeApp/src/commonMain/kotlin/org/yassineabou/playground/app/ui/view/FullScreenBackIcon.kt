package org.yassineabou.playground.app.ui.view

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun FullScreenBackIcon(modifier: Modifier = Modifier, onBackPress: () -> Unit) {
    IconButton(
        onClick = onBackPress,
        modifier = modifier
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBackIos,
            contentDescription = "ArrowBackIos"
        )
    }
}