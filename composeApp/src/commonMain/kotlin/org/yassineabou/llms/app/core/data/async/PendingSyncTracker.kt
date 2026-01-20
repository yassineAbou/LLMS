package org.yassineabou.llms.app.core.data.async

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

/**
 * Tracks items that failed to sync and are pending retry.
 * Thread-safe implementation using StateFlow for atomic updates.
 */
class PendingSyncTracker {

    private val _chatIds = MutableStateFlow<Set<String>>(emptySet())
    private val _imageIds = MutableStateFlow<Set<String>>(emptySet())

    val chatIds: Set<String> get() = _chatIds.value
    val imageIds: Set<String> get() = _imageIds.value

    fun markChatPending(id: String) {
        _chatIds.update { it + id }
    }

    fun markChatSynced(id: String) {
        _chatIds.update { it - id }
    }

    fun markImagePending(id: String) {
        _imageIds.update { it + id }
    }

    fun markImageSynced(id: String) {
        _imageIds.update { it - id }
    }

    fun clear() {
        _chatIds.value = emptySet()
        _imageIds.value = emptySet()
    }
}