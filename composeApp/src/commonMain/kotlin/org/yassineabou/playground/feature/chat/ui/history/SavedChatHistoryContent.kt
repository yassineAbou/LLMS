package org.yassineabou.playground.feature.chat.ui.history

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import org.yassineabou.playground.app.ui.navigation.Screen
import org.yassineabou.playground.feature.Imagine.view.ContentStateAnimator
import org.yassineabou.playground.feature.chat.ui.ChatViewModel
import org.yassineabou.playground.feature.chat.ui.view.ChatHistoryListView

@Composable
fun SavedChatHistoryContent(
    chatViewModel: ChatViewModel,
    navController: NavController
) {
    val savedChatHistoryList = chatViewModel.savedChatHistoryList
    val selectedAIProviders = chatViewModel.selectedAIProviders.collectAsState()

    Surface {
        ContentStateAnimator(
            contentList = savedChatHistoryList.filter { conversation ->
                selectedAIProviders.value[conversation.aiProvider.name] == true
            },
            contentComposable = { list ->
                ChatHistoryListView(
                    historyConversationList = list,
                    removeHistoryConversation = {
                        chatViewModel.deleteChatHistory(it)
                        chatViewModel.toggleBookmark(it)
                    },
                    toggleBookmark = { chatViewModel.toggleBookmark(it) },
                    onClick = { chatHistory ->
                        chatViewModel.selectChatHistory(chatHistory)
                        navController.navigate(Screen.ChatScreen.route)
                    }
                )
            },
            navigateToChatScreen = { navController.navigate(Screen.ChatScreen.route) }
        )
    }
}