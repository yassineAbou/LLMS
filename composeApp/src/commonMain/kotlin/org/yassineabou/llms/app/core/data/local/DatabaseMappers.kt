package org.yassineabou.llms.app.core.data.local

import org.yassineabou.llms.db.ChatMessagesSelectMessagesByChatIdResult
import org.yassineabou.llms.db.ChatsSelectAllChatsResult
import org.yassineabou.llms.db.GeneratedImagesSelectAllImagesResult
import org.yassineabou.llms.db.UserGetUserResult

/*
* Mapping layer: SQLiteNow result types → old SQLDelight data classes.
*
* This exists so the Compose UI, ViewModels, and AsyncManager can keep
* using the original SQLDelight-generated data classes unchanged.
*
* THREE categories of difference are handled:
*
* 1. NULLABILITY — SQLiteNow marks primary-key columns as nullable
*    (e.g. id: String?), SQLDelight had them non-null.
*    → We default with ?: "" or ?: 0L / ?: 1L.
*
* 2. NAMING — SQLiteNow uses camelCase (textModelName, createdAt),
*    SQLDelight used snake_case (text_model_name, created_at).
*    → Manual field mapping.
*
* 3. TYPES — SQLiteNow maps INTEGER boolean columns to Kotlin Boolean,
*    SQLDelight kept them as Long.
*    → Convert Boolean → Long (true=1L, false=0L).
*/

// ── Chats ────────────────────────────────────────────────────────────

fun ChatsSelectAllChatsResult.toChats(): Chats = Chats(
    id = this.id ?: "",
    title = this.title,
    description = this.description,
    text_model_name = this.textModelName,
    is_bookmarked = if (this.isBookmarked) 1L else 0L,
    created_at = this.createdAt
)

fun List<ChatsSelectAllChatsResult>.toChats(): List<Chats> = map { it.toChats() }

// ── Chat Messages ────────────────────────────────────────────────────

fun ChatMessagesSelectMessagesByChatIdResult.toChatMessage(): Chat_messages = Chat_messages(
    id = this.id ?: 0L,
    chat_id = this.chatId,
    message = this.message,
    is_user = if (this.isUser) 1L else 0L,
    timestamp = this.timestamp
)

fun List<ChatMessagesSelectMessagesByChatIdResult>.toChatMessages(): List<Chat_messages> =
    map { it.toChatMessage() }

// ── Generated Images ─────────────────────────────────────────────────

fun GeneratedImagesSelectAllImagesResult.toGeneratedImage(): Generated_images = Generated_images(
    id = this.id ?: "",
    prompt = this.prompt,
    image_data = this.imageData,
    image_model_name = this.imageModelName,
    generated_at = this.generatedAt
)

fun List<GeneratedImagesSelectAllImagesResult>.toGeneratedImages(): List<Generated_images> =
    map { it.toGeneratedImage() }

// ── User ─────────────────────────────────────────────────────────────

fun UserGetUserResult.toUser(): User = User(
    id = this.id ?: 1L,
    google_sub_id = this.googleSubId,
    email = this.email,
    username = this.username,
    profile_pic_url = this.profilePicUrl,
    created_at = this.createdAt
)