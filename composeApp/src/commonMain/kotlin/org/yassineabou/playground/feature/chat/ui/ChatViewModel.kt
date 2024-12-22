package org.yassineabou.playground.feature.chat.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.yassineabou.playground.feature.chat.model.ChatHistory
import org.yassineabou.playground.feature.chat.model.TextModel
import org.yassineabou.playground.feature.chat.model.generatedChatHistoryList
import org.yassineabou.playground.feature.chat.model.textGenModelList

class ChatViewModel: ViewModel() {


   private val _tempSelectedTextModel = MutableStateFlow<TextModel>(textGenModelList.first())
   val tempSelectedTextModel: StateFlow<TextModel> = _tempSelectedTextModel

   private val _selectedTextModel = MutableStateFlow<TextModel>(textGenModelList.first())
   val selectedTextModel: StateFlow<TextModel> = _selectedTextModel

   private val _chatHistoryList = mutableStateListOf<ChatHistory>().apply {
       addAll(generatedChatHistoryList) // Assuming this is where default values come from
   }
   val chatHistoryList: SnapshotStateList<ChatHistory> = _chatHistoryList


    private val _savedChatHistoryList =  mutableStateListOf<ChatHistory>()
    val savedChatHistoryList: SnapshotStateList<ChatHistory> = _savedChatHistoryList

    private val _selectedAIProviders = MutableStateFlow<Map<String, Boolean>>(mapOf(
        "Meta" to true,
        "Google" to true,
        "AnthraciteOrg" to true,
        "AI21 Lab" to true,
        "Hugging Face H4" to true,
        "Alibaba Cloud" to true,
        "Deep Seek" to true,
        "TII" to true,
        "CohereForAI" to true
    ))
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
}