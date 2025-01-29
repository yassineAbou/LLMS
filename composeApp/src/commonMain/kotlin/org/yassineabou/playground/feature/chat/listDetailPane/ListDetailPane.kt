package org.yassineabou.playground.feature.chat.listDetailPane

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.yassineabou.playground.feature.chat.model.ChatHistory
import org.yassineabou.playground.feature.chat.ui.ChatMessagesList
import org.yassineabou.playground.feature.chat.ui.ChatViewModel
import org.yassineabou.playground.feature.chat.ui.view.AIProvidersFilterMenu

@OptIn(ExperimentalMaterial3AdaptiveApi::class, ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun ListDetailPane(
    chatViewModel: ChatViewModel
) {
    // Use ChatHistory as the type for the navigator
    val navigator = rememberListDetailPaneScaffoldNavigator<ChatHistory>()

    // Handle back navigation
    /*
    BackHandler(navigator.canNavigateBack()) {
        navigator.navigateBack()
    }

     */

    val windowSizeClass = calculateWindowSizeClass()

    ListDetailPaneScaffold(
        directive = navigator.scaffoldDirective,
        value = navigator.scaffoldValue,
        listPane = {
            AnimatedPane {
                ChatListPane(
                    chatViewModel = chatViewModel,
                    windowSizeClass = windowSizeClass,
                    navigateToDetailPane = { navigator.navigateTo(ListDetailPaneScaffoldRole.Detail) }
                )
            }
        },
        detailPane = {
            AnimatedPane {
                // Check if the current destination is the Detail pane
                if (navigator.currentDestination?.pane == ListDetailPaneScaffoldRole.Detail) {
                    navigator.currentDestination?.content?.let { chat ->
                        ChatDetailPane(
                            chat = chat,
                            chatViewModel = chatViewModel,
                            windowSizeClass = windowSizeClass
                        )
                    }
                }
            }
        }
    )
}

@Composable
fun ChatListPane(
    chatViewModel: ChatViewModel,
    windowSizeClass: WindowSizeClass,
    navigateToDetailPane: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceContainerLow)
    ) {
        // Top Bar with Icons
        NewChatButton(
            onNewChatClick = { chatViewModel.startNewChat() }
        )

        // List Sections
        ListPaneSections(
            chatViewModel = chatViewModel,
            windowSizeClass = windowSizeClass,
            navigateToDetailPane = navigateToDetailPane
        )
    }
}

@Composable
fun ChatDetailPane(
    chat: ChatHistory,
    chatViewModel: ChatViewModel,
    windowSizeClass: WindowSizeClass
) {
    Column(modifier = Modifier.fillMaxSize()) {
        // Display chat messages
        if (chat.chatMessages.isNotEmpty()) {
            ChatMessagesList(
                chatMessages = chat.chatMessages,
                selectedTextModel = chatViewModel.selectedTextModel.value
            )
        } else {
            Text(
                text = "No messages in this chat.",
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}