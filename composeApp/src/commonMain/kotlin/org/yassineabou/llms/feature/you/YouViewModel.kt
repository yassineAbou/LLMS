package org.yassineabou.llms.feature.you

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.yassineabou.llms.feature.you.model.AuthScreen

class YouViewModel: ViewModel (){
    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn

    private val _authScreen = MutableStateFlow<AuthScreen>(AuthScreen.Login)
    val authScreen: StateFlow<AuthScreen> = _authScreen

    private val _showAuthSheet = MutableStateFlow(false)
    val showAuthSheet: StateFlow<Boolean> = _showAuthSheet

    fun onLogin() {
        _isLoggedIn.value = true
        _showAuthSheet.value = false
    }

    fun onLogout() {
        _isLoggedIn.value = false
    }

    fun onShowAuthSheet(show: Boolean) {
        _showAuthSheet.value = show
    }

    fun navigateTo(screen: AuthScreen) {
        _authScreen.value = screen
    }

    fun resetAuthScreen() {
        _authScreen.value = AuthScreen.Login
    }

}


