package org.yassineabou.llms.feature.you.model

sealed class AuthScreen(val order: Int) {
    object Login : AuthScreen(0)
    object Register : AuthScreen(1)
    object ForgotPassword : AuthScreen(2)
}