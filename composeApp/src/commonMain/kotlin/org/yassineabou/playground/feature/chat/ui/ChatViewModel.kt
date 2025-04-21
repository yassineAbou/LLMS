package org.yassineabou.playground.feature.chat.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.text.AnnotatedString
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.yassineabou.playground.feature.chat.data.model.ChatHistory
import org.yassineabou.playground.feature.chat.data.model.ChatMessageModel
import org.yassineabou.playground.feature.chat.data.model.TextGenModelList
import org.yassineabou.playground.feature.chat.data.model.TextModel
import org.yassineabou.playground.feature.chat.data.network.ChutesAiRepository
import org.yassineabou.playground.feature.chat.data.network.GitHubMarkdownRepository
import org.yassineabou.playground.feature.chat.data.network.TextGenerationState
import kotlin.coroutines.cancellation.CancellationException

class ChatViewModel(
    private val chutesAiRepository: ChutesAiRepository,
    private val gitHubMarkdownRepository: GitHubMarkdownRepository
) : ViewModel() {

    // region State Properties
    // ========================================================================================
    //                          Text Model Selection State
    // ========================================================================================

    /** Holds the temporarily selected model before confirmation */
    private val _tempSelectedTextModel = MutableStateFlow<TextModel>(TextGenModelList.deepseek.first())
    val tempSelectedTextModel: StateFlow<TextModel> = _tempSelectedTextModel

    /** Currently active text model used for generation */
    private val _selectedTextModel = MutableStateFlow<TextModel>(TextGenModelList.deepseek.first())
    val selectedTextModel: StateFlow<TextModel> = _selectedTextModel

    // ========================================================================================
    //                              Chat Message State
    // ========================================================================================

    /** Current active chat conversation messages */
    private val _currentChatMessages = mutableStateListOf<ChatMessageModel>()
    val currentChatMessages: SnapshotStateList<ChatMessageModel> = _currentChatMessages


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

    /** API Key management - Replace with secure storage implementation */
    private var apiKey: String = ""

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
        if (_textGenerationState.value is TextGenerationState.Loading) stopGeneration()
        _currentChatMessages.add(
            ChatMessageModel(
                rawMessage = message,
                isUser = isUser,
                renderState = if (isUser) ChatMessageModel.RenderState.STABLE else ChatMessageModel.RenderState.PENDING
            )
        )
        if (isUser) initiateResponseGeneration()
    }

    /**
     * Stops ongoing response generation
     */
    fun stopGeneration() {
         val loadingState = textGenerationState.value as TextGenerationState.Loading
         val aiMessageIndex = loadingState.id

         // Update state and message
         _textGenerationState.value = TextGenerationState.Failure()
         _currentChatMessages[aiMessageIndex] = ChatMessageModel(
             rawMessage = (textGenerationState.value as TextGenerationState.Failure).message,
             isUser = false,
             renderState = ChatMessageModel.RenderState.ERROR
         )
         _textGenerationState.value = TextGenerationState.Success("")
    }

    fun regenerateResponse(index: Int) {
        val userMessage = _currentChatMessages[index - 1].rawMessage
        val currentMessage = _currentChatMessages[index]
        _currentChatMessages[index] = currentMessage.copy(rawMessage = "")
        performResponseGeneration(
            messageIndex = index,
            prompt = userMessage,
            initialMessage = ""
        )
    }

    // ========================================================================================
    //                          Private Generation Methods
    // ========================================================================================

    private fun initiateResponseGeneration() {
        val userMessage = _currentChatMessages.lastOrNull { it.isUser }?.rawMessage ?: ""
        _currentChatMessages.add(
            ChatMessageModel(
                rawMessage = "",
                isUser = false,
                renderState = ChatMessageModel.RenderState.STREAMING
            )
        )
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

        // Initialize message state
        _currentChatMessages[messageIndex] = currentMessage.copy(rawMessage = initialMessage)

            _textGenerationState.value = TextGenerationState.Loading(messageIndex)

            try {
                chutesAiRepository.streamChat(
                    apiKey = apiKey,
                    prompt = prompt,
                    model = chutesName
                ).collect { chunk ->
                    if (textGenerationState.value is TextGenerationState.Loading) {
                        updateStreamingMessage(messageIndex, chunk)
                    }
                }
                renderFinalMessage(messageIndex)
            } catch (e: Exception) {
                handleGenerationError(e, messageIndex)
            } finally {
                _textGenerationState.value = TextGenerationState.Success("")
            }
        }
    }

    private fun handleGenerationError(e: Exception, messageIndex: Int) {
        if (e !is CancellationException) {
            val errorMessage = e.message ?: "Generation failed"
            _textGenerationState.value = TextGenerationState.Failure(errorMessage)
            _currentChatMessages[messageIndex] = ChatMessageModel(
                rawMessage = "⚠️ $errorMessage",
                isUser = false,
                renderState = ChatMessageModel.RenderState.ERROR,
            )
        }
    }

    // endregion

    // ========================================================================================
    //                          Markdown Rendering Methods
    // ========================================================================================

    private fun updateStreamingMessage(index: Int, chunk: String) {
        _currentChatMessages[index] = _currentChatMessages[index].copy(
            rawMessage = _currentChatMessages[index].rawMessage + chunk
        )
    }

    private suspend fun renderFinalMessage(index: Int) {
        try {
            val rawContent = _currentChatMessages[index].rawMessage
            val html = gitHubMarkdownRepository.renderMarkdown(rawContent)
            val annotatedText = gitHubMarkdownRepository.parseHtmlToAnnotatedString(html)

            _currentChatMessages[index] = _currentChatMessages[index].copy(
                renderedContent = annotatedText,
                renderState = ChatMessageModel.RenderState.STABLE
            )
        } catch (e: Exception) {
            _currentChatMessages[index] = _currentChatMessages[index].copy(
                renderState = ChatMessageModel.RenderState.ERROR,
                renderedContent = AnnotatedString("Error rendering message: ${e.message}")
            )
        }
        _textGenerationState.value = TextGenerationState.Success("")
    }

    // endregion
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
        val fullDescription = _currentChatMessages.joinToString("\n") { it.rawMessage }
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
