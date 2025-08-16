package org.yassineabou.llms.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import org.kodein.di.DI
import org.yassineabou.llms.app.core.di.LocalDI
import org.yassineabou.llms.app.core.di.appModule
import org.yassineabou.llms.app.core.theme.AppTheme


@Composable
fun App() {
    val di = remember {
        DI {
            import(appModule())
        }
    }
    CompositionLocalProvider(LocalDI provides di) {
        AppTheme {
            MainScreen()
        }
    }
}




