package org.yassineabou.playground

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.yassineabou.playground.app.App
import org.yassineabou.playground.app.core.util.FileKit

fun main() = application {

    FileKit.init(appId = "LLMs")


    Window(
        onCloseRequest = ::exitApplication,
        title = "LLMs",
    ) {
        App()
    }
}