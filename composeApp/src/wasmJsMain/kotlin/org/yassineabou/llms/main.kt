@file:OptIn(ExperimentalComposeUiApi::class)

package org.yassineabou.llms

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.jetbrains.compose.resources.WebResourcesConfiguration
import org.jetbrains.compose.resources.configureWebResources
import org.yassineabou.llms.app.App
import org.yassineabou.llms.app.core.util.setupWasmDatabasePersistence

fun main() {
    val basePath = window.location.pathname
        .substringBeforeLast('/', "")
        .let { if (it.isEmpty()) "/" else "$it/" }

    WebResourcesConfiguration.resourcePathMapping { path -> "$basePath$path" }

    val appScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    ComposeViewport(document.body!!) {
        App(configureWasmPersistence = { db ->
            setupWasmDatabasePersistence(db, appScope)
        })
    }
}