
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import screens.ScreenA
import theme.AppTheme

@Composable
fun App() {
    AppTheme {
        Navigation(MainScreen)
    }
}

private object MainScreen : Screen {
    private fun readResolve(): Any = MainScreen

    @Composable
    override fun Content() {
       ScreenA().Content()
    }
}
