package org.yassineabou.llms.feature.chat.ui.history

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import org.yassineabou.llms.app.core.navigation.Screen
import org.yassineabou.llms.feature.imagine.ui.view.ContentStateAnimator
import org.yassineabou.llms.feature.chat.ui.ChatViewModel
import org.yassineabou.llms.feature.chat.ui.view.ChatHistoryListView

@Composable
fun SavedChatHistoryContent(
    chatViewModel: ChatViewModel,
    navController: NavController
) {
    val savedChatHistoryList = chatViewModel.savedChatHistoryList

    Surface {
        ContentStateAnimator(
            contentList = savedChatHistoryList,
            contentComposable = { list ->
                ChatHistoryListView(
                    chatHistoryList = list,
                    removeChatHistory = {
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