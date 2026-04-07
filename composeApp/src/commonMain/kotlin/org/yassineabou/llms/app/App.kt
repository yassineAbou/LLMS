package org.yassineabou.llms.app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch
import org.kodein.di.DI
import org.yassineabou.llms.app.core.di.LocalDI
import org.yassineabou.llms.app.core.di.createDI
import org.yassineabou.llms.app.core.sharedViews.AppLoadingIndicator
import org.yassineabou.llms.app.core.theme.AppTheme
import org.yassineabou.llms.app.core.util.DebugTestScreen
import org.yassineabou.llms.db.LlmsDatabase


@Composable
fun App(configureWasmPersistence: (LlmsDatabase) -> Unit = {}) {
    var di by remember { mutableStateOf<DI?>(null) }
    var showDebug by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        launch {
            di = createDI(configureWasmPersistence)
        }
    }
    AppTheme {
        if (di == null) {
            AppLoadingIndicator(modifier = Modifier.fillMaxSize())
        } else {
            CompositionLocalProvider(LocalDI provides di!!) {
                if (showDebug) {
                    DebugTestScreen(onClose = { showDebug = false })
                } else {
                    MainScreen()
                }

            }
        }
    }
}


