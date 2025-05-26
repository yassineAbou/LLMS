package org.yassineabou.llms.app.core.sharedViews

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun ModelHeader(
    backgroundColor: Color,
    image: DrawableResource,
    onInfoClick: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            painter = painterResource(image),
            contentDescription = "Text Model",
            modifier = Modifier.size(40.dp)
        )
        InfoIconButton(
            onInfoClick = { onInfoClick(true) }
        )
    }
}


@Composable
fun InfoIconButton(
    modifier: Modifier = Modifier,
    onInfoClick: () -> Unit
) {
    IconButton(
        modifier = modifier,
        onClick = onInfoClick
    ) {
        Icon(
            imageVector = Icons.TwoTone.Info,
            contentDescription = "info",
        )
    }
}