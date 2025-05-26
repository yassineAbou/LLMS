package org.yassineabou.llms.feature.chat.ui.listDetailPane

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.yassineabou.llms.app.core.theme.colorSchemeCustom
import org.yassineabou.llms.feature.chat.data.model.ChatHistory

@Composable
fun ListPaneItem(
    chat: ChatHistory,
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
                chat = chat,
                modifier = Modifier
                    .padding(start = 8.dp)
                    .weight(1f)
            )
            MoreOptionsMenu(
                expanded = expanded,
                isBookmarked = chat.isBookmarked,
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
    chat: ChatHistory,
    modifier: Modifier
) {
    Text(
        text = chat.description,
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