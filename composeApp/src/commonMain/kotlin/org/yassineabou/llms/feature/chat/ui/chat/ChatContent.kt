package org.yassineabou.llms.feature.chat.ui.chat

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardDoubleArrowDown
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.launch
import org.yassineabou.llms.Chat_messages
import org.yassineabou.llms.app.core.sharedViews.SelectedModel
import org.yassineabou.llms.app.core.theme.colorSchemeCustom
import org.yassineabou.llms.app.core.util.Animations
import org.yassineabou.llms.app.core.data.remote.GenerationState
import org.yassineabou.llms.feature.chat.data.model.TextModel
import org.yassineabou.llms.feature.chat.ui.ChatViewModel
import org.yassineabou.llms.feature.chat.ui.view.ChatAppBar
import org.yassineabou.llms.feature.chat.ui.view.ChatBubble
import org.yassineabou.llms.feature.chat.ui.view.TextModelsBottomSheet

@Composable
fun ChatContent(
    chatViewModel: ChatViewModel,
    selectedTextModel: TextModel,
    showAppBar: Boolean, // New parameter to control visibility
    onClickHistory: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    var text by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    var selectModelClicked by remember { mutableStateOf(false) }
    val chatMessages = chatViewModel.currentChatMessages
    val textGenerationState by  chatViewModel.generationState.collectAsStateWithLifecycle()
    val isLoading = textGenerationState is GenerationState.Loading

    Box(modifier = modifier.fillMaxSize()) {
        if (showAppBar) {
            // Show ChatAppBar if showAppBar is true
            ChatAppBar(
                title = selectedTextModel.title,
                image = selectedTextModel.image,
                modifier = Modifier
                    .statusBarsPadding()
                    .fillMaxWidth(),
                onClickHistory = onClickHistory,
                onNewChatClick = { chatViewModel.startNewChat() },
                onSelect = { selectModelClicked = true  }
            )
        } else {
            // Show SelectedTextModel if showAppBar is false
            SelectedModel(
                title = selectedTextModel.title,
                image = selectedTextModel.image,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(8.dp)
                    .clickable { selectModelClicked = true }
                    .background(
                        color = MaterialTheme.colorSchemeCustom.alwaysBlue.copy(alpha = 0.5f),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(6.dp)
            )
        }

        ChatMessagesList(
            chatMessages = chatMessages,
            selectedTextModel = selectedTextModel,
            generationState = textGenerationState,
            regenerateResponse = { index -> chatViewModel.regenerateResponse(index) }
        )

        AskAnythingField(
            modifier = Modifier.align(Alignment.BottomStart),
            onSendClick = {
                if (isLoading) {
                    chatViewModel.stopGeneration()
                } else {
                    if (text.isNotEmpty()) {
                        chatViewModel.sendMessage(text)
                        text = ""
                        keyboardController?.hide()
                        focusManager.clearFocus()
                    }
                }
            },
            text = text,
            onTextChange = { text = it },
            isLoading = isLoading
        )

        if (selectModelClicked) {
            TextModelsBottomSheet(
                chatViewModel = chatViewModel,
                onDismissRequest = { selectModelClicked = false },
                onAuthenticated = { selectModelClicked = false }
            )
        }
    }
}

// Extracted scroll-to-bottom button component
@Composable
private fun ScrollToBottomButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    visibility: Boolean
) {
    AnimatedVisibility(
        visible = visibility,
        enter = Animations.slideFadeIn(),
        exit = Animations.slideFadeOut(),
        modifier = modifier
    ) {
        IconButton(
            onClick = onClick,
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.primary,
                    shape = CircleShape
                )
                .shadow(4.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.KeyboardDoubleArrowDown,
                contentDescription = "Scroll to bottom",
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}


@Composable
private fun ChatMessagesList(
    generationState: GenerationState,
    chatMessages: List<Chat_messages>,
    selectedTextModel: TextModel,
    regenerateResponse: (Int) -> Unit
) {
    val lazyListState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    // Track scroll position to show/hide button
    val showScrollButton by remember {
        derivedStateOf {
            val layoutInfo = lazyListState.layoutInfo
            val totalItems = chatMessages.size
            if (totalItems == 0) false else {
                val lastVisibleItem = layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
                lastVisibleItem < totalItems - 1
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            state = lazyListState,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 70.dp, bottom = 80.dp)
        ) {
            itemsIndexed(chatMessages) { index, chatMessage ->
                val isLoading = when (generationState) {
                    is GenerationState.Loading -> {
                        // Only show loading for the specific AI message index
                        chatMessage.is_user != 1L && generationState.id == index
                    }
                    else -> false
                }

                ChatBubble(
                    chatMessage = chatMessage,
                    aiIcon = selectedTextModel.image,
                    isLoading = isLoading,
                    regenerateResponse = { regenerateResponse(index) }
                )
            }
        }

        ScrollToBottomButton(
            onClick = {
                coroutineScope.launch {
                    lazyListState.animateScrollToItem(chatMessages.lastIndex)
                }
            },
            visibility = showScrollButton,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 100.dp, end = 8.dp)
        )
    }

    // Existing auto-scroll logic
    val lastMessageLength by remember(chatMessages.size) {
        derivedStateOf { chatMessages.lastOrNull()?.message?.length ?: 0 }
    }

    LaunchedEffect(chatMessages.size, lastMessageLength) {
        if (chatMessages.isNotEmpty()) {
            val lastIndex = chatMessages.lastIndex
            val scrollThreshold = 3
            val layoutInfo = lazyListState.layoutInfo
            val visibleItems = layoutInfo.visibleItemsInfo
            if ((visibleItems.lastOrNull()?.index ?: 0) >= lastIndex - scrollThreshold) {
                lazyListState.scrollToItem(lastIndex)
            }
        }
    }
}

@Composable
fun AskAnythingField(
    text: String,
    isLoading: Boolean,
    modifier: Modifier = Modifier,
    onSendClick: () -> Unit,
    onTextChange: (String) -> Unit
) {
    Box(modifier) {
        OutlinedTextField(
            value = text,
            onValueChange = onTextChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 56.dp)
                .padding(start = 16.dp, end = 8.dp, top = 16.dp, bottom = 16.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorSchemeCustom.askAnythingBgColor,
                unfocusedBorderColor = MaterialTheme.colorSchemeCustom.askAnythingBgColor,
                focusedContainerColor = MaterialTheme.colorSchemeCustom.askAnythingBgColor,
                unfocusedContainerColor = MaterialTheme.colorSchemeCustom.askAnythingBgColor,
                disabledContainerColor = MaterialTheme.colorSchemeCustom.askAnythingBgColor
            ),
            shape = MaterialTheme.shapes.extraLarge,
            placeholder = {
                Text(
                    text = "Ask anything",
                    color = MaterialTheme.colorScheme.onBackground
                )
            },
            trailingIcon = {
                if (text.isNotEmpty()) {
                    ClearIcon(
                        show = text.isNotEmpty(),
                        onClick = { onTextChange("") }
                    )
                }
            }
        )

        SendStopButton(
            isGenerating = isLoading,
            modifier = Modifier
                .padding(end = 16.dp)
                .size(40.dp)
                .background(MaterialTheme.colorSchemeCustom.alwaysBlue, CircleShape)
                .align(Alignment.CenterEnd),
            onClick = onSendClick,
        )
    }
}

@Composable
private fun ClearIcon(
    show: Boolean,
    onClick: () -> Unit
) {
    if (show) {
        IconButton(onClick = onClick) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Clear",
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

@Composable
fun SendStopButton(
    isGenerating: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        modifier = modifier

    ) {
        Icon(
            imageVector = if (isGenerating) Icons.Filled.Stop
            else Icons.AutoMirrored.Filled.Send,
            contentDescription = if (isGenerating) "Stop generation" else "Send message",
            tint = Color.White,
            modifier = Modifier.size(20.dp)
        )
    }
}