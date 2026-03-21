package org.yassineabou.llms.feature.chat.ui.chat

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.yassineabou.llms.app.core.navigation.ChatHistoryRoute
import org.yassineabou.llms.app.core.navigation.Navigator
import org.yassineabou.llms.feature.chat.ui.ChatViewModel
import org.yassineabou.llms.feature.chat.ui.listDetailPane.ListDetailPane
import org.yassineabou.llms.feature.imagine.ui.util.rememberIsLargeScreen


@Composable
fun ChatScreen(
    navigator: Navigator,
    chatViewModel: ChatViewModel
) {
    val isLargeScreen = rememberIsLargeScreen()
    val selectedTextModel by chatViewModel.selectedTextModel.collectAsStateWithLifecycle()

    if (isLargeScreen) {
        // Show ListDetailPane for medium or larger screens
        ListDetailPane(chatViewModel = chatViewModel)
    } else {

        ChatContent(
            chatViewModel = chatViewModel,
            selectedTextModel = selectedTextModel,
            showAppBar = true,
            onClickHistory = { navigator.navigate(ChatHistoryRoute) }
        )
    }
}




