package app
import androidx.compose.runtime.Composable
import app.navigation.BottomNavigation
import app.theme.AppTheme

@Composable
fun App() {
    AppTheme {
        BottomNavigation()
    }
}