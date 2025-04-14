package org.yassineabou.playground.feature.chat.ui.view

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.yassineabou.playground.app.core.sharedViews.SnackbarController
import org.yassineabou.playground.app.core.theme.colorSchemeCustom
import org.yassineabou.playground.app.core.util.Animations


@Composable
fun ChatBubble(
    message: String,
    isUser: Boolean,
    aiIcon: DrawableResource,
    isLoading: Boolean,
    isGenerating: Boolean
) {
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp, end = 12.dp)
                .background(
                    color = if (isUser) MaterialTheme.colorScheme.surface else MaterialTheme.colorSchemeCustom.alwaysBlue.copy(
                        alpha = 0.5f
                    ),
                )
            ,
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            ChatBubbleIcon(isUser = isUser, aiIcon = aiIcon)

            when {
                isLoading -> LoadingIndicator()
                else -> ChatBubbleMessage(
                    message = message,
                    isUser = isUser,
                    isGenerating = isGenerating
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
private fun LoadingIndicator() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(20.dp),
            strokeWidth = 2.dp,
            color = MaterialTheme.colorSchemeCustom.alwaysWhite
        )
        Text(
            text = "Generating...",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorSchemeCustom.alwaysWhite
        )
    }
}

@Composable
private fun ChatBubbleMessage(
    message: String,
    isUser: Boolean,
    isGenerating: Boolean
) {
    if (isUser) {
        UserMessage(message = message)
    } else {
        AiMessage(
            message = message,
            isGenerating = isGenerating
        )
    }
}

@Composable
private fun UserMessage(message: String) {
    Text(
        text = message,
        color = MaterialTheme.colorScheme.onSurface,
        style = MaterialTheme.typography.titleMedium,
        modifier = Modifier.padding(top = 8.dp) // Add top padding to align with the icon
    )
}

@Composable
private fun AiMessage(
    message: String,
    isGenerating: Boolean
) {
    val clipboardManager = LocalClipboardManager.current
    val snackbarController = SnackbarController.current

    Column(
        modifier = Modifier
            .padding(top = 8.dp)
            .fillMaxWidth()
    ) {
        SelectionContainer {
            Text(
                text = message,
                color = MaterialTheme.colorSchemeCustom.alwaysWhite,
                style = MaterialTheme.typography.titleMedium
            )
        }

        AnimatedVisibility(
            visible = !isGenerating,
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
                        tint = MaterialTheme.colorSchemeCustom.alwaysWhite
                    )
                }
            }
        }
    }
}