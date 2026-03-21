package org.yassineabou.llms.app.core.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Image
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation3.runtime.NavKey

data class NavigationBarItem(
    val name: String,
    val route: NavKey,
    val icon: ImageVector,
)

val listNavigationBarItems = listOf(
    NavigationBarItem(
        name = "Chat",
        route = ChatRoute,
        icon = Icons.AutoMirrored.Filled.Chat,
    ),
    NavigationBarItem(
        name = "Imagine",
        route = ImagineRoute,
        icon = Icons.Filled.Image,
    ),
    NavigationBarItem(
        name = "You",
        route = YouRoute,
        icon = Icons.Filled.AccountCircle,
    )
)