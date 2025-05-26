package org.yassineabou.llms.app.core.sharedViews

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/*
The CustomIconButton is created to solve the issue where increasing the icon size
does not automatically increase the pressable (clickable) area of the button.
By wrapping the icon in a Box with a fixed size, we ensure that the clickable area
remains consistent and independent of the icon size.

 */

@Composable
fun CustomIconButton(
    icon: ImageVector,
    iconSize: Dp = 35.dp,
    buttonSize: Dp = 48.dp,
    contentDescription: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {

    Box(
        modifier = modifier
            .then(Modifier.size(buttonSize))
            .clip(CircleShape)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            modifier = Modifier.size(iconSize)
        )
    }
}