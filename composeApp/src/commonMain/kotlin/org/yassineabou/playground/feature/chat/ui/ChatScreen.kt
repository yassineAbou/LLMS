package org.yassineabou.playground.feature.chat.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.AttachFile
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.yassineabou.playground.app.ui.navigation.Screen
import org.yassineabou.playground.app.ui.theme.colorSchemeCustom
import org.yassineabou.playground.feature.chat.model.ChatMessage
import org.yassineabou.playground.feature.chat.model.TextModel
import org.yassineabou.playground.feature.chat.ui.view.ChatAppBar
import org.yassineabou.playground.feature.chat.ui.view.ChatBubble
import org.yassineabou.playground.feature.chat.ui.view.ChooseActionBottomSheet
import org.yassineabou.playground.feature.chat.ui.view.TextGenTypesBottomSheet

@Composable
fun ChatScreen(
    navController: NavController,
    chatViewModel: ChatViewModel
) {
    val selectedTextModel by chatViewModel.selectedTextModel.collectAsState()
    var selectModelClicked by remember { mutableStateOf(false) }
    var attachButtonClicked by remember { mutableStateOf(false) }
    val chatMessages = chatViewModel.currentChatMessages
    var text by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {

        ChatAppBar(
            title = selectedTextModel.title,
            image = selectedTextModel.image,
            modifier = Modifier
                .statusBarsPadding()
                .fillMaxWidth()
                .padding(8.dp),
            onClickHistory = { navController.navigate(Screen.ChatHistoryScreen.route)},
            onNewChatClick = { chatViewModel.startNewChat() },
            onSelect = { selectModelClicked = true }
        )

        ChatMessagesList(
            chatMessages = chatMessages,
            selectedTextModel = selectedTextModel
        )

        AskAnythingField(
            modifier = Modifier.align(Alignment.BottomStart),
            onAttachClick = { /* Handle attachment */ },
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
}


@Composable
private fun ChatMessagesList(
    chatMessages: List<ChatMessage>,
    selectedTextModel: TextModel,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 70.dp, bottom = 80.dp) // Adjusted padding for app bar and input field
    ) {
        items(chatMessages) { message ->
            ChatBubble(
                message = message.message,
                isUser = message.isUser,
                aiIcon = selectedTextModel.image
            )
        }
    }
}



@Composable
private fun AskAnythingField(
    modifier: Modifier = Modifier,
    onAttachClick: () -> Unit,
    onSendClick: () -> Unit,
    text: String,
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
            leadingIcon = {
                AttachIcon(onAttachClick = onAttachClick)
            },
            trailingIcon = {
                if (text.isNotEmpty()) {
                    ClearIcon(
                        show = text.isNotEmpty(),
                        onClick = { onTextChange("") }
                    )
                }
            },
            singleLine = true
        )

        SendButton(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 16.dp)
                .size(40.dp)
                .background(
                    color = MaterialTheme.colorSchemeCustom.alwaysBlue,
                    shape = CircleShape
                ),
            onClick = onSendClick
        )
    }
}

@Composable
private fun AttachIcon(
    onAttachClick: () -> Unit
) = IconButton(onAttachClick) {
    Icon(
        imageVector = Icons.Filled.AttachFile,
        contentDescription = "Attachment",
        tint = MaterialTheme.colorScheme.onSurfaceVariant,
        modifier = Modifier.size(20.dp)
    )
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
private fun SendButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.Send,
            contentDescription = "Send",
            tint = Color.White,
            modifier = Modifier.size(20.dp)
        )
    }
}
