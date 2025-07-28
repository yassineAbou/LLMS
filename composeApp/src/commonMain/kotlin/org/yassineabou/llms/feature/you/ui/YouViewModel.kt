package org.yassineabou.llms.feature.you.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.yassineabou.llms.feature.you.model.AuthInfo
import org.yassineabou.llms.feature.you.model.AuthMethod
import org.yassineabou.llms.feature.you.model.AuthState
import org.yassineabou.llms.feature.you.ui.util.AuthUtils
import org.yassineabou.llms.feature.you.ui.util.PasskeyAuth

class YouViewModel : ViewModel() {

    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn

    private val _authInfo = MutableStateFlow<AuthInfo?>(null)
    val authInfo: StateFlow<AuthInfo?> = _authInfo

    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState: StateFlow<AuthState> = _authState

    fun onLogin(provider: String) {
        if (provider == "Passkey") {
            handlePasskeyLogin()
        } else {
            handleGoogleLogin()
        }
    }

    private fun handlePasskeyLogin() {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            PasskeyAuth.signInWithPasskey(
                onSuccess = { authData ->
                    _authInfo.value = AuthUtils.parsePasskeyAuthData(authData)
                    _isLoggedIn.value = true
                    _authState.value = AuthState.Success
                },
                onFailure = { error ->
                    _authState.value = AuthState.Error(error.message ?: "Authentication failed")
                }
            )
        }
    }

    private fun handleGoogleLogin() {
        _authInfo.value = AuthInfo(
            userId = "user123",
            username = "user@gmail.com",
            authMethod = AuthMethod.GOOGLE
        )
        _isLoggedIn.value = true
        _authState.value = AuthState.Success
    }

    fun onLogout() {
        _isLoggedIn.value = false
        _authInfo.value = null
        _authState.value = AuthState.Idle
    }
}