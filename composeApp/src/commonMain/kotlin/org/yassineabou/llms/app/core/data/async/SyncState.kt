@file:OptIn(ExperimentalTime::class)

package org.yassineabou.llms.app.core.data.async

import kotlin.time.Clock
import kotlin.time.ExperimentalTime

sealed class SyncState {

    data object Idle : SyncState()

    data object Syncing : SyncState()

    data class Success(
        val timestamp: String = Clock.System.now().toString()
    ) : SyncState()

    data class Error(
        val message: String,
        val timestamp: String = Clock.System.now().toString()
    ) : SyncState()
}