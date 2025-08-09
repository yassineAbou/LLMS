package org.yassineabou.llms

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.sunildhiman90.kmauth.core.KMAuthInitializer
import org.yassineabou.llms.app.App
import org.yassineabou.llms.app.core.util.FileKit
import org.yassineabou.llms.app.core.util.GoogleOAuthConfig

fun main() = application {

    FileKit.init(appId = "LLMs")

    KMAuthInitializer.initClientSecret(
        clientSecret = GoogleOAuthConfig.CLIENT_SECRET
    )
    Window(
        onCloseRequest = ::exitApplication,
        title = "LLMs",
    ) {
        App()
    }
}