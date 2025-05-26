package org.yassineabou.llms

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.yassineabou.llms.app.App
import org.yassineabou.llms.app.core.util.FileKit

fun main() = application {

    FileKit.init(appId = "LLMs")


    Window(
        onCloseRequest = ::exitApplication,
        title = "LLMs",
    ) {
        App()
    }
}