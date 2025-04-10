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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.yassineabou.playground.app.core.theme.colorSchemeCustom


@Composable
fun ChatBubble(
    message: String,
    isUser: Boolean,
    aiIcon: DrawableResource,
    isLoading: Boolean,
    errorMessage: String?
) {
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = if (isUser) MaterialTheme.colorScheme.surface else MaterialTheme.colorSchemeCustom.alwaysBlue.copy(
                        alpha = 0.5f
                    ),
                )
                .padding(16.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            ChatBubbleIcon(isUser = isUser, aiIcon = aiIcon)

            when {
                isLoading -> LoadingIndicator()
                errorMessage != null -> ErrorMessage(errorMessage)
                else -> ChatBubbleMessage(
                    message = message,
                    isUser = isUser
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
private fun ErrorMessage(message: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.errorContainer,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(12.dp)
    ) {
        Icon(
            imageVector = Icons.Default.ErrorOutline,
            contentDescription = "Error",
            tint = MaterialTheme.colorScheme.error
        )
        Text(
            text = message,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.error
        )
    }
}

@Composable
private fun ChatBubbleMessage(
    message: String,
    isUser: Boolean,
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
