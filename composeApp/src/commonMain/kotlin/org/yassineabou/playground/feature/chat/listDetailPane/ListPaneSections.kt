package org.yassineabou.playground.feature.chat.listDetailPane

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.yassineabou.playground.feature.chat.ui.ChatViewModel

@Composable
fun ListPaneSections(
    chatViewModel: ChatViewModel,
    windowSizeClass: WindowSizeClass,
    navigateToDetailPane: () -> Unit,
) {
    val savedChatHistoryList = chatViewModel.savedChatHistoryList
    val chatHistoryList = chatViewModel.chatHistoryList

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            SectionHeader(
                title = "Saved",
                onMenuClick = { /* Handle menu click */ }
            )
        }
        items(savedChatHistoryList) { chat ->
            ListPaneItem(
                chat = chat,
                onClick = {
                    chatViewModel.loadChatMessages(chat)
                    if (windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact) {
                        navigateToDetailPane()
                    }
                },
                onPinClick = {
                    chatViewModel.toggleBookmark(chat) // Pin/Unpin the chat
                },
                onDeleteClick = {
                    chatViewModel.deleteChatHistory(chat) // Delete the chat
                }
            )
        }
        item {
            SectionHeader(
                title = "Recent",
                onMenuClick = { /* Handle menu click */ }
            )
        }
        items(chatHistoryList) { chat ->
            ListPaneItem(
                chat = chat,
                onClick = {
                    chatViewModel.loadChatMessages(chat)
                    if (windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact) {
                        navigateToDetailPane()
                    }
                },
                onPinClick = {
                    chatViewModel.toggleBookmark(chat) // Pin/Unpin the chat
                },
                onDeleteClick = {
                    chatViewModel.deleteChatHistory(chat) // Delete the chat
                }
            )
        }
    }
}

@Composable
fun SectionHeader(
    title: String,
    onMenuClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.weight(1f)
        )
        IconButton(onClick = onMenuClick) {
            Icon(imageVector = Icons.Default.MoreVert, contentDescription = "Menu")
        }
    }
}