package org.yassineabou.playground.feature.chat.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import llms.composeapp.generated.resources.Res
import llms.composeapp.generated.resources.ic_github
import org.yassineabou.playground.feature.chat.model.AIProvider
import org.yassineabou.playground.feature.chat.model.ChatHistory
import org.yassineabou.playground.feature.chat.model.ChatMessage
import org.yassineabou.playground.feature.chat.model.TextModel
import org.yassineabou.playground.feature.chat.model.aiProvidersMap
import org.yassineabou.playground.feature.chat.model.generateLongResponse
import org.yassineabou.playground.feature.chat.model.generatedChatHistoryList
import org.yassineabou.playground.feature.chat.model.textGenModelList
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

class ChatViewModel : ViewModel() {

    private val _tempSelectedTextModel = MutableStateFlow<TextModel>(textGenModelList.first())
    val tempSelectedTextModel: StateFlow<TextModel> = _tempSelectedTextModel

    private val _selectedTextModel = MutableStateFlow<TextModel>(textGenModelList.first())
    val selectedTextModel: StateFlow<TextModel> = _selectedTextModel

    private val _currentChatMessages = mutableStateListOf<ChatMessage>()
    val currentChatMessages: SnapshotStateList<ChatMessage> = _currentChatMessages

    private val _chatHistoryList = mutableStateListOf<ChatHistory>().apply {
        addAll(generatedChatHistoryList) // Assuming this is where default values come from
    }
    val chatHistoryList: SnapshotStateList<ChatHistory> = _chatHistoryList

    private val _savedChatHistoryList = mutableStateListOf<ChatHistory>()
    val savedChatHistoryList: SnapshotStateList<ChatHistory> = _savedChatHistoryList

    private val _currentChatId = MutableStateFlow<String?>(null)
    val currentChatId: StateFlow<String?> get() = _currentChatId

    private val _selectedAIProviders = MutableStateFlow<Map<String, Boolean>>(
        mapOf(
            "Meta" to true,
            "Google" to true,
            "AnthraciteOrg" to true,
            "AI21 Lab" to true,
            "Hugging Face H4" to true,
            "Alibaba Cloud" to true,
            "Deep Seek" to true,
            "TII" to true,
            "CohereForAI" to true
        )
    )
    val selectedAIProviders: StateFlow<Map<String, Boolean>> = _selectedAIProviders

    fun selectTempTextModel(textGenModel: TextModel) {
        _tempSelectedTextModel.value = textGenModel
    }

    fun confirmSelectedTextModel() {
        _selectedTextModel.value = _tempSelectedTextModel.value
        _tempSelectedTextModel.value = textGenModelList.first()
    }

    fun setTempSelectedToSelected() {
        _tempSelectedTextModel.value = _selectedTextModel.value
    }

    fun sendMessage(message: String, isUser: Boolean = true) {
        _currentChatMessages.add(ChatMessage(message, isUser))
        if (isUser) {
            // Simulate AI response
            viewModelScope.launch {
                val aiResponse = generateLongResponse() // Generate medium-length response
                _currentChatMessages.add(ChatMessage(aiResponse, false))
            }
        }
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
                // Add a new chat history item
                val newChat = ChatHistory(
                    title = "Chat ${_chatHistoryList.size + 1}",
                    description = description,
                    aiProvider = AIProvider(aiProvider, aiProviderIcon),
                    id = Uuid.toString(),
                    chatMessages = _currentChatMessages.toList()
                )
                _chatHistoryList.add(newChat)
                _currentChatId.value = newChat.id // Track the new chat ID
            }
        }

        // Clear the current chat messages and reset the chat ID
        _currentChatMessages.clear()
        _currentChatId.value = null
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
        // If you want to save this state elsewhere, like in a database or sharedprefs, do it here
        if (conversation.isBookmarked) {
            _savedChatHistoryList.add(conversation)
        } else {
            _savedChatHistoryList.remove(conversation) // If unbookmarked, remove from saved list
        }
    }

    fun toggleAIProvider(providerName: String) {
        _selectedAIProviders.value = _selectedAIProviders.value.toMutableMap().apply {
            this[providerName] = !(this[providerName] ?: false)
        }
    }

    fun loadChatMessages(chatHistory: ChatHistory) {
        _currentChatMessages.clear()
        _currentChatMessages.addAll(chatHistory.chatMessages)
        _currentChatId.value = chatHistory.id // Track the loaded chat ID
    }
}