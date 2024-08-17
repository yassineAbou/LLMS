package org.yassineabou.playground.app

import androidx.compose.runtime.Composable
import org.koin.compose.KoinApplication
import org.yassineabou.playground.app.di.appModule
import org.yassineabou.playground.app.navigation.BottomNavigation
import org.yassineabou.playground.app.theme.AppTheme

@Composable
fun App() {
    KoinApplication(application = {
        modules(appModule())
    }) {
        AppTheme {
            BottomNavigation()
        }
    }
}

