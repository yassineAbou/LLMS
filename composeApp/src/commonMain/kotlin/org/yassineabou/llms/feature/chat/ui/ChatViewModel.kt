@file:OptIn(ExperimentalUuidApi::class, ExperimentalTime::class)

package org.yassineabou.llms.feature.chat.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.yassineabou.llms.Chat_messages
import org.yassineabou.llms.Chats
import org.yassineabou.llms.app.core.data.remote.ai.AiRepository
import org.yassineabou.llms.app.core.data.remote.ai.GenerationState
import org.yassineabou.llms.app.core.data.async.AsyncManager
import org.yassineabou.llms.feature.chat.data.model.ChatMessage
import org.yassineabou.llms.feature.chat.data.model.TextModel
import kotlin.coroutines.cancellation.CancellationException
import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid


class ChatViewModel(
    private val aiRepository: AiRepository,
    private val asyncManager: AsyncManager
) : ViewModel() {

    // ========================================================================================
    //                                  State Properties
    // ========================================================================================

    // region Text Model Selection State
    // ========================================================================================
    private val _tempSelectedTextModel = MutableStateFlow<TextModel>(TextModel.DEFAULT)
    private val _selectedTextModel = MutableStateFlow<TextModel>(TextModel.DEFAULT)

    val tempSelectedTextModel: StateFlow<TextModel> = _tempSelectedTextModel
    val selectedTextModel: StateFlow<TextModel> = _selectedTextModel
    // endregion

    // region Dynamic Text Models State
    // ========================================================================================
    private val _availableTextModels = MutableStateFlow<List<TextModel>>(emptyList())
    val availableTextModels: StateFlow<List<TextModel>> = _availableTextModels.asStateFlow()

    private val _isLoadingModels = MutableStateFlow(false)
    val isLoadingModels: StateFlow<Boolean> = _isLoadingModels.asStateFlow()

    private val _modelsLoadError = MutableStateFlow<String?>(null)
    val modelsLoadError: StateFlow<String?> = _modelsLoadError.asStateFlow()
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
        viewModelScope.launch {
            asyncManager.getAllChats().collect { chats ->
                _allChats.update { chats }

                if (chats.isEmpty()) {
                    resetCurrentChat()
                }
            }
        }

        loadTextModels()
    }

    // ========================================================================================
    //                                  Model Management
    // ========================================================================================

    // region Load Text Models
    // ========================================================================================
    fun loadTextModels() {
        viewModelScope.launch {
            _isLoadingModels.value = true
            _modelsLoadError.value = null

            aiRepository.getTextModels()
                .onSuccess { models ->
                    _availableTextModels.value = models

                    if (models.isNotEmpty() && _selectedTextModel.value == TextModel.DEFAULT) {
                        val cheapestModel = models.first()
                        _selectedTextModel.value = cheapestModel
                        _tempSelectedTextModel.value = cheapestModel
                    }
                }
                .onFailure { error ->
                    _modelsLoadError.value = error.message ?: "Failed to load models"

                    if (_availableTextModels.value.isEmpty()) {
                        _availableTextModels.value = listOf(TextModel.DEFAULT)
                    }
                }

            _isLoadingModels.value = false
        }
    }
    // endregion

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
        if (modelChanged) {
            handleModelChangeWorkflow()
        } else {
            confirmSelectedTextModel()
        }
    }

    private fun handleModelChangeWorkflow() {
        confirmSelectedTextModel()
        if (_currentChatMessages.isNotEmpty()) {
            resetCurrentChat()
        }
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
        val userMessage = _currentChatMessages.lastOrNull { it.is_user == 1L }?.message ?: ""
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
            val currentMessage = _currentChatMessages[messageIndex]
            val currentModel = _selectedTextModel.value

            _currentChatMessages[messageIndex] = currentMessage.copy(message = "")
            _generationState.value = GenerationState.Loading(messageIndex)

            try {
                val conversationHistory = _currentChatMessages
                    .take(maxOf(0, messageIndex - 1))
                    .filter { it.message.isNotBlank() }
                    .map { dbMessage ->
                        ChatMessage(
                            role = if (dbMessage.is_user == 1L) "user" else "assistant",
                            content = dbMessage.message
                        )
                    }

                aiRepository.streamChat(
                    prompt = prompt,
                    textModel = currentModel,
                    conversationHistory = conversationHistory
                ).collect { chunk ->
                    if (generationState.value is GenerationState.Loading) {
                        _currentChatMessages[messageIndex] = _currentChatMessages[messageIndex].copy(
                            message = _currentChatMessages[messageIndex].message + chunk
                        )
                    }
                }

                _generationState.value = GenerationState.Success
                autoSaveCurrentChat()

            } catch (e: Exception) {
                handleGenerationError(e, messageIndex)
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
    //                              Auto-Save Function
    // ========================================================================================

    private fun autoSaveCurrentChat() {
        if (_currentChatMessages.isEmpty()) return

        viewModelScope.launch {
            val isNewChat = _selectedChats.value == null
            val chatId = if (isNewChat) {
                Uuid.random().toString()
            } else {
                _selectedChats.value!!.id
            }

            val description = generateChatDescription()

            val chat = Chats(
                id = chatId,
                title = if (isNewChat) {
                    "Chat ${_allChats.value.size + 1}"
                } else {
                    _selectedChats.value!!.title
                },
                description = description,
                text_model_name = _selectedTextModel.value.modelName,
                is_bookmarked = _selectedChats.value?.is_bookmarked ?: 0L,
                created_at = _selectedChats.value?.created_at ?: Clock.System.now().toString()
            )

            asyncManager.insertChatWithMessages(
                chat = chat,
                messages = _currentChatMessages.map { it.copy(chat_id = chatId) }
            )

            if (isNewChat) {
                _selectedChats.value = chat
            }
        }
    }

    // ========================================================================================
    //                                  Chat History Management
    // ========================================================================================

    // region Public Chat Methods
    // ========================================================================================

    fun startNewChat(forceNew: Boolean = false) {
        resetCurrentChat()
    }

    fun resetCurrentChat() {
        _currentChatMessages.clear()
        _selectedChats.value = null
    }

    fun deleteChats(chats: Chats) {
        viewModelScope.launch {
            asyncManager.deleteChatById(chats.id)
            if (_selectedChats.value?.id == chats.id) {
                resetCurrentChat()
            }
        }
    }

    fun clearChats() {
        viewModelScope.launch {
            asyncManager.clearAllChats()
            resetCurrentChat()
        }
    }

    fun toggleBookmark(chat: Chats) {
        viewModelScope.launch {
            val updatedChat = chat.copy(
                is_bookmarked = if (chat.is_bookmarked == 1L) 0L else 1L
            )
            asyncManager.insertChat(updatedChat)

            if (_selectedChats.value?.id == chat.id) {
                _selectedChats.value = updatedChat
            }
        }
    }

    fun selectChats(chats: Chats) {
        viewModelScope.launch {
            _selectedChats.value = chats
            loadChats(chats)

            val matchedModel = _availableTextModels.value.find { it.modelName == chats.text_model_name }
            _selectedTextModel.value = matchedModel ?: TextModel.DEFAULT
            _tempSelectedTextModel.value = matchedModel ?: TextModel.DEFAULT
        }
    }
    // endregion

    // region History Implementation
    // ========================================================================================

    private fun generateChatDescription(): String {
        val fullDescription = _currentChatMessages.joinToString("\n") { it.message }
        return if (fullDescription.length > 150) "${fullDescription.take(150)}..." else fullDescription
    }

    private fun loadChats(chats: Chats) {
        viewModelScope.launch {
            _currentChatMessages.clear()
            val chatMessages = asyncManager.getMessagesByChatId(chats.id).firstOrNull() ?: emptyList()
            _currentChatMessages.addAll(chatMessages)
        }
    }
    // endregion
}