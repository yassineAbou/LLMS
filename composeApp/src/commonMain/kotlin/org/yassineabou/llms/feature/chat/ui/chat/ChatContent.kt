package org.yassineabou.llms.feature.chat.ui.chat

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import org.yassineabou.llms.app.core.data.remote.ai.GenerationState
import org.yassineabou.llms.app.core.sharedViews.SelectedModel
import org.yassineabou.llms.app.core.theme.colorSchemeCustom
import org.yassineabou.llms.app.core.util.Animations
import org.yassineabou.llms.feature.chat.data.model.TextModel
import org.yassineabou.llms.feature.chat.ui.ChatViewModel
import org.yassineabou.llms.feature.chat.ui.view.ChatAppBar
import org.yassineabou.llms.feature.chat.ui.view.ChatBubble
import org.yassineabou.llms.feature.chat.ui.view.TextModelsBottomSheet

@Composable
fun ChatContent(
    chatViewModel: ChatViewModel,
    selectedTextModel: TextModel,
    showAppBar: Boolean,
    onClickHistory: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    var text by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    var selectModelClicked by remember { mutableStateOf(false) }
    val chatMessages = chatViewModel.currentChatMessages
    val textGenerationState by chatViewModel.generationState.collectAsStateWithLifecycle()
    val isLoading = textGenerationState is GenerationState.Loading

    Box(modifier = modifier.fillMaxSize()) {
        if (showAppBar) {
            ChatAppBar(
                title = selectedTextModel.title,
                image = selectedTextModel.image,
                modifier = Modifier
                    .statusBarsPadding()
                    .fillMaxWidth(),
                onClickHistory = onClickHistory,
                onNewChatClick = { chatViewModel.startNewChat() },
                onSelect = { selectModelClicked = true }
            )
        } else {
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

@Composable
private fun ChatMessagesList(
    generationState: GenerationState,
    chatMessages: List<Chat_messages>,
    selectedTextModel: TextModel,
    regenerateResponse: (Int) -> Unit
) {
    val lazyListState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    val showScrollToBottom by remember {
        derivedStateOf { lazyListState.canScrollForward }
    }

    LaunchedEffect(chatMessages.size, chatMessages.lastOrNull()?.message) {
        if (chatMessages.isNotEmpty()) {
            launch {
                lazyListState.animateScrollToItem(chatMessages.lastIndex)
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize().padding(top = 70.dp, bottom = 80.dp)) {
        LazyColumn(
            state = lazyListState,
            modifier = Modifier.fillMaxSize()
        ) {
            itemsIndexed(chatMessages) { index, chatMessage ->
                val isLoading = generationState is GenerationState.Loading &&
                        chatMessage.is_user != 1L &&
                        generationState.id == index

                ChatBubble(
                    chatMessage = chatMessage,
                    aiIcon = selectedTextModel.image,
                    isLoading = isLoading,
                    regenerateResponse = { regenerateResponse(index) }
                )
            }
        }

        ScrollToBottomButton(
            visibility = showScrollToBottom,
            onClick = {
                coroutineScope.launch {
                    lazyListState.animateScrollToItem(chatMessages.lastIndex)
                }
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        )

    }
}

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
                .shadow(4.dp, CircleShape)
                .background(
                    color = MaterialTheme.colorScheme.primary,
                    shape = CircleShape
                )
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