package app.ui

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import app.ui.navigation.Navigation
import cafe.adriel.voyager.core.screen.Screen
import feature.ScreenA

@Composable
fun App() {
    MaterialTheme {
        Navigation(MainScreen)
    }
}

private object MainScreen : Screen {

    @Composable
    override fun Content() {
       ScreenA().Content()
    }
}
