package org.yassineabou.llms.feature.chat.data.model

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

data class ChatMessageModel(
    val message: String,
    val isUser: Boolean,
)

data class ChatHistory @OptIn(ExperimentalUuidApi::class) constructor(
    val title: String,
    var description: String,
    val textModel: TextModel,
    var isBookmarked: Boolean = false,
    var id: String = Uuid.toString(),
    var chatMessages: List<ChatMessageModel> = emptyList()
)

