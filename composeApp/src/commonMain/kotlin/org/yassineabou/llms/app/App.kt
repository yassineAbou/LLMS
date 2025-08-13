package org.yassineabou.llms.app

import androidx.compose.material3.SnackbarDuration
import androidx.compose.runtime.*
import co.touchlab.kermit.Logger
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.kodein.di.DI
import org.yassineabou.llms.UserService
import org.yassineabou.llms.app.core.di.LocalDI
import org.yassineabou.llms.app.core.di.appModule
import org.yassineabou.llms.app.core.sharedViews.SnackbarController
import org.yassineabou.llms.app.core.theme.AppTheme
import org.yassineabou.llms.app.core.util.setupRPC


@Composable
fun App() {
    var service: UserService? by remember { mutableStateOf(null) }
    var connected by remember { mutableStateOf(false) }
    val di = remember {
        DI {
            import(appModule())
        }
    }

    // Connect & Ping the RPC server
    LaunchedEffect(Unit) {
        launch {
            while (true) {
                // Attempt to (re)connect to the RPC server.
                while (!connected) {
                    try {
                        service = setupRPC()
                        connected = true
                        SnackbarController.showMessage(
                            message = "Connected to server",
                            duration = SnackbarDuration.Short
                        )
                    } catch (e: Exception) {
                        connected = false
                        SnackbarController.showMessage(
                            message = "Connection failed: ${e.message}",
                            duration = SnackbarDuration.Long
                        )
                        Logger.i("Rpc") { "${e.message}" }
                    }
                    delay(2000) // Wait 2 seconds before trying to reconnect.
                }

                // Ping the RPC server every second.
                service?.let {
                    try {
                        while (true) {
                            println(service?.ping())
                            delay(1000)

                        }
                    } catch (e: Exception) {
                        connected = false
                        SnackbarController.showMessage(
                            message = "Connection lost, trying to reconnect...",
                            duration = SnackbarDuration.Long
                        )
                        Logger.i("Rpc") { "${e.message}" }
                    }
                }
            }
        }
    }

    CompositionLocalProvider(LocalDI provides di) {
        AppTheme {
            MainScreen()
        }
    }
}




