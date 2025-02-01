package org.yassineabou.playground.feature.chat.listDetailPane

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.yassineabou.playground.app.ui.util.fadeInAndExpand
import org.yassineabou.playground.app.ui.util.fadeOutAndShrink
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
                .padding(horizontal = 8.dp)
        ) {
            item {
                AnimatedVisibility(
                    visible = savedChatHistoryList.isNotEmpty(),
                    enter = fadeInAndExpand(), // Use the descriptive enter animation
                    exit = fadeOutAndShrink()
                ) {
                    SectionHeader(title = "Saved")
                }
            }
            items(savedChatHistoryList) { chat ->
                ListPaneItem(
                    chat = chat,
                    selected = selectedItem.value == chat,
                    onClick = { selectedItem.value = chat },
                    onPinClick = { chatViewModel.toggleBookmark(chat) },
                    onDeleteClick = {chatViewModel.deleteChatHistory(chat) }
                )

            }
            item {
                SectionHeader(title = "Recent")
            }
            items(chatHistoryList) { chat ->
                ListPaneItem(
                    chat = chat,
                    selected = selectedItem.value == chat,
                    onClick = { selectedItem.value = chat },
                    onPinClick = { chatViewModel.toggleBookmark(chat) },
                    onDeleteClick = {chatViewModel.deleteChatHistory(chat) }
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
