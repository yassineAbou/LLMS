
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import screens.ScreenA

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
