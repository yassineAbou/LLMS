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


@Composable
fun App() {
    var di by remember { mutableStateOf<DI?>(null) }

    LaunchedEffect(Unit) {
        launch {
            di = createDI()
        }
    }

    if (di == null) {
        AppLoadingIndicator(modifier = Modifier.fillMaxSize())
    } else {
        CompositionLocalProvider(LocalDI provides di!!) {
            AppTheme {
                MainScreen()
            }
        }
    }
}


