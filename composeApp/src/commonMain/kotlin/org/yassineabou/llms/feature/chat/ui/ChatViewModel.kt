@file:OptIn(ExperimentalUuidApi::class)

package org.yassineabou.llms.feature.chat.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import org.yassineabou.llms.Chat_messages
import org.yassineabou.llms.Chats
import org.yassineabou.llms.app.core.data.local.LlmsDatabaseRepository
import org.yassineabou.llms.app.core.data.remote.ChutesAiEndPoint.API_KEY
import org.yassineabou.llms.app.core.data.remote.ChutesAiRepository
import org.yassineabou.llms.app.core.data.remote.GenerationState
import org.yassineabou.llms.feature.chat.data.model.TextGenModelList
import org.yassineabou.llms.feature.chat.data.model.TextModel
import kotlin.coroutines.cancellation.CancellationException
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

class ChatViewModel(
    private val chutesAiRepository: ChutesAiRepository,
    private val llmsDatabaseRepository: LlmsDatabaseRepository
) : ViewModel() {

    // ========================================================================================
    //                                  State Properties
    // ========================================================================================

    // region Text Model Selection State
    // ========================================================================================
    private val _tempSelectedTextModel = MutableStateFlow<TextModel>(TextGenModelList.defaultModel)
    private val _selectedTextModel = MutableStateFlow<TextModel>(TextGenModelList.defaultModel)

    val tempSelectedTextModel: StateFlow<TextModel> = _tempSelectedTextModel
    val selectedTextModel: StateFlow<TextModel> = _selectedTextModel
    // endregion

    // region Chat Message State
    // ========================================================================================
    private val _currentChatMessages = mutableStateListOf<Chat_messages>()
    val currentChatMessages: SnapshotStateList<Chat_messages> = _currentChatMessages
    // endregion

    // region Chat History State
    // ========================================================================================
    private val _selectedChats = MutableStateFlow<Chats?>(null)

    private val _allChats = MutableStateFlow<List<Chats>>(emptyList())

    val allChats: StateFlow<List<Chats>> = _allChats

    val savedChats: StateFlow<List<Chats>> = _allChats.map { chats ->
        chats.filter { it.is_bookmarked == 1L }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val recentChats: StateFlow<List<Chats>> = _allChats.map { chats ->
        chats.filter { it.is_bookmarked == 0L }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val selectedChats: MutableStateFlow<Chats?> get() = _selectedChats
    // endregion

    // region Response Generation State
    // ========================================================================================
    private val _generationState = MutableStateFlow<GenerationState>(GenerationState.Success)
    val generationState: MutableStateFlow<GenerationState> = _generationState
    // endregion

    init {
        // Start collecting chats from repository
        viewModelScope.launch {
            llmsDatabaseRepository.getAllChats().collect { chats ->
                _allChats.update { chats }
            }
        }
    }

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
        _currentChatMessages.add(
            Chat_messages(
                id = 0L,
                chat_id = "",
                message = message,
                is_user = if (isUser) 1 else 0,
                timestamp = Clock.System.now().toString()
            )
        )
        if (isUser) initiateResponseGeneration()
    }

    fun stopGeneration() {
        val loadingState = generationState.value as GenerationState.Loading
        val aiMessageIndex = loadingState.id

        _generationState.value = GenerationState.Failure()
        _currentChatMessages[aiMessageIndex] = Chat_messages(
                id = 0L,
                chat_id = "",
                message = (generationState.value as GenerationState.Failure).message,
                is_user = 0,
                timestamp = Clock.System.now().toString()
            )
        _generationState.value = GenerationState.Success
    }

    fun regenerateResponse(index: Int) {
        val userMessage = _currentChatMessages[index - 1].message
        val currentMessage = _currentChatMessages[index]
        _currentChatMessages[index] = currentMessage.copy(message = "")
        performResponseGeneration(
            messageIndex = index,
            prompt = userMessage
        )
    }
    // endregion

    // region Response Generation Implementation
    // ========================================================================================
    private fun initiateResponseGeneration() {
        val userMessage = _currentChatMessages.lastOrNull { it.is_user == 1L}?.message ?: ""
        _currentChatMessages.add(
            Chat_messages(
                id = 0L,
                chat_id = "",
                message = "",
                is_user = 0,
                timestamp = Clock.System.now().toString()
            )
        )
        performResponseGeneration(
            messageIndex = _currentChatMessages.lastIndex,
            prompt = userMessage
        )
    }

    private fun performResponseGeneration(
        messageIndex: Int,
        prompt: String,
    ) {
        viewModelScope.launch {
            val chutesName = _selectedTextModel.value.chutesName
            val currentMessage = _currentChatMessages[messageIndex]

            _currentChatMessages[messageIndex] = currentMessage.copy(message = "")
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
            _currentChatMessages[messageIndex] = Chat_messages(
                    id = 0L,
                    chat_id = "",
                    message = "⚠️ $errorMessage",
                    is_user = 0,
                    timestamp = Clock.System.now().toString()
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
        createChatHistory(description)
        resetCurrentChat()
    }

    fun resetCurrentChat() {
        _currentChatMessages.clear()
        _selectedChats.value = null
    }

    fun deleteChats(chats: Chats) {
        viewModelScope.launch {
            llmsDatabaseRepository.deleteChatById(chats.id)
            resetCurrentChat()
        }

    }

    fun clearChats() {
        viewModelScope.launch {
            llmsDatabaseRepository.clearAllChats()
            resetCurrentChat()
        }
    }

    fun toggleBookmark(chat: Chats) {
        viewModelScope.launch {
            val updatedChat = chat.copy(
                is_bookmarked = if (chat.is_bookmarked == 1L) 0L else 1L
            )
            llmsDatabaseRepository.insertChat(updatedChat)
        }
    }

    fun selectChats(chats: Chats) {
        viewModelScope.launch {
            _selectedChats.value = chats
            loadChats(chats)
            val matchedModel = TextGenModelList.allModels.find { it.chutesName == chats.text_model_name }
            _selectedTextModel.value = matchedModel ?: TextGenModelList.defaultModel
            _tempSelectedTextModel.value = matchedModel ?: TextGenModelList.defaultModel
        }
    }
    // endregion

    // region History Implementation
    // ========================================================================================

    private fun generateChatDescription(): String {
        val fullDescription = _currentChatMessages.joinToString("\n") { it.message }
        return if (fullDescription.length > 150) "${fullDescription.take(150)}..." else fullDescription
    }

    private fun createChatHistory(description: String) {
        viewModelScope.launch {

            val isNewChat = _selectedChats.value == null
            val chatId = if (isNewChat) Uuid.random().toString() else _selectedChats.value!!.id

            val chats = Chats(
                id = chatId,
                title = if (isNewChat) "Chat ${_allChats.value.size + 1}" else _selectedChats.value!!.title,
                description = description,
                text_model_name = _selectedTextModel.value.chutesName,
                is_bookmarked = 0L,
                created_at = Clock.System.now().toString()
            )

            llmsDatabaseRepository.insertChatWithMessages(
                chat = chats,
                messages = _currentChatMessages.map {
                    it.copy(chat_id = chatId)
                }
            )
        }
    }

    private fun loadChats(chats: Chats) {
        viewModelScope.launch {
            _currentChatMessages.clear()
            val chatMessages = llmsDatabaseRepository.getMessagesByChatId(chats.id).firstOrNull() ?: emptyList()
            _currentChatMessages.addAll(chatMessages)

        }
    }
    // endregion
}