package org.yassineabou.llms.feature.you

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class YouViewModel : ViewModel() {
    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn


    // Auth actions
    fun onLogin() {
        _isLoggedIn.value = true

    }

    fun onLogout() {
        _isLoggedIn.value = false
    }
}


