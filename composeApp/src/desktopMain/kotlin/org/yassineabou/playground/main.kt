package org.yassineabou.playground

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.yassineabou.playground.app.App

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "LLMS",
    ) {
        App()
    }
}