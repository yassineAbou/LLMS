package org.yassineabou.llms.app.core.util

import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.yassineabou.llms.db.LlmsDatabase

fun setupWasmDatabasePersistence(db: LlmsDatabase, scope: CoroutineScope) {
    requestPersistentStorage()

    document.addEventListener("visibilitychange") {
        scope.launch { db.persistSnapshotNow() }
    }

    window.addEventListener("beforeunload") {
        scope.launch { db.persistSnapshotNow() }
    }
}

private fun requestPersistentStorage() {
    js("navigator.storage && navigator.storage.persist && navigator.storage.persist()")
}