package org.yassineabou.llms.feature.chat.ui.history

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.yassineabou.llms.app.core.navigation.ChatRoute
import org.yassineabou.llms.app.core.navigation.Navigator
import org.yassineabou.llms.feature.chat.ui.ChatViewModel
import org.yassineabou.llms.feature.chat.ui.view.ChatHistoryListView
import org.yassineabou.llms.feature.imagine.ui.view.ContentStateAnimator

@Composable
fun RecentChatHistoryContent(
    chatViewModel: ChatViewModel,
    navigator: Navigator
) {
    val recentChats by chatViewModel.recentChats.collectAsStateWithLifecycle()
    val availableModels by chatViewModel.availableTextModels.collectAsStateWithLifecycle()

    Surface {
        ContentStateAnimator(
            contentList =  recentChats,
            contentComposable = { list ->
                ChatHistoryListView(
                    chats = list,
                    availableModels = availableModels,
                    deleteChats = { chatViewModel.deleteChats(it) },
                    toggleBookmark = { chatViewModel.toggleBookmark(it) },
                    onClick = { chatHistory ->
                        chatViewModel.selectChats(chatHistory)
                        navigator.navigate(ChatRoute)
                    }
                )
            },
            navigateToChatScreen = { navigator.navigate(ChatRoute) }
        )
    }
}