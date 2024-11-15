package org.yassineabou.playground.app.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Brush
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Sms
import androidx.compose.material.icons.outlined.Brush
import androidx.compose.material.icons.outlined.GridView
import androidx.compose.material.icons.outlined.History
import androidx.compose.material.icons.outlined.Sms
import androidx.compose.ui.graphics.vector.ImageVector

data class TopTabsRow(val title: String, val selectedIcon: ImageVector, val unselectedIcon: ImageVector)

val textGenTabRows = listOf(
    TopTabsRow(title = "Chat", selectedIcon = Icons.Filled.Sms, unselectedIcon = Icons.Outlined.Sms),
    TopTabsRow(title = "History", selectedIcon = Icons.Filled.History, unselectedIcon = Icons.Outlined.History)
)

val imageGenTabRows = listOf(
    TopTabsRow(title = "Imagine", selectedIcon = Icons.Filled.Brush, unselectedIcon = Icons.Outlined.Brush),
    TopTabsRow(" Generated Images", selectedIcon = Icons.Filled.GridView, unselectedIcon = Icons.Outlined.GridView)
)