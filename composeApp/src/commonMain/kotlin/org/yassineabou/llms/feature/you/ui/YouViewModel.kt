@file:OptIn(ExperimentalTime::class)

package org.yassineabou.llms.feature.you.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sunildhiman90.kmauth.google.KMAuthGoogle
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.yassineabou.llms.app.core.data.local.LlmsDatabaseInterface
import org.yassineabou.llms.feature.you.data.model.AuthInfo
import org.yassineabou.llms.feature.you.data.model.AuthState
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

class YouViewModel(private val llmsDatabaseRepository: LlmsDatabaseInterface) : ViewModel() {

    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn

    private val _authInfo = MutableStateFlow<AuthInfo?>(null)
    val authInfo: StateFlow<AuthInfo?> = _authInfo

    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState: StateFlow<AuthState> = _authState


    private val googleAuthManager = KMAuthGoogle.googleAuthManager

    init {
        // Check if user is already logged in from local database
        checkInitialAuthState()
    }

    private fun checkInitialAuthState() {
        viewModelScope.launch {
            llmsDatabaseRepository.getCurrentUser().collect { dbUser ->
                if (dbUser != null) {
                    // User found in local database - restore session
                    _authInfo.value = AuthInfo(
                        userId = dbUser.google_sub_id,
                        email = dbUser.email,
                        username = dbUser.username,
                        imageUrl = dbUser.profile_pic_url
                    )
                    _isLoggedIn.value = true
                } else {
                    // No user in database - logged out state
                    _authInfo.value = null
                    _isLoggedIn.value = false
                }
            }
        }
    }

    fun onLogin() {
        handleGoogleLogin()
    }

    private fun handleGoogleLogin() {
        viewModelScope.launch {
            _authState.value = AuthState.Loading

            val result = googleAuthManager.signIn()
            val user = result.getOrNull()

            // --- Guard Clause 1: Handle sign-in failure ---
            if (user == null) {
                val errorMessage = result.exceptionOrNull()?.message ?: "Google sign-in failed."
                _authState.value = AuthState.Error(errorMessage)
                return@launch
            }

            // --- Guard Clause 2: Handle invalid user data ---
            val userId = user.idToken
            if (userId?.isBlank() != false) {
                _authState.value = AuthState.Error("Failed to get valid user ID from Google.")
                return@launch
            }

            // --- Happy Path ---
            val email = user.email ?: "no-email@provided.com"
            val username = user.name ?: email.substringBefore("@")
            val profilePicUrl = user.profilePicUrl

            // Update UI state
            _authInfo.value = AuthInfo(
                userId = userId,
                email = email,
                username = username,
                imageUrl = profilePicUrl,
            )
            _isLoggedIn.value = true
            _authState.value = AuthState.Success

            llmsDatabaseRepository.saveUser(
                googleSubId = userId,
                email = email,
                username = username,
                profilePicUrl = profilePicUrl,
                createdAt = Clock.System.now().toString()
            )
        }
    }

    fun onLogout() {
        // Sign out from Google as well
        viewModelScope.launch {
            _isLoggedIn.value = false
            _authInfo.value = null
            _authState.value = AuthState.Idle

            llmsDatabaseRepository.clearUser()


            // --- REAL GOOGLE AUTH SIGN OUT ---
            // Uncomment this to sign out from Google as well
            googleAuthManager.signOut()
        }
    }

}