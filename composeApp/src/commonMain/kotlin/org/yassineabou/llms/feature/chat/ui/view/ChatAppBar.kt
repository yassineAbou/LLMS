package org.yassineabou.llms.feature.chat.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.History
import androidx.compose.material.icons.outlined.MapsUgc
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.DrawableResource
import org.yassineabou.llms.app.core.sharedViews.CustomIconButton
import org.yassineabou.llms.app.core.sharedViews.SelectedModel
import org.yassineabou.llms.app.core.theme.colorSchemeCustom

@Composable
fun ChatAppBar(
    title: String,
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
        SelectedModel(
            title = title,
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
    CustomIconButton(
        icon = Icons.Outlined.History,
        contentDescription = "History",
        modifier = modifier,
        onClick = onClick,
    )
}

@Composable
fun NewChatIconButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    CustomIconButton(
        icon = Icons.Outlined.MapsUgc,
        contentDescription = "New Chat",
        modifier = modifier,
        onClick = onClick,
    )
}
