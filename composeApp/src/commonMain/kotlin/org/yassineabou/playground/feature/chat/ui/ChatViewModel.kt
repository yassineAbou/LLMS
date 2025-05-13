package org.yassineabou.playground.feature.chat.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.yassineabou.playground.app.core.data.ChutesAiEndPoint.API_KEY
import org.yassineabou.playground.app.core.data.ChutesAiRepository
import org.yassineabou.playground.app.core.data.GenerationState
import org.yassineabou.playground.feature.chat.data.model.ChatHistory
import org.yassineabou.playground.feature.chat.data.model.ChatMessageModel
import org.yassineabou.playground.feature.chat.data.model.TextGenModelList
import org.yassineabou.playground.feature.chat.data.model.TextModel
import kotlin.coroutines.cancellation.CancellationException

class ChatViewModel(
    private val chutesAiRepository: ChutesAiRepository
) : ViewModel() {

    // ========================================================================================
    //                                  State Properties
    // ========================================================================================

    // region Text Model Selection State
    // ========================================================================================
    private val _tempSelectedTextModel = MutableStateFlow<TextModel>(TextGenModelList.qwen.first())
    private val _selectedTextModel = MutableStateFlow<TextModel>(TextGenModelList.qwen.first())

    val tempSelectedTextModel: StateFlow<TextModel> = _tempSelectedTextModel
    val selectedTextModel: StateFlow<TextModel> = _selectedTextModel
    // endregion

    // region Chat Message State
    // ========================================================================================
    private val _currentChatMessages = mutableStateListOf<ChatMessageModel>()
    val currentChatMessages: SnapshotStateList<ChatMessageModel> = _currentChatMessages
    // endregion

    // region Chat History State
    // ========================================================================================
    private val _chatHistoryList = mutableStateListOf<ChatHistory>()
    private val _savedChatHistoryList = mutableStateListOf<ChatHistory>()
    private val _currentChatId = MutableStateFlow<String?>(null)
    private val _selectedChatHistory = MutableStateFlow<ChatHistory?>(null)

    val chatHistoryList: SnapshotStateList<ChatHistory> = _chatHistoryList
    val savedChatHistoryList: SnapshotStateList<ChatHistory> = _savedChatHistoryList
    val currentChatId: StateFlow<String?> get() = _currentChatId
    val selectedChatHistory: MutableStateFlow<ChatHistory?> get() = _selectedChatHistory
    // endregion

    // region Response Generation State
    // ========================================================================================
    private val _generationState = MutableStateFlow<GenerationState>(GenerationState.Success)
    val generationState: MutableStateFlow<GenerationState> = _generationState
    // endregion

    // ========================================================================================
    //                                  Model Management
    // ========================================================================================

    // region Model Selection Methods
    // ========================================================================================
    fun selectTempTextModel(textGenModel: TextModel) {
        _tempSelectedTextModel.value = textGenModel
    }

    fun confirmSelectedTextModel() {
        _selectedTextModel.value = _tempSelectedTextModel.value
    }

    fun setTempSelectedToSelected() {
        _tempSelectedTextModel.value = _selectedTextModel.value
    }

    fun handleModelSelectionChange() {
        val modelChanged = tempSelectedTextModel.value != selectedTextModel.value
        if (modelChanged) handleModelChangeWorkflow() else confirmSelectedTextModel()
    }

    private fun handleModelChangeWorkflow() {
        if (currentChatMessages.isNotEmpty()) finalizeCurrentChat()
        confirmSelectedTextModel()
        if (currentChatMessages.isNotEmpty()) startNewChat(forceNew = true) else resetCurrentChat()
    }
    // endregion

    // ========================================================================================
    //                                  Message Handling
    // ========================================================================================

    // region Public Message Methods
    // ========================================================================================
    fun sendMessage(message: String, isUser: Boolean = true) {
        if (_generationState.value is GenerationState.Loading) stopGeneration()
        _currentChatMessages.add(ChatMessageModel(message = message, isUser = isUser))
        if (isUser) initiateResponseGeneration()
    }

    fun stopGeneration() {
        val loadingState = generationState.value as GenerationState.Loading
        val aiMessageIndex = loadingState.id

        _generationState.value = GenerationState.Failure()
        _currentChatMessages[aiMessageIndex] = ChatMessageModel(
            message = (generationState.value as GenerationState.Failure).message,
            isUser = false,
        )
        _generationState.value = GenerationState.Success
    }

    fun regenerateResponse(index: Int) {
        val userMessage = _currentChatMessages[index - 1].message
        val currentMessage = _currentChatMessages[index]
        _currentChatMessages[index] = currentMessage.copy(message = "")
        performResponseGeneration(
            messageIndex = index,
            prompt = userMessage,
            initialMessage = ""
        )
    }
    // endregion

    // region Response Generation Implementation
    // ========================================================================================
    private fun initiateResponseGeneration() {
        val userMessage = _currentChatMessages.lastOrNull { it.isUser }?.message ?: ""
        _currentChatMessages.add(ChatMessageModel(message = "", isUser = false))
        performResponseGeneration(
            messageIndex = _currentChatMessages.lastIndex,
            prompt = userMessage,
            initialMessage = ""
        )
    }

    private fun performResponseGeneration(
        messageIndex: Int,
        prompt: String,
        initialMessage: String
    ) {
        viewModelScope.launch {
            val chutesName = _selectedTextModel.value.chutesName
            val currentMessage = _currentChatMessages[messageIndex]

            _currentChatMessages[messageIndex] = currentMessage.copy(message = initialMessage)
            _generationState.value = GenerationState.Loading(messageIndex)

            try {
                chutesAiRepository.streamChat(
                    apiKey = API_KEY,
                    prompt = prompt,
                    model = chutesName
                ).collect { chunk ->
                    if (generationState.value is GenerationState.Loading) {
                        _currentChatMessages[messageIndex] = _currentChatMessages[messageIndex].copy(
                            message = _currentChatMessages[messageIndex].message + chunk
                        )
                    }
                }
                _generationState.value = GenerationState.Success
            } catch (e: Exception) {
                handleGenerationError(e, messageIndex)
            } finally {
                _generationState.value = GenerationState.Success
            }
        }
    }

    private fun handleGenerationError(e: Exception, messageIndex: Int) {
        if (e !is CancellationException) {
            val errorMessage = e.message ?: "Generation failed"
            _generationState.value = GenerationState.Failure(errorMessage)
            _currentChatMessages[messageIndex] = ChatMessageModel(
                message = "⚠️ $errorMessage",
                isUser = false,
            )
        }
    }
    // endregion

    // ========================================================================================
    //                                  Chat History Management
    // ========================================================================================

    // region Public Chat Methods
    // ========================================================================================
    private fun finalizeCurrentChat() {
        if (currentChatMessages.isNotEmpty()) startNewChat(forceNew = false)
    }

    fun startNewChat(forceNew: Boolean = false) {
        if (currentChatMessages.isEmpty()) {
            resetCurrentChat()
            return
        }

        val description = generateChatDescription()
        when {
            forceNew || _currentChatId.value == null -> createNewChatHistory(description)
            else -> findExistingChat()?.let { updateExistingChat(it, description) }
        }
        resetCurrentChat()
    }

    fun resetCurrentChat() {
        _currentChatMessages.clear()
        _currentChatId.value = null
        _selectedChatHistory.value = null
    }

    fun deleteChatHistory(chatHistory: ChatHistory) {
        _chatHistoryList.remove(chatHistory)
        _selectedChatHistory.value = _chatHistoryList.firstOrNull() ?: run {
            resetCurrentChat()
            null
        }
    }

    fun clearChatHistory() {
        _chatHistoryList.clear()
        _savedChatHistoryList.clear()
        _selectedChatHistory.value = null
        resetCurrentChat()
    }

    fun toggleBookmark(chatHistory: ChatHistory) {
        chatHistory.isBookmarked = !chatHistory.isBookmarked
        if (chatHistory.isBookmarked) moveToSaved(chatHistory) else moveToRecent(chatHistory)
    }

    fun selectChatHistory(chatHistory: ChatHistory) {
        _selectedChatHistory.value = chatHistory
        loadChatMessages(chatHistory)
        _selectedTextModel.value = chatHistory.textModel
        _tempSelectedTextModel.value = chatHistory.textModel
    }
    // endregion

    // region History Implementation
    // ========================================================================================
    private fun findExistingChat() = _currentChatId.value?.let { id ->
        _chatHistoryList.find { it.id == id }
    }

    private fun generateChatDescription(): String {
        val fullDescription = _currentChatMessages.joinToString("\n") { it.message }
        return if (fullDescription.length > 150) "${fullDescription.take(150)}..." else fullDescription
    }

    private fun createNewChatHistory(description: String) {
        val newChat = ChatHistory(
            title = "Chat ${_chatHistoryList.size + 1}",
            description = description,
            textModel = _selectedTextModel.value,
            chatMessages = _currentChatMessages.toList()
        )
        _chatHistoryList.add(0, newChat)
        _currentChatId.value = newChat.id
    }

    private fun updateExistingChat(existingChat: ChatHistory, description: String) {
        val updatedChat = existingChat.copy(
            description = description,
            chatMessages = _currentChatMessages.toList()
        )
        _chatHistoryList.remove(selectedChatHistory.value)
        _chatHistoryList.add(0, updatedChat)
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
    // endregion
}