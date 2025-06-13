package org.yassineabou.llms.app.core.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Image
import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationBarItem(
    val name: String,
    val route: String,
    val icon: ImageVector,
)

val listNavigationBarItems = listOf(
    NavigationBarItem(
        name = "Chat",
        route = Screen.ChatScreen.route,
        icon = Icons.AutoMirrored.Filled.Chat,
    ),
    NavigationBarItem(
        name = "Imagine",
        route = Screen.ImagineScreen.route,
        icon = Icons.Filled.Image,
    ),
    NavigationBarItem(
        name = "You",
        route = Screen.YouScreen.route,
        icon = Icons.Filled.AccountCircle,
    )
)