@file:OptIn(ExperimentalTime::class)

package org.yassineabou.llms.feature.you.data.model

import kotlin.time.Clock
import kotlin.time.ExperimentalTime

data class AuthInfo(
    val userId: String,
    val username: String,
    val credentialId: String? = null,
    val publicKey: String? = null,
    val authenticatorData: String? = null,
    val clientDataJSON: String? = null,
    val imageUrl: String? = null,
    val timestamp: Long = Clock.System.now().toEpochMilliseconds()
)