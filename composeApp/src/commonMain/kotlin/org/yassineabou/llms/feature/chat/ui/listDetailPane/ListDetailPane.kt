package org.yassineabou.llms.feature.chat.ui.listDetailPane

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import llms.composeapp.generated.resources.Res
import llms.composeapp.generated.resources.ic_clear
import org.jetbrains.compose.resources.painterResource
import org.yassineabou.llms.Chats
import org.yassineabou.llms.app.core.util.Animations
import org.yassineabou.llms.feature.chat.ui.ChatViewModel
import org.yassineabou.llms.feature.chat.ui.chat.ChatContent
import org.yassineabou.llms.app.core.sharedViews.ConfirmationDialogContent
import org.yassineabou.llms.feature.imagine.ui.view.DropDownDialog
import org.yassineabou.llms.feature.imagine.ui.view.NoContentMessage

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun ListDetailPane(
    chatViewModel: ChatViewModel
) {
    // Use ChatHistory as the type for the navigator
    val navigator = rememberListDetailPaneScaffoldNavigator<Chats>()

    ListDetailPaneScaffold(
        directive = navigator.scaffoldDirective,
        value = navigator.scaffoldValue,
        listPane = {
            AnimatedPane {
                HistoryListPane(
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
private fun HistoryListPane(
    chatViewModel: ChatViewModel
) {
    var showClearHistoryDialog by remember { mutableStateOf(false) }
    val allChats by chatViewModel.allChats.collectAsStateWithLifecycle()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceContainerLow)
    ) {
        // Extracted Top Bar
        HistoryListPaneTop(
            onNewChatClick = { chatViewModel.startNewChat() },
            onClearClick = {
                if (allChats.isNotEmpty()) { // Check if the list is not empty
                    showClearHistoryDialog = true
                }
            },
        )

        // Animate between ListPaneSections and EmptyGeneratedMessage
        AnimatedVisibility(
            visible = allChats.isNotEmpty(),
            enter = Animations.fadeInExpand(), // Use the descriptive enter animation
            exit = Animations.fadeOutShrink()
        ) {
            // Show ListPaneSections when chatHistoryList is not empty
            ListPaneSections(
                chatViewModel = chatViewModel,
            )
        }

        AnimatedVisibility(
            visible = allChats.isEmpty(),
            enter = Animations.fadeInExpand(), // Use the descriptive enter animation
            exit = Animations.fadeOutShrink()
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
                ConfirmationDialogContent(
                    title = "Are you Sure?",
                    message = "This will permanently delete all your chat history. This action cannot be undone.",
                    onDismiss = { showClearHistoryDialog = false },
                    onConfirm = {
                        chatViewModel.clearChats()
                        showClearHistoryDialog = false
                    }
                )
            }
        }
    }
}

@Composable
private fun HistoryListPaneTop(
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