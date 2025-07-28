package org.yassineabou.llms.feature.you.ui.util

expect object PasskeyAuth {
    suspend fun signInWithPasskey(onSuccess: (String) -> Unit, onFailure: (Throwable) -> Unit)
}