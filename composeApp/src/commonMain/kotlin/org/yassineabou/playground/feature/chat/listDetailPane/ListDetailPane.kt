package org.yassineabou.playground.feature.chat.listDetailPane

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.yassineabou.playground.app.ui.theme.colorSchemeCustom
import org.yassineabou.playground.feature.chat.model.ChatHistory
import org.yassineabou.playground.feature.chat.ui.AskAnythingField
import org.yassineabou.playground.feature.chat.ui.ChatMessagesList
import org.yassineabou.playground.feature.chat.ui.ChatViewModel
import org.yassineabou.playground.feature.chat.ui.view.AIProvidersFilterMenu
import org.yassineabou.playground.feature.chat.ui.view.ChooseActionBottomSheet
import org.yassineabou.playground.feature.chat.ui.view.SelectedTextModel
import org.yassineabou.playground.feature.chat.ui.view.TextGenTypesBottomSheet

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
                //if (navigator.currentDestination?.pane == ListDetailPaneScaffoldRole.Detail) {
                    //navigator.currentDestination?.content?.let { chat ->
                        val selectedTextModel by chatViewModel.selectedTextModel.collectAsStateWithLifecycle()
                        var selectModelClicked by remember { mutableStateOf(false) }
                        var attachButtonClicked by remember { mutableStateOf(false) }
                        val chatMessages = chatViewModel.currentChatMessages
                        var text by remember { mutableStateOf("") }
                        val keyboardController = LocalSoftwareKeyboardController.current
                        val focusManager = LocalFocusManager.current
                        Box(modifier = Modifier.fillMaxSize()) {
                            SelectedTextModel(
                                title = selectedTextModel.title,
                                image = selectedTextModel.image,
                                modifier = Modifier
                                    .align(Alignment.TopCenter)
                                    .padding(8.dp)
                                    .clickable { selectModelClicked = true  }
                                    .background(
                                        color = MaterialTheme.colorSchemeCustom.alwaysBlue.copy(alpha = 0.5f),
                                        shape = RoundedCornerShape(8.dp)
                                    )
                                    .padding(6.dp)
                            )

                            ChatMessagesList(
                                chatMessages = chatMessages,
                                selectedTextModel = selectedTextModel
                            )
                            AskAnythingField(
                                modifier = Modifier.align(Alignment.BottomStart),
                                onAttachClick = { attachButtonClicked = true },
                                onSendClick = {
                                    if (text.isNotEmpty()) {
                                        chatViewModel.sendMessage(text)
                                        text = ""
                                        keyboardController?.hide()
                                        focusManager.clearFocus()
                                    }
                                },
                                text = text,
                                onTextChange = { text = it }
                            )

                            if (selectModelClicked) {
                                TextGenTypesBottomSheet(
                                    onDismissRequest = { selectModelClicked = false },
                                    onAuthenticated = {
                                        selectModelClicked = false
                                    }
                                )
                            }
                            if (attachButtonClicked) {
                                ChooseActionBottomSheet(
                                    onActionSeletced = { attachButtonClicked = false },
                                    onDismissRequest = { attachButtonClicked = false }
                                )
                            }
                        }
                   // }
               // }
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
        Row {
            // Top Bar with Icons
            NewChatButton(
                onNewChatClick = { chatViewModel.startNewChat() }
            )
            Spacer(modifier = Modifier.weight(1F))
            IconButton(
                modifier = Modifier.padding(top = 12.dp),
                onClick = {}
            ) {
                Icon(
                    contentDescription = "Clear",
                    imageVector = Icons.Default.DeleteForever
                )
            }
        }

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