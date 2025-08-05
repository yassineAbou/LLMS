package org.yassineabou.llms

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import kotlinx.browser.document
import kotlinx.browser.window
import org.jetbrains.compose.resources.WebResourcesConfiguration
import org.jetbrains.compose.resources.configureWebResources
import org.yassineabou.llms.app.App

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    // 🔑 Fix resource path resolution for non-root deployments
    val basePath = window.location.pathname
        .substringBeforeLast('/', "")
        .let { if (it.isEmpty()) "/" else "$it/" }

    WebResourcesConfiguration.resourcePathMapping { path ->
        "$basePath$path"
    }

    ComposeViewport(document.body!!) {
         App()
     }
}