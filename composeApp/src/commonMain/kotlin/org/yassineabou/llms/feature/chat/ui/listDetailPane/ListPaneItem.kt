package org.yassineabou.llms.feature.chat.ui.listDetailPane

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.yassineabou.llms.Chats
import org.yassineabou.llms.app.core.theme.colorSchemeCustom

@Composable
fun ListPaneItem(
    chats: Chats,
    selected: Boolean,
    onClick: () -> Unit,
    onPinClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                if (selected) MaterialTheme.colorSchemeCustom.alwaysBlue.copy(alpha = 0.25F) else Color.Transparent,
                shape = RoundedCornerShape(24.dp)
            )
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .clickable {
                onClick()
            }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            ChatDescription(
                chats = chats,
                modifier = Modifier
                    .padding(start = 8.dp)
                    .weight(1f)
            )
            MoreOptionsMenu(
                expanded = expanded,
                isBookmarked = chats.is_bookmarked == 1L,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .width(56.dp),
                onToggleExpanded = { expanded = !expanded },
                onPinClick = onPinClick,
                onDeleteClick = onDeleteClick
            )
        }
    }
}

@Composable
private fun ChatDescription(
    chats: Chats,
    modifier: Modifier
) {
    Text(
        text = chats.description ?: "",
        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
        maxLines = 1,
        modifier = modifier
    )
}

@Composable
private fun MoreOptionsMenu(
    expanded: Boolean,
    isBookmarked: Boolean,
    modifier: Modifier,
    onToggleExpanded: () -> Unit,
    onPinClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Box(
        modifier = modifier
    ) {
        IconButton(
            onClick = onToggleExpanded,
            modifier = Modifier.align(Alignment.Center)
        ) {
            Icon(
                imageVector = Icons.Default.MoreHoriz,
                contentDescription = "More options",
                modifier = Modifier.size(18.dp)
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = onToggleExpanded
        ) {
            DropdownMenuItem(
                text = { Text(if (isBookmarked) "Unpin" else "Pin") },
                onClick = {
                    onPinClick()
                    onToggleExpanded()
                }
            )
            DropdownMenuItem(
                text = { Text("Delete") },
                onClick = {
                    onDeleteClick()
                    onToggleExpanded()
                }
            )
        }
    }
}