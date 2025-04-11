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
import org.yassineabou.playground.feature.chat.data.model.ChatHistory
import org.yassineabou.playground.feature.chat.data.model.ChatMessage
import org.yassineabou.playground.feature.chat.data.model.TextGenModelList
import org.yassineabou.playground.feature.chat.data.model.TextModel
import org.yassineabou.playground.feature.chat.data.network.AIHordeRepository
import org.yassineabou.playground.feature.chat.data.network.TextGenerationState

class ChatViewModel(private val aiHordeRepository: AIHordeRepository) : ViewModel() {

    // region State Properties
    // ========================================================================================
    //                          Text Model Selection State
    // ========================================================================================

    /** Holds the temporarily selected model before confirmation */
    private val _tempSelectedTextModel = MutableStateFlow<TextModel>(TextGenModelList.deepSeek.first())
    val tempSelectedTextModel: StateFlow<TextModel> = _tempSelectedTextModel

    /** Currently active text model used for generation */
    private val _selectedTextModel = MutableStateFlow<TextModel>(TextGenModelList.deepSeek.first())
    val selectedTextModel: StateFlow<TextModel> = _selectedTextModel

    // ========================================================================================
    //                              Chat Message State
    // ========================================================================================

    /** Current active chat conversation messages */
    private val _currentChatMessages = mutableStateListOf<ChatMessage>()
    val currentChatMessages: SnapshotStateList<ChatMessage> = _currentChatMessages


    // ========================================================================================
    //                            Chat History State
    // ========================================================================================

    /** List of recent chat conversations */
    private val _chatHistoryList = mutableStateListOf<ChatHistory>()
    val chatHistoryList: SnapshotStateList<ChatHistory> = _chatHistoryList

    /** List of bookmarked/saved chats */
    private val _savedChatHistoryList = mutableStateListOf<ChatHistory>()
    val savedChatHistoryList: SnapshotStateList<ChatHistory> = _savedChatHistoryList

    /** ID of the currently active chat session */
    private val _currentChatId = MutableStateFlow<String?>(null)
    val currentChatId: StateFlow<String?> get() = _currentChatId

    /** Currently selected chat history item */
    private val _selectedChatHistory = MutableStateFlow<ChatHistory?>(null)
    val selectedChatHistory: MutableStateFlow<ChatHistory?> get() = _selectedChatHistory

    // ========================================================================================
    //                          Response Generation State
    // ========================================================================================

    /** Flag indicating if response generation is in progress */
    val isGenerating = mutableStateOf(false)

    /** API Key management - Replace with secure storage implementation */
    private var apiKey: String = "0000000000"

    private val _textGenerationState = MutableStateFlow<TextGenerationState>(TextGenerationState.Success(""))
    val textGenerationState: MutableStateFlow<TextGenerationState> = _textGenerationState
    // endregion

    // region Text Model Management
    // ========================================================================================
    //                          Public Model Selection Methods
    // ========================================================================================

    /**
     * Updates the temporary selected text model
     * @param textGenModel The new model to set as temporary selection
     */
    fun selectTempTextModel(textGenModel: TextModel) {
        _tempSelectedTextModel.value = textGenModel
    }

    /**
     * Confirms the temporary model as the active model
     */
    fun confirmSelectedTextModel() {
        _selectedTextModel.value = _tempSelectedTextModel.value
    }

    /**
     * Resets temporary selection to match current active model
     */
    fun setTempSelectedToSelected() {
        _tempSelectedTextModel.value = _selectedTextModel.value
    }

    /**
     * Handles model selection changes and manages chat transitions
     */
    fun handleModelSelectionChange() {
        val modelChanged = tempSelectedTextModel.value != selectedTextModel.value
        if (modelChanged) handleModelChangeWorkflow() else confirmSelectedTextModel()
    }

    /**
     * Manages workflow when changing models mid-conversation
     */
    private fun handleModelChangeWorkflow() {
        if (currentChatMessages.isNotEmpty()) finalizeCurrentChat()
        confirmSelectedTextModel()
        if (currentChatMessages.isNotEmpty()) startNewChat(forceNew = true) else resetCurrentChat()
    }
    // endregion

    // region Message Handling & Generation
    // ========================================================================================
    //                          Public Message Methods
    // ========================================================================================

    /**
     * Adds a new message to the chat and initiates AI response
     * @param message The text message content
     * @param isUser Flag indicating if the message is from the user
     */
    fun sendMessage(message: String, isUser: Boolean = true) {
        if (isGenerating.value) stopGeneration()
        _currentChatMessages.add(ChatMessage(message, isUser))
        if (isUser) initiateResponseGeneration()
    }

    /**
     * Stops ongoing response generation
     */
    fun stopGeneration() {
        if (isGenerating.value) {
            isGenerating.value = false
            if (textGenerationState.value is TextGenerationState.Loading) {
                _textGenerationState.value = TextGenerationState.Failure()
                val aiMessageIndex = _currentChatMessages.lastIndex
                _currentChatMessages[aiMessageIndex] = ChatMessage(
                    message = (textGenerationState.value as TextGenerationState.Failure).message,
                    isUser = false
                )
                _textGenerationState.value = TextGenerationState.Success("")
            }
        }
    }

    // ========================================================================================
    //                          Private Generation Methods
    // ========================================================================================

    private fun initiateResponseGeneration() {
        val userMessage = _currentChatMessages.lastOrNull { it.isUser }?.message ?: ""
        val modelTitle = _selectedTextModel.value.title

        _currentChatMessages.add(ChatMessage("", false))
        val aiMessageIndex = _currentChatMessages.lastIndex

        isGenerating.value = true  // Add this

        viewModelScope.launch {
            _textGenerationState.value = TextGenerationState.Loading

            try {
                aiHordeRepository.generateText(
                    apiKey = apiKey,
                    prompt = userMessage,
                    //modelTitle = modelTitle  // Uncomment this
                ).fold(
                    onSuccess = { generatedText ->
                        _textGenerationState.value = TextGenerationState.Success(generatedText)
                        simulateTypingEffect(generatedText, aiMessageIndex)
                    },
                    onFailure = { exception ->
                        _textGenerationState.value = TextGenerationState.Failure()
                        _currentChatMessages[aiMessageIndex] = ChatMessage(
                            message = (textGenerationState.value as TextGenerationState.Failure).message,
                            isUser = false
                        )
                    }
                )
            } finally {
                isGenerating.value = false  // Add this
                _textGenerationState.value = TextGenerationState.Success("")
            }
        }
    }

    private suspend fun simulateTypingEffect(response: String, aiMessageIndex: Int) {
        for (i in response.indices) {
            if (!isGenerating.value) break
            _currentChatMessages[aiMessageIndex] =
                ChatMessage(response.take(i + 1), false)
            delay(12) // Natural typing speed
        }
    }

    // endregion

    // region Chat History Management
    // ========================================================================================
    //                          Public Chat Methods
    // ========================================================================================

    /**
     * Finalizes current chat before model changes
     */
    fun finalizeCurrentChat() {
        if (currentChatMessages.isNotEmpty()) startNewChat(forceNew = false)
    }

    /**
     * Starts a new chat session
     * @param forceNew Creates new history even when continuing existing chat
     */
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

    /**
     * Clears current chat state
     */
    fun resetCurrentChat() {
        _currentChatMessages.clear()
        _currentChatId.value = null
        _selectedChatHistory.value = null
    }

    /**
     * Delete chat history item
     */
    fun deleteChatHistory(chatHistory: ChatHistory) {
        _chatHistoryList.remove(chatHistory)

        // Select first item if list not empty, else clear selection
        if (_chatHistoryList.isNotEmpty()) {
            selectChatHistory(_chatHistoryList.first())
        } else {
            _selectedChatHistory.value = null
            resetCurrentChat()
        }
    }

    /**
     * Deletes all chat history
     */
    fun clearChatHistory() {
        _chatHistoryList.clear()
        _savedChatHistoryList.clear()
        _selectedChatHistory.value = null
        resetCurrentChat()
    }

    /**
     * Toggles bookmark status for a chat
     * @param chatHistory The chat history item to modify
     */
    fun toggleBookmark(chatHistory: ChatHistory) {
        chatHistory.isBookmarked = !chatHistory.isBookmarked
        if (chatHistory.isBookmarked) moveToSaved(chatHistory) else moveToRecent(chatHistory)
    }

    /**
     * Loads a chat history into current session
     * @param chatHistory The history item to load
     */
    fun selectChatHistory(chatHistory: ChatHistory) {
        _selectedChatHistory.value = chatHistory
        loadChatMessages(chatHistory)
        _selectedTextModel.value = chatHistory.textModel
        _tempSelectedTextModel.value = chatHistory.textModel
    }

    // ========================================================================================
    //                          Private History Methods
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