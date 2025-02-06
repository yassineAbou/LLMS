package org.yassineabou.playground.feature.chat.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.yassineabou.playground.app.ui.theme.colorSchemeCustom


@Composable
fun ChatBubble(
    message: String,
    isUser: Boolean,
    aiIcon: DrawableResource,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        // Background color for the entire row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = if (isUser) MaterialTheme.colorScheme.surface else MaterialTheme.colorSchemeCustom.alwaysBlue.copy(
                        alpha = 0.5f
                    ),
                )
                .padding(16.dp), // Only padding inside the row
            verticalAlignment = Alignment.Top, // Align to the top
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Icon for AI or User
            ChatBubbleIcon(isUser = isUser, aiIcon = aiIcon)

            // Message text with typewriter animation for AI messages
            ChatBubbleMessage(
                message = message,
                isUser = isUser
            )
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
            .padding(8.dp),
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
    Image(
        painter = painterResource(aiIcon),
        contentDescription = "AI Icon",
        modifier = Modifier.size(30.dp)
    )
}

@Composable
private fun ChatBubbleMessage(
    message: String,
    isUser: Boolean
) {
    if (isUser) {
        UserMessage(message = message)
    } else {
        AiMessage(
            message = message
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
    message: String
) {

    Text(
        text = message,
        color = MaterialTheme.colorSchemeCustom.alwaysWhite,
        style = MaterialTheme.typography.titleMedium,
        modifier = Modifier.padding(top = 8.dp)
    )
}
