package org.yassineabou.llms.feature.you.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sunildhiman90.kmauth.google.KMAuthGoogle
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.yassineabou.llms.feature.you.data.model.AuthInfo
import org.yassineabou.llms.feature.you.data.model.AuthState

class YouViewModel : ViewModel() {

    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn

    private val _authInfo = MutableStateFlow<AuthInfo?>(null)
    val authInfo: StateFlow<AuthInfo?> = _authInfo

    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState: StateFlow<AuthState> = _authState


    private val googleAuthManager = KMAuthGoogle.googleAuthManager

    fun onLogin() {
        handleGoogleLogin()
    }

    private fun handleGoogleLogin() {
        viewModelScope.launch {
            // --- FAKE DATA FOR GOOGLE LOGIN ---
            // Replace this block with real Google Auth when needed
            /*
             delay(1000) // Simulate network delay
            _authInfo.value = AuthInfo(
                userId = "fake-google-id-123",
                username = "Fake Google User",
                imageUrl = "https://example.com/fake-profile-pic.png",
                authMethod = AuthMethod.GOOGLE
            )
            _isLoggedIn.value = true
            _authState.value = AuthState.Success

             */


            // --- REAL GOOGLE AUTH CODE ---
            // Uncomment this block to use real Google Authentication
            _authState.value = AuthState.Loading
            val result = googleAuthManager.signIn()
                if (result.isSuccess) {
                    val user = result.getOrNull()
                    _authInfo.value = AuthInfo(
                        userId  = user?.id ?: "",
                        username = user?.name ?: user?.email ?: "Google User",
                        imageUrl = user?.profilePicUrl,
                    )
                    _isLoggedIn.value = true
                    _authState.value = AuthState.Success
                } else {
                    _authState.value = AuthState.Error(
                        result.exceptionOrNull()?.message ?: "Google sign-in failed"
                    )
                    //Logger.e(result.exceptionOrNull()) { result.exceptionOrNull()?.message.toString() }
                }


        }

    }

    fun onLogout() {
        // Sign out from Google as well
        viewModelScope.launch {
            _isLoggedIn.value = false
            _authInfo.value = null
            _authState.value = AuthState.Idle


            // --- REAL GOOGLE AUTH SIGN OUT ---
            // Uncomment this to sign out from Google as well
            googleAuthManager.signOut()
        }
    }
}