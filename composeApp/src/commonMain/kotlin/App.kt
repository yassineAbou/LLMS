
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import tabs.ImageGenTab
import tabs.ProfileTab
import tabs.TextGenTab
import theme.AppTheme

@Composable
fun App() {
    AppTheme {
        TabNavigator(TextGenTab) {
            Scaffold(
                content = { CurrentTab() },
                bottomBar = {
                    NavigationBar {
                        TabNavigationItem(TextGenTab)
                        TabNavigationItem(ImageGenTab)
                        TabNavigationItem(ProfileTab)
                    }
                }
            )
        }
    }
}