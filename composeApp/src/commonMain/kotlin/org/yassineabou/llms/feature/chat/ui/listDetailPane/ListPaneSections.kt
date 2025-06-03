package org.yassineabou.llms.feature.chat.ui.listDetailPane

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
import org.yassineabou.llms.app.core.util.Animations
import org.yassineabou.llms.feature.chat.ui.ChatViewModel

@Composable
fun ListPaneSections(
    chatViewModel: ChatViewModel
) {
    val savedChats by chatViewModel.savedChats.collectAsStateWithLifecycle()
    val recentChats by chatViewModel.recentChats.collectAsStateWithLifecycle()
    val selectedChats by chatViewModel.selectedChats.collectAsStateWithLifecycle()

    LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp)
        ) {
            item {
                AnimatedVisibility(
                    visible = savedChats.isNotEmpty(),
                    enter = Animations.fadeInExpand(), // Use the descriptive enter animation
                    exit = Animations.fadeOutShrink()
                ) {
                    SectionHeader(title = "Saved")
                }
            }
            items(savedChats) { chat ->
                ListPaneItem(
                    chats = chat,
                    selected = chat == selectedChats,
                    onClick = {
                        chatViewModel.selectChats(chat)
                    },
                    onPinClick = { chatViewModel.toggleBookmark(chat) },
                    onDeleteClick = {chatViewModel.deleteChats(chat) }
                )

            }
            item {
                AnimatedVisibility(
                    visible = recentChats.isNotEmpty(),
                    enter = Animations.fadeInExpand(), // Use the descriptive enter animation
                    exit = Animations.fadeOutShrink()
                ) {
                    SectionHeader(title = "Recent")
                }
            }
            items(recentChats) { chat ->
                ListPaneItem(
                    chats = chat,
                    selected = chat == selectedChats,
                    onClick = {
                        chatViewModel.selectChats(chat)
                    },
                    onPinClick = { chatViewModel.toggleBookmark(chat) },
                    onDeleteClick = { chatViewModel.deleteChats(chat) }
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
