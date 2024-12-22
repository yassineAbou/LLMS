package org.yassineabou.playground.feature.chat.ui.history

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import org.koin.compose.viewmodel.koinViewModel
import org.yassineabou.playground.feature.Imagine.view.ContentStateAnimator
import org.yassineabou.playground.feature.chat.ui.ChatViewModel
import org.yassineabou.playground.feature.chat.ui.view.ChatHistoryListView

@Composable
fun RecentChatHistoryContent(
    chatViewModel: ChatViewModel = koinViewModel()
) {

    val chatHistoryList = chatViewModel.chatHistoryList
    val selectedAIProviders = chatViewModel.selectedAIProviders.collectAsState()

    Surface {
        ContentStateAnimator(
            contentType = "Recent Chats History",
            contentList = chatHistoryList.filter { conversation ->
                selectedAIProviders.value[conversation.aiProvider.name] == true
            },
            contentComposable = { list ->
                ChatHistoryListView(
                    historyConversationList = list,
                    removeHistoryConversation = {
                        chatViewModel.deleteChatHistory(it)
                        chatViewModel.toggleBookmark(it)
                    },
                    toggleBookmark = { chatViewModel.toggleBookmark(it) }
                )
            }
        )
    }
}