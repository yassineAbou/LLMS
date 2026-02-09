package org.yassineabou.llms.feature.chat.ui.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.unit.dp
import org.yassineabou.llms.Chats
import org.yassineabou.llms.app.core.theme.colorSchemeCustom
import org.yassineabou.llms.feature.chat.data.model.TextModel


@Composable
fun ChatHistoryListView(
    chats: List<Chats>,
    deleteChats: (Chats) -> Unit,
    toggleBookmark: (Chats) -> Unit,
    onClick: (Chats) -> Unit,
    availableModels: List<TextModel> = emptyList()
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(
            chats,
        ) { conversationItem ->
            ChatHistoryCard(
                chats = conversationItem,
                availableModels = availableModels,
                deleteChats = { deleteChats(it) },
                toggleBookmark = { toggleBookmark(it) },
                onClick = { onClick(it) }
            )
        }
    }
}

@Composable
fun ChatHistoryCard(
    chats: Chats,
    availableModels: List<TextModel>,
    deleteChats: (Chats) -> Unit,
    toggleBookmark: (Chats) -> Unit,
    onClick: (Chats) -> Unit
) {
    val textModel by remember(chats.text_model_name, availableModels) {
        derivedStateOf {
            availableModels.find { it.modelName == chats.text_model_name }
                ?: TextModel.DEFAULT
        }
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(chats) },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            ChatHistoryDetails(
                title = chats.title,
                description = chats.description ?: ""
            )
            ChatHistoryAction(
                textModel = textModel,
                isBookmarked = chats.is_bookmarked == 1L,
                deleteConversationFromHistory = { deleteChats(chats) },
                toggleBookmark = { toggleBookmark(chats) },
            )
        }
    }
}

@Composable
fun ChatHistoryDetails(
    title: String,
    description: String
) {
    Column {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = description,
            style = MaterialTheme.typography.bodyMedium,
        )
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Composable
fun ChatHistoryAction(
    textModel: TextModel,
    isBookmarked: Boolean,
    toggleBookmark: () -> Unit,
    deleteConversationFromHistory: () -> Unit,

    ) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AiProviderInfo(textModel = textModel)
        ActionButtons(
            isBookmarked = isBookmarked,
            toggleBookmark = toggleBookmark,
            deleteConversationFromHistory = deleteConversationFromHistory,
        )
    }
}

@Composable
fun AiProviderInfo(textModel: TextModel) {
    Row(
        modifier = Modifier
            .border(
                BorderStroke(1.dp, LightGray),
                shape = RoundedCornerShape(8.dp)
            )
            .padding(6.dp),
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = textModel.title,
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
    val bookmarkTint = if (isBookmarked) MaterialTheme.colorSchemeCustom.alwaysBlue else MaterialTheme.colorScheme.onBackground
    Row {
        IconButton(
            onClick = { toggleBookmark() }
        ) {
            Icon(
                imageVector = Icons.Filled.Bookmark,
                contentDescription = "Save",
                tint = bookmarkTint,
            )
        }
        IconButton(onClick = deleteConversationFromHistory) {
            Icon(imageVector = Icons.Filled.Delete, contentDescription = "Delete")
        }
    }
}