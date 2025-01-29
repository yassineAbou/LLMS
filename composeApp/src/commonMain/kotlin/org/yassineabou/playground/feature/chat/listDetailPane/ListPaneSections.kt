package org.yassineabou.playground.feature.chat.listDetailPane

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
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
import org.yassineabou.playground.app.ui.theme.colorSchemeCustom
import org.yassineabou.playground.feature.chat.model.ChatHistory
import org.yassineabou.playground.feature.chat.ui.ChatViewModel

@Composable
fun ListPaneSections(
    chatViewModel: ChatViewModel,
    windowSizeClass: WindowSizeClass,
    navigateToDetailPane: () -> Unit,
) {
    val savedChatHistoryList = chatViewModel.savedChatHistoryList
    val chatHistoryList = chatViewModel.chatHistoryList
    val selectedItem = remember { mutableStateOf<ChatHistory?>(null) } // Track selected item

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        item {
            SectionHeader(title = "Saved")
        }
        items(savedChatHistoryList) { chat ->
            ListPaneItem(
                chat = chat,
                onClick = { selectedItem.value = chat },
                selected = selectedItem.value == chat,
                navigateToDetailPane = navigateToDetailPane,
                windowSizeClass = windowSizeClass,
                chatViewModel = chatViewModel
            )
        }
        item {
            SectionHeader(title = "Recent")
        }
        items(chatHistoryList) { chat ->
            ListPaneItem(
                chat = chat,
                onClick = { selectedItem.value = chat },
                selected = selectedItem.value == chat,
                navigateToDetailPane = navigateToDetailPane,
                windowSizeClass = windowSizeClass,
                chatViewModel = chatViewModel
            )
        }
    }
}

@Composable
fun SectionHeader(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium.copy(
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.primary
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 12.dp)
    )
}

@Composable
fun ListPaneItem(
    chat: ChatHistory,
    onClick: () -> Unit,
    selected: Boolean,
    navigateToDetailPane: () -> Unit,
    windowSizeClass: WindowSizeClass,
    chatViewModel: ChatViewModel,
    onPinClick: () -> Unit = {},
    onDeleteClick: () -> Unit = {}
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
            .clickable { onClick() }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = chat.description,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Medium
                ),
                maxLines = 1,
                modifier = Modifier
                    .padding(start = 8.dp)
                    .weight(1f)
            )

            Box(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .width(56.dp)
            ) {
                IconButton(
                    onClick = { expanded = !expanded },
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
                    onDismissRequest = { expanded = false }
                ) {
                    DropdownMenuItem(
                        text = { Text("Pin") },
                        onClick = {
                            onPinClick()
                            expanded = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Delete") },
                        onClick = {
                            onDeleteClick()
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}