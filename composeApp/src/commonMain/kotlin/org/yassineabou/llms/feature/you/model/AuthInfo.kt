package org.yassineabou.llms.feature.you.model

import kotlinx.datetime.Clock


data class AuthInfo(
    val userId: String,
    val username: String,
    val authMethod: AuthMethod,
    val credentialId: String? = null,
    val publicKey: String? = null,
    val authenticatorData: String? = null,
    val clientDataJSON: String? = null,
    val timestamp: Long = Clock.System.now().toEpochMilliseconds()
)

enum class AuthMethod {
    PASSKEY,
    GOOGLE,
}