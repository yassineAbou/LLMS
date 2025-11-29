package org.yassineabou.llms.app.core.data.async

import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds




/**
 * Configuration for synchronization behavior.
 *
 * @property syncInterval How often to run background sync
 * @property retryDelay Delay before retrying failed operations
 * @property maxRetries Maximum retry attempts for failed operations
 */
data class SyncConfig(
    val syncInterval: Duration = 30.seconds,
    val retryDelay: Duration = 5.seconds,
    val maxRetries: Int = 3
)