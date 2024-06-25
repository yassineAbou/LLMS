package app.navigation

import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import features.profile.ProfileTab
import features.imageGen.ImageGenHorizontalPager
import features.textGen.TextGenHorizontalPager

@Composable
fun BottomNavigation() {
    TabNavigator(TextGenHorizontalPager) {
        Scaffold(
            content = { CurrentTab() },
            topBar = {},
            bottomBar = {
                NavigationBar {
                    TabNavigationItem(TextGenHorizontalPager)
                    TabNavigationItem(ImageGenHorizontalPager)
                    TabNavigationItem(ProfileTab)
                }
            }
        )
    }
}