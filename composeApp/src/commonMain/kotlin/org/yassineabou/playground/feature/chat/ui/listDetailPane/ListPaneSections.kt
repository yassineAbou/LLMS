package org.yassineabou.playground.feature.chat.ui.listDetailPane

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.yassineabou.playground.app.core.util.Animations
import org.yassineabou.playground.feature.chat.ui.ChatViewModel

@Composable
fun ListPaneSections(
    chatViewModel: ChatViewModel
) {
    val savedChatHistoryList = chatViewModel.savedChatHistoryList
    val chatHistoryList = chatViewModel.chatHistoryList
    val selectedChatHistory by chatViewModel.selectedChatHistory.collectAsStateWithLifecycle()

    LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp)
        ) {
            item {
                AnimatedVisibility(
                    visible = savedChatHistoryList.isNotEmpty(),
                    enter = Animations.fadeInExpand(), // Use the descriptive enter animation
                    exit = Animations.fadeOutShrink()
                ) {
                    SectionHeader(title = "Saved")
                }
            }
            items(savedChatHistoryList) { chat ->
                ListPaneItem(
                    chat = chat,
                    selected = chat == selectedChatHistory,
                    onClick = {
                        chatViewModel.selectChatHistory(chat)
                    },
                    onPinClick = { chatViewModel.toggleBookmark(chat) },
                    onDeleteClick = {chatViewModel.deleteChatHistory(chat) }
                )

            }
            item {
                AnimatedVisibility(
                    visible = chatHistoryList.isNotEmpty(),
                    enter = Animations.fadeInExpand(), // Use the descriptive enter animation
                    exit = Animations.fadeOutShrink()
                ) {
                    SectionHeader(title = "Recent")
                }
            }
            items(chatHistoryList) { chat ->
                ListPaneItem(
                    chat = chat,
                    selected = chat == selectedChatHistory,
                    onClick = {
                        chatViewModel.selectChatHistory(chat)
                    },
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
