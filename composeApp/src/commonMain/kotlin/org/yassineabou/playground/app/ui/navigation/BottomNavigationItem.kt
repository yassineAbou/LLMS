package org.yassineabou.playground.app.ui.navigation

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab

@Composable
fun RowScope.TabNavigationItem(tab: Tab) {
    val localBottomNavigator = LocalTabNavigator.current

    NavigationBarItem(
        selected = localBottomNavigator.current == tab,
        onClick = { localBottomNavigator.current = tab },
        icon = { tab.options.icon?.let { Icon(painter = it, contentDescription = null) } },
        label = { Text(tab.options.title) }
    )
}