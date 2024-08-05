package app
import androidx.compose.runtime.Composable
import app.navigation.BottomNavigation
import app.theme.AppTheme
import di.appModule
import org.koin.compose.KoinApplication

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