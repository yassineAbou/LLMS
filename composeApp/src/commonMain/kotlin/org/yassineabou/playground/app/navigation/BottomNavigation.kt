package org.yassineabou.playground.app.navigation

import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import org.yassineabou.playground.feature.imageGen.ImageGenHorizontalPager
import org.yassineabou.playground.feature.profile.ui.ProfileTab
import org.yassineabou.playground.feature.textGen.TextGenHorizontalPager


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
