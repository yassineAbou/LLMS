package org.yassineabou.playground.feature.chat.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.outlined.History
import androidx.compose.material.icons.outlined.MapsUgc
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.yassineabou.playground.app.ui.theme.colorSchemeCustom

@Composable
fun ChatAppBar(
    title: String,
    image: DrawableResource,
    modifier: Modifier = Modifier,
    onClickHistory: () -> Unit,
    onNewChatClick: () -> Unit,
    onSelect: () -> Unit
) {
    Box(
        modifier = modifier
    ) {
        HistoryIconButton(
            modifier = Modifier.align(Alignment.TopStart),
            onClick = onClickHistory
        )
        SelectedTextModel(
            title = title,
            image = image,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(8.dp)
                .clickable { onSelect() }
                .background(
                    color = MaterialTheme.colorSchemeCustom.alwaysBlue.copy(alpha = 0.5f),
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(6.dp)
        )
        NewChatIconButton(
            modifier = Modifier.align(Alignment.TopEnd),
            onClick = onNewChatClick
        )

    }
}

@Composable
fun HistoryIconButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    IconButton(
        modifier = modifier,
        onClick = onClick
    ) {
        Icon(
            imageVector = Icons.Outlined.History,
            modifier = Modifier.size(35.dp),
            contentDescription = "History"
        )
    }
}

@Composable
fun NewChatIconButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    IconButton(
        modifier = modifier,
        onClick = onClick
    ) {
        Icon(
            imageVector = Icons.Outlined.MapsUgc,
            modifier = Modifier.size(35.dp),
            contentDescription = "History"
        )
    }
}

@Composable
fun SelectedTextModel(
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
            color = MaterialTheme.colorSchemeCustom.alwaysWhite
        )
        Icon(
            imageVector = Icons.Filled.KeyboardArrowDown,
            contentDescription = "Select text model",
            tint = MaterialTheme.colorSchemeCustom.alwaysWhite
        )
    }
}
