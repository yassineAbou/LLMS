package org.yassineabou.playground.feature.profile.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.yassineabou.playground.feature.profile.model.UserUiState
import org.yassineabou.playground.feature.profile.model.prototypeUser

class ProfileViewModel : ViewModel() {

    private val _userUiState = MutableStateFlow<UserUiState?>(null)
    val userUiState = _userUiState.asStateFlow()

    private val _showBottomSheet = MutableStateFlow(false)
    val showBottomSheet = _showBottomSheet.asStateFlow()

    fun onLogin() {
        _showBottomSheet.value = true
    }

    fun onLogout() {
        _userUiState.value = null
    }

    fun onAuthenticated() {
        _userUiState.value = prototypeUser
    }

    fun onDismissBottomSheet() {
        _showBottomSheet.value = false
    }

}