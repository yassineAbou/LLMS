package org.yassineabou.llms.feature.you.ui.util

actual object PasskeyAuth {
    actual suspend fun signInWithPasskey(
        onSuccess: (String) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
    }
}