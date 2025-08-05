package org.yassineabou.llms.feature.you.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import com.sunildhiman90.kmauth.google.KMAuthGoogle
import kotlinx.coroutines.delay
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

    // Uncomment this if you want to try Google Authentication:
    //private val googleAuthManager = KMAuthGoogle.googleAuthManager

    fun onLogin(authMethod: AuthMethod) {
        if (authMethod == AuthMethod.PASSKEY) {
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
        viewModelScope.launch {
            // --- FAKE DATA FOR GOOGLE LOGIN ---
            // Replace this block with real Google Auth when needed
             delay(1000) // Simulate network delay
            _authInfo.value = AuthInfo(
                userId = "fake-google-id-123",
                username = "Fake Google User",
                imageUrl = "https://example.com/fake-profile-pic.png",
                authMethod = AuthMethod.GOOGLE
            )
            _isLoggedIn.value = true
            _authState.value = AuthState.Success


            // --- REAL GOOGLE AUTH CODE ---
            // Uncomment this block to use real Google Authentication
            /*
            _authState.value = AuthState.Loading
            val result = googleAuthManager.signIn()
                if (result.isSuccess) {
                    val user = result.getOrNull()
                    _authInfo.value = AuthInfo(
                        userId  = user?.id ?: "",
                        username = user?.name ?: user?.email ?: "Google User",
                        imageUrl = user?.profilePicUrl,
                        authMethod = AuthMethod.GOOGLE
                    )
                    _isLoggedIn.value = true
                    _authState.value = AuthState.Success
                } else {
                    _authState.value = AuthState.Error(
                        result.exceptionOrNull()?.message ?: "Google sign-in failed"
                    )
                    //Logger.e(result.exceptionOrNull()) { result.exceptionOrNull()?.message.toString() }
                }
             */

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
            // googleAuthManager.signOut()
        }
    }
}