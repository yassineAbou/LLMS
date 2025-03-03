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

    // Text Model Selection
    private val _tempSelectedTextModel = MutableStateFlow<TextModel>(TextGenModelList.deepSeek.first())
    val tempSelectedTextModel: StateFlow<TextModel> = _tempSelectedTextModel

    private val _selectedTextModel = MutableStateFlow<TextModel>(TextGenModelList.deepSeek.first())
    val selectedTextModel: StateFlow<TextModel> = _selectedTextModel

    // Chat Messages
    private val _currentChatMessages = mutableStateListOf<ChatMessage>()
    val currentChatMessages: SnapshotStateList<ChatMessage> = _currentChatMessages

    // Chat History Management
    private val _chatHistoryList = mutableStateListOf<ChatHistory>()
    val chatHistoryList: SnapshotStateList<ChatHistory> = _chatHistoryList

    private val _savedChatHistoryList = mutableStateListOf<ChatHistory>()
    val savedChatHistoryList: SnapshotStateList<ChatHistory> = _savedChatHistoryList

    private val _currentChatId = MutableStateFlow<String?>(null)
    val currentChatId: StateFlow<String?> get() = _currentChatId

    // Generation State
    private var fullResponse: String = ""
    val isGenerating = mutableStateOf(false)

    // AI Provider Selection
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

    //region Text Model Selection
    fun selectTempTextModel(textGenModel: TextModel) {
        _tempSelectedTextModel.value = textGenModel
    }

    fun confirmSelectedTextModel() {
        _selectedTextModel.value = _tempSelectedTextModel.value
    }

    fun setTempSelectedToSelected() {
        _tempSelectedTextModel.value = _selectedTextModel.value
    }
    //endregion

    //region Message Handling
    fun sendMessage(message: String, isUser: Boolean = true) {
        if (isGenerating.value) stopGeneration()

        _currentChatMessages.add(ChatMessage(message, isUser))
        if (isUser) initiateResponseGeneration()
    }

    private fun initiateResponseGeneration() {
        fullResponse = generateLongResponse()

        // First add the empty AI message
        _currentChatMessages.add(ChatMessage("", false))
        // Then get its index
        val aiMessageIndex = _currentChatMessages.lastIndex

        isGenerating.value = true

        viewModelScope.launch {
            try {
                generateResponseIncrementally(aiMessageIndex)
            } finally {
                resetGenerationState()
            }
        }
    }

    private suspend fun generateResponseIncrementally(aiMessageIndex: Int) {
        for (i in fullResponse.indices) {
            if (!isGenerating.value) break
            updateAiMessage(aiMessageIndex, fullResponse.take(i + 1))
            delay(5)
        }
    }

    private fun updateAiMessage(index: Int, text: String) {
        _currentChatMessages[index] = ChatMessage(text, false)
    }

    private fun resetGenerationState() {
        isGenerating.value = false
    }

    fun stopGeneration() {
        isGenerating.value = false
    }
    //endregion

    //region Chat Management
    fun startNewChat() {
        if (_currentChatMessages.isEmpty()) return

        val existingChat = findExistingChat()
        val description = generateChatDescription()

        if (existingChat != null) {
            updateExistingChat(existingChat, description)
        } else {
            createNewChatHistory(description)
        }

        resetCurrentChat()
    }

    private fun findExistingChat() = _currentChatId.value?.let { id ->
        _chatHistoryList.find { it.id == id }
    }

    private fun generateChatDescription(): String {
        val fullDescription = _currentChatMessages.joinToString("\n") { it.message }
        return if (fullDescription.length > 150) {
            fullDescription.take(150) + "..."
        } else fullDescription
    }

    @OptIn(ExperimentalUuidApi::class)
    private fun createNewChatHistory(description: String) {
        val aiProvider = _selectedTextModel.value.provider
        val aiProviderIcon = aiProvidersMap[aiProvider] ?: Res.drawable.ic_github

        val newChat = ChatHistory(
            title = "Chat ${_chatHistoryList.size + 1}",
            description = description,
            aiProvider = AIProvider(aiProvider, aiProviderIcon),
            id = Uuid.toString(),
            chatMessages = _currentChatMessages.toList()
        )

        _chatHistoryList.add(0, newChat)
        _currentChatId.value = newChat.id
    }

    private fun updateExistingChat(existingChat: ChatHistory, description: String) {
        existingChat.description = description
        existingChat.chatMessages = _currentChatMessages.toList()
    }

    private fun resetCurrentChat() {
        _currentChatMessages.clear()
        _currentChatId.value = null
        _selectedChatHistory.value = null
    }

    fun clearChatHistory() {
        _chatHistoryList.clear()
        _savedChatHistoryList.clear()
    }

    fun deleteChatHistory(conversation: ChatHistory) {
        _chatHistoryList.remove(conversation)
    }

    fun toggleBookmark(chatHistory: ChatHistory) {
        chatHistory.isBookmarked = !chatHistory.isBookmarked

        if (chatHistory.isBookmarked) {
            moveToSaved(chatHistory)
        } else {
            moveToRecent(chatHistory)
        }
    }

    private fun moveToSaved(chatHistory: ChatHistory) {
        _chatHistoryList.remove(chatHistory)
        _savedChatHistoryList.add(0, chatHistory)
    }

    private fun moveToRecent(chatHistory: ChatHistory) {
        _savedChatHistoryList.remove(chatHistory)
        _chatHistoryList.add(0, chatHistory)
    }

    private fun loadChatMessages(chatHistory: ChatHistory) {
        _currentChatMessages.clear()
        _currentChatMessages.addAll(chatHistory.chatMessages)
        _currentChatId.value = chatHistory.id
    }

    fun selectChatHistory(chatHistory: ChatHistory) {
        _selectedChatHistory.value = chatHistory
        loadChatMessages(chatHistory)
    }
    //endregion

    //region AI Provider Management
    fun toggleAIProvider(providerName: String) {
        _selectedAIProviders.value = _selectedAIProviders.value.toMutableMap().apply {
            this[providerName] = !this[providerName]!!
        }
    }
    //endregion

}