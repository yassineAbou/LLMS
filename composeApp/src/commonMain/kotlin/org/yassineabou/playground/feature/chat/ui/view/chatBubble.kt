package org.yassineabou.playground.feature.chat.ui.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Autorenew
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.mikepenz.markdown.m3.Markdown
import com.mikepenz.markdown.m3.markdownColor
import com.mikepenz.markdown.m3.markdownTypography
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.yassineabou.playground.app.core.sharedViews.LoadingContent
import org.yassineabou.playground.app.core.sharedViews.SnackbarController
import org.yassineabou.playground.app.core.theme.colorSchemeCustom
import org.yassineabou.playground.app.core.util.Animations
import org.yassineabou.playground.feature.chat.data.model.ChatMessageModel


@Composable
fun ChatBubble(
    chatMessage: ChatMessageModel,
    aiIcon: DrawableResource,
    isLoading: Boolean,
    regenerateResponse: () -> Unit
) {
    val backgroundColor = when {
        chatMessage.isUser -> MaterialTheme.colorScheme.surface
        isLoading -> Color.Black
        else -> MaterialTheme.colorSchemeCustom.alwaysBlue.copy(alpha = 0.5f)
    }
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp, end = 12.dp)
                .background(backgroundColor),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            ChatBubbleIcon(isUser = chatMessage.isUser, aiIcon = aiIcon)

            when {
                isLoading -> {
                    LoadingContent(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                            .animateContentSize()
                    )
                }
                else -> ChatBubbleMessage(
                    chatMessage = chatMessage,
                    isLoading = isLoading,
                    regenerateResponse = regenerateResponse
                )
            }

        }
    }
}

@Composable
private fun ChatBubbleIcon(
    isUser: Boolean,
    aiIcon: DrawableResource
) {
    if (isUser) {
        UserIcon()
    } else {
        AiProviderIcon(aiIcon = aiIcon)
    }
}

@Composable
private fun UserIcon() {

    Box(
        modifier = Modifier
            .size(40.dp)
            .background(
                MaterialTheme.colorScheme.background,
                CircleShape
            ) // Background for the icon
            .padding(end = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Default.Person,
            contentDescription = "User Icon",
            tint = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
private fun AiProviderIcon(aiIcon: DrawableResource) {
    Box(
        modifier = Modifier
            .size(30.dp)
            .padding(start = 4.dp, top = 4.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(aiIcon),
            contentDescription = "AI Icon"
        )
    }

}

@Composable
private fun ChatBubbleMessage(
    chatMessage: ChatMessageModel,
    isLoading: Boolean,
    regenerateResponse: () -> Unit
) {
    if (chatMessage.isUser) {
        UserMessage(message = chatMessage.message)
    } else {
        AiMessage(
            message = chatMessage.message,
            isLoading = isLoading,
            regenerateResponse = regenerateResponse
        )
    }
}

@Composable
private fun UserMessage(message: String) {
    SelectionContainer {
        Text(
            text = message,
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

@Composable
private fun AiMessage(
    message: String,
    isLoading: Boolean,
    regenerateResponse: () -> Unit
) {
    val clipboardManager = LocalClipboardManager.current
    val snackbarController = SnackbarController.current

    Column(
        modifier = Modifier
            .padding(top = 8.dp)
            .fillMaxWidth()
    ) {
        Markdown(
            content = message.trimIndent(),
            colors = markdownColor(
                text = Color.White,
            ),
            typography = markdownTypography(
                // Header hierarchy
                h1 = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold
                ),
                h2 = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.SemiBold,
                    textDecoration = TextDecoration.Underline // Add underline
                ),
                h3 = MaterialTheme.typography.titleMedium.copy(
                    fontStyle = FontStyle.Italic // Italic text
                ),
                h4 = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Medium // Moderate boldness
                ),
                h5 = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Normal // Base weight
                ),
                h6 = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Light, // Light weight
                    fontStyle = FontStyle.Italic // Combined with italics
                )
            )
        )

        AnimatedVisibility(
            visible = !isLoading,
            enter = Animations.slideFadeIn(),
            exit = Animations.slideFadeOut()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(
                    onClick = {
                        clipboardManager.setText(AnnotatedString(message))
                        snackbarController.showMessage("Copied to clipboard")
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.ContentCopy,
                        contentDescription = "Copy",
                        tint = Color.White
                    )
                }
                IconButton(
                    onClick = regenerateResponse
                ) {
                    Icon(
                        imageVector = Icons.Default.Autorenew,
                        contentDescription = "Regenerate",
                        tint = Color.White
                    )
                }
            }
        }
    }
}