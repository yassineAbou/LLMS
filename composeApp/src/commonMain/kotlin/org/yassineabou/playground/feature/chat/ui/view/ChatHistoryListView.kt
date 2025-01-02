package org.yassineabou.playground.feature.chat.ui.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import org.yassineabou.playground.app.ui.theme.colorSchemeCustom
import org.yassineabou.playground.feature.chat.model.AIProvider
import org.yassineabou.playground.feature.chat.model.ChatHistory


@Composable
fun ChatHistoryListView(
    historyConversationList: List<ChatHistory>,
    removeHistoryConversation: (ChatHistory) -> Unit,
    toggleBookmark: (ChatHistory) -> Unit,
    onClick: (ChatHistory) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(
            historyConversationList,
        ) { conversationItem ->
            ChatHistoryCard(
                conversation = conversationItem,
                removeHistoryConversation = { removeHistoryConversation(it) },
                toggleBookmark = { toggleBookmark(it) },
                onClick = { onClick(it) }
            )
        }
    }
}

@Composable
fun ChatHistoryCard(
    conversation: ChatHistory,
    removeHistoryConversation: (ChatHistory) -> Unit,
    toggleBookmark: (ChatHistory) -> Unit,
    onClick: (ChatHistory) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(conversation) },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            ChatHistoryContent(
                title = conversation.title,
                content = conversation.description
            )
            ChatHistoryAction(
                aiProvider = conversation.aiProvider,
                isBookmarked = conversation.isBookmarked,
                deleteConversationFromHistory = { removeHistoryConversation(conversation) },
                toggleBookmark = { toggleBookmark(conversation) },
            )
        }
    }
}

@Composable
fun ChatHistoryContent(
    title: String,
    content: String
) {
    Column {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = content,
            style = MaterialTheme.typography.bodyMedium,
        )
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Composable
fun ChatHistoryAction(
    aiProvider: AIProvider,
    isBookmarked: Boolean,
    toggleBookmark: () -> Unit,
    deleteConversationFromHistory: () -> Unit,

    ) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AiProviderInfo(aiProvider = aiProvider)
        ActionButtons(
            isBookmarked = isBookmarked,
            toggleBookmark = toggleBookmark,
            deleteConversationFromHistory = deleteConversationFromHistory,
        )
    }
}

@Composable
fun AiProviderInfo(aiProvider: AIProvider) {
    Row(
        modifier = Modifier
            .border(BorderStroke(1.dp, MaterialTheme.colorSchemeCustom.alwaysGray), shape = RoundedCornerShape(8.dp))
            .padding(6.dp),
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(resource = aiProvider.icon),
            contentDescription = "",
            modifier = Modifier.size(20.dp)
        )
        Text(
            text = aiProvider.name,
            style = MaterialTheme.typography.titleSmall,
        )
    }
}

@Composable
fun ActionButtons(
    isBookmarked: Boolean,
    toggleBookmark: () -> Unit,
    deleteConversationFromHistory: () -> Unit,
) {
    // Use a mutable state that updates with the passed 'isBookmarked'
    var isBookmarkedState by remember { mutableStateOf(isBookmarked) }

    // Update the state whenever 'isBookmarked' changes
    LaunchedEffect(isBookmarked) {
        isBookmarkedState = isBookmarked
    }

    val bookmarkTint = if (isBookmarkedState) MaterialTheme.colorSchemeCustom.alwaysBlue else MaterialTheme.colorScheme.onBackground

    Row {
        IconButton(
            onClick = {
                toggleBookmark()
                // Here, you might want to update isBookmarkedState directly if 'toggleBookmark' doesn't do this
                isBookmarkedState = !isBookmarkedState // If 'toggleBookmark' doesn't update the state, do it here
            }
        ) {
            Icon(
                imageVector = Icons.Filled.Bookmark,
                contentDescription = "Save",
                tint = bookmarkTint, // Apply the tint
            )
        }
        IconButton(onClick = deleteConversationFromHistory) {
            Icon(imageVector = Icons.Filled.Delete, contentDescription = "Delete")
        }
    }
}