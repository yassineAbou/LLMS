package org.yassineabou.llms.app.core.util

import org.yassineabou.llms.Chat_messages
import org.yassineabou.llms.Chats
import org.yassineabou.llms.Generated_images
import org.yassineabou.llms.api.ChatDto
import org.yassineabou.llms.api.ChatMessageDto
import org.yassineabou.llms.api.GeneratedImageDto

// DtoMappers.kt

/**
 * Utility object for mapping DTOs from remote services
 * to local SQLDelight database entities.
 */
object DtoMappers {

    fun ChatDto.toLocalChat(): Chats {
        return Chats(
            id = this.id,
            title = this.title,
            description = this.description,
            text_model_name = this.textModelName,
            is_bookmarked = if (this.isBookmarked) 1L else 0L,
            created_at = this.createdAt
        )
    }

    fun ChatMessageDto.toLocalChatMessage(): Chat_messages {
        return Chat_messages(
            id = this.id.toLong(),
            chat_id = this.chatId,
            message = this.message,
            is_user = if (this.isUser) 1L else 0L,
            timestamp = this.timestamp
        )
    }

    fun GeneratedImageDto.toLocalImage(): Generated_images {
        return Generated_images(
            id = this.id,
            prompt = this.prompt,
            image_data = this.imageData,
            image_model_name = this.imageModelName,
            generated_at = this.generatedAt
        )
    }
}