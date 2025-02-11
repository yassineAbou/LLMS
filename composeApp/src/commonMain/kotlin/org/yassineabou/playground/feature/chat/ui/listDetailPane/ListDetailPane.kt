package org.yassineabou.playground.feature.chat.ui.listDetailPane

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import llms.composeapp.generated.resources.Res
import llms.composeapp.generated.resources.ic_clear
import org.jetbrains.compose.resources.painterResource
import org.yassineabou.playground.app.core.util.fadeInExpand
import org.yassineabou.playground.app.core.util.fadeOutShrink
import org.yassineabou.playground.feature.Imagine.ui.view.DropDownDialog
import org.yassineabou.playground.feature.Imagine.ui.view.NoContentMessage
import org.yassineabou.playground.feature.chat.model.ChatHistory
import org.yassineabou.playground.feature.chat.ui.ChatViewModel
import org.yassineabou.playground.feature.chat.ui.chat.ChatContent
import org.yassineabou.playground.feature.chat.ui.view.ClearHistoryDialogContent

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun ListDetailPane(
    chatViewModel: ChatViewModel
) {
    // Use ChatHistory as the type for the navigator
    val navigator = rememberListDetailPaneScaffoldNavigator<ChatHistory>()

    ListDetailPaneScaffold(
        directive = navigator.scaffoldDirective,
        value = navigator.scaffoldValue,
        listPane = {
            AnimatedPane {
                ChatListPane(
                    chatViewModel = chatViewModel,
                )
            }
        },
        detailPane = {
            AnimatedPane {
                val selectedTextModel by chatViewModel.selectedTextModel.collectAsStateWithLifecycle()
                ChatContent(
                    chatViewModel = chatViewModel,
                    selectedTextModel = selectedTextModel,
                    showAppBar = false,
                )
            }
        }
    )
}

@Composable
fun ChatListPane(
    chatViewModel: ChatViewModel
) {
    var showClearHistoryDialog by remember { mutableStateOf(false) }
    val chatHistoryList = chatViewModel.chatHistoryList
    val savedChatHistoryList = chatViewModel.savedChatHistoryList
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceContainerLow)
    ) {
        // Extracted Top Bar
        ChatListPaneTop(
            onNewChatClick = { chatViewModel.startNewChat() },
            onClearClick = {
                if (chatHistoryList.isNotEmpty()) { // Check if the list is not empty
                    showClearHistoryDialog = true
                }
            },
        )

        // Animate between ListPaneSections and EmptyGeneratedMessage
        AnimatedVisibility(
            visible = chatHistoryList.isNotEmpty() or savedChatHistoryList.isNotEmpty(),
            enter = fadeInExpand(), // Use the descriptive enter animation
            exit = fadeOutShrink()
        ) {
            // Show ListPaneSections when chatHistoryList is not empty
            ListPaneSections(
                chatViewModel = chatViewModel,
            )
        }

        AnimatedVisibility(
            visible = chatHistoryList.isEmpty() and savedChatHistoryList.isEmpty(),
            enter = fadeInExpand(), // Use the descriptive enter animation
            exit = fadeOutShrink()
        ) {
            // Show EmptyGeneratedMessage when chatHistoryList is empty
            NoContentMessage(
                modifier = Modifier.fillMaxSize(),
                title = "Start a Conversation!",
                subtitle = "Explore AI-generated responses and have meaningful discussions.",
                buttonText = "Get Started",
                onButtonClick = { chatViewModel.startNewChat() }
            )
        }

        if (showClearHistoryDialog) {
            DropDownDialog(
                onDismissRequest = {
                    showClearHistoryDialog = false
                }
            ) {
                ClearHistoryDialogContent(
                    onDismiss = { showClearHistoryDialog = false },
                    onConfirm = {
                        chatViewModel.clearChatHistory()
                        showClearHistoryDialog = false
                    }
                )
            }
        }
    }
}

@Composable
fun ChatListPaneTop(
    onNewChatClick: () -> Unit,
    onClearClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Top Bar with Icons
        NewChatButton(
            onNewChatClick = onNewChatClick
        )
        // Extracted Clear Icon Button
        ClearIconButton(
            onClearClick = onClearClick
        )
    }
}

@Composable
private fun ClearIconButton(
    onClearClick: () -> Unit,
) {
    IconButton(
        modifier = Modifier.padding(top = 12.dp),
        onClick = onClearClick
    ) {
        Icon(
            contentDescription = "Clear",
            painter = painterResource(Res.drawable.ic_clear),
            tint = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.size(24.dp)
        )
    }
}