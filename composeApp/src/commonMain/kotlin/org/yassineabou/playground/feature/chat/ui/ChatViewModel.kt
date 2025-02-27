package org.yassineabou.playground.feature.chat.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import llms.composeapp.generated.resources.Res
import llms.composeapp.generated.resources.ic_github
import org.yassineabou.playground.feature.chat.model.AIProvider
import org.yassineabou.playground.feature.chat.model.ChatHistory
import org.yassineabou.playground.feature.chat.model.ChatMessage
import org.yassineabou.playground.feature.chat.model.TextGenModelList
import org.yassineabou.playground.feature.chat.model.TextModel
import org.yassineabou.playground.feature.chat.model.aiProvidersMap
import org.yassineabou.playground.feature.chat.model.generateLongResponse
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

class ChatViewModel : ViewModel() {

    private val _tempSelectedTextModel = MutableStateFlow<TextModel>(TextGenModelList.deepSeek.first())
    val tempSelectedTextModel: StateFlow<TextModel> = _tempSelectedTextModel

    private val _selectedTextModel = MutableStateFlow<TextModel>(TextGenModelList.deepSeek.first())
    val selectedTextModel: StateFlow<TextModel> = _selectedTextModel

    private val _currentChatMessages = mutableStateListOf<ChatMessage>()
    val currentChatMessages: SnapshotStateList<ChatMessage> = _currentChatMessages

    private val _chatHistoryList = mutableStateListOf<ChatHistory>()
    val chatHistoryList: SnapshotStateList<ChatHistory> = _chatHistoryList

    private val _savedChatHistoryList = mutableStateListOf<ChatHistory>()
    val savedChatHistoryList: SnapshotStateList<ChatHistory> = _savedChatHistoryList

    private val _currentChatId = MutableStateFlow<String?>(null)
    val currentChatId: StateFlow<String?> get() = _currentChatId

    private var fullResponse: String = ""
    private var isGeneratingInternal = false
    val isGenerating = mutableStateOf(false)


    private val _selectedAIProviders = MutableStateFlow<Map<String, Boolean>>(
        mapOf(
            "Deep Seek" to true,
            "Alibaba Cloud" to true,
            "Meta" to true,
        )
    )
    val selectedAIProviders: StateFlow<Map<String, Boolean>> = _selectedAIProviders

    private val _selectedChatHistory = MutableStateFlow<ChatHistory?>(null)
    val selectedChatHistory: StateFlow<ChatHistory?> get() = _selectedChatHistory

    fun selectTempTextModel(textGenModel: TextModel) {
        _tempSelectedTextModel.value = textGenModel
    }

    fun confirmSelectedTextModel() {
        _selectedTextModel.value = _tempSelectedTextModel.value
    }

    fun setTempSelectedToSelected() {
        _tempSelectedTextModel.value = _selectedTextModel.value
    }

    fun sendMessage(message: String, isUser: Boolean = true) {
        if (isGeneratingInternal) {
            stopGeneration() // Stop any ongoing generation before starting a new one
        }

        _currentChatMessages.add(ChatMessage(message, isUser))
        if (isUser) {
            // Generate a new full response
            fullResponse = generateLongResponse()
            _currentChatMessages.add(ChatMessage("", false))

            isGenerating.value = true
            isGeneratingInternal = true

            viewModelScope.launch {
                try {
                    val aiIndex = _currentChatMessages.lastIndex
                    for (i in fullResponse.indices) {
                        if (!isGeneratingInternal) break // Stop if generation is canceled
                        val currentText = fullResponse.take(i + 1)
                        _currentChatMessages[aiIndex] = ChatMessage(currentText, false)
                        delay(5)
                    }
                } finally {
                    isGenerating.value = false
                    isGeneratingInternal = false
                }
            }
        }
    }

    fun stopGeneration() {
        isGeneratingInternal = false // Signal to stop generation
        isGenerating.value = false
    }

    @OptIn(ExperimentalUuidApi::class)
    fun startNewChat() {
        if (_currentChatMessages.isNotEmpty()) {
            // Concatenate chat messages
            val fullDescription = _currentChatMessages.joinToString("\n") { it.message }

            // Limit the description to 150 characters and add "..." if truncated
            val description = if (fullDescription.length > 150) {
                fullDescription.take(150) + "..."
            } else {
                fullDescription
            }

            // Use the selected text model's AI provider
            val aiProvider = _selectedTextModel.value.provider
            val aiProviderIcon = aiProvidersMap[aiProvider] ?: Res.drawable.ic_github

            // Check if the current chat already exists in the history
            val existingChat = _currentChatId.value?.let { id ->
                _chatHistoryList.find { it.id == id }
            }

            if (existingChat != null) {
                // Update the existing chat history item
                existingChat.description = description
                existingChat.chatMessages = _currentChatMessages.toList()
            } else {
                // Add a new chat history item at the beginning of the list
                val newChat = ChatHistory(
                    title = "Chat ${_chatHistoryList.size + 1}",
                    description = description,
                    aiProvider = AIProvider(aiProvider, aiProviderIcon),
                    id = Uuid.toString(),
                    chatMessages = _currentChatMessages.toList()
                )
                _chatHistoryList.add(0, newChat) // Insert at the beginning
                _currentChatId.value = newChat.id // Track the new chat ID
            }
        }

        // Clear the current chat messages and reset the chat ID
        _currentChatMessages.clear()
        _currentChatId.value = null
        _selectedChatHistory.value = null // Clear the selected chat history
    }

    fun clearChatHistory() {
        _chatHistoryList.clear()
        _savedChatHistoryList.clear()
    }

    fun deleteChatHistory(conversation: ChatHistory) {
        _chatHistoryList.remove(conversation)
    }

    fun toggleBookmark(conversation: ChatHistory) {
        conversation.isBookmarked = !conversation.isBookmarked
        if (conversation.isBookmarked) {
            // Remove from recent list and add to saved list
            _chatHistoryList.remove(conversation)
            _savedChatHistoryList.add(0, conversation) // Insert at the beginning
        } else {
            // Remove from saved list and add back to recent list
            _savedChatHistoryList.remove(conversation)
            _chatHistoryList.add(0, conversation) // Insert at the beginning
        }
    }

    fun toggleAIProvider(providerName: String) {
        _selectedAIProviders.value = _selectedAIProviders.value.toMutableMap().apply {
            this[providerName] = !(this[providerName] ?: false)
        }
    }

    private fun loadChatMessages(chatHistory: ChatHistory) {
        _currentChatMessages.clear()
        _currentChatMessages.addAll(chatHistory.chatMessages)
        _currentChatId.value = chatHistory.id // Track the loaded chat ID
    }

    fun selectChatHistory(chatHistory: ChatHistory) {
        _selectedChatHistory.value = chatHistory
        loadChatMessages(chatHistory) // Load the messages for the selected chat
    }

}