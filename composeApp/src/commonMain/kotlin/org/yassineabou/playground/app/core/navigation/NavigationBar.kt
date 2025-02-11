package org.yassineabou.playground.app.core.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Person
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
        icon = Icons.Default.Image,
    ),
    NavigationBarItem(
        name = "Profile",
        route = Screen.Profile.route,
        icon = Icons.Default.Person,
    ),
)