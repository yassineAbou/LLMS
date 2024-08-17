package org.yassineabou.playground.app.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.TextSnippet
import androidx.compose.material.icons.automirrored.outlined.TextSnippet
import androidx.compose.material.icons.filled.AddPhotoAlternate
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Sms
import androidx.compose.material.icons.outlined.AddPhotoAlternate
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
    TopTabsRow(title = "Txt2Img", selectedIcon = Icons.AutoMirrored.Filled.TextSnippet, unselectedIcon = Icons.AutoMirrored.Outlined.TextSnippet),
    TopTabsRow("Img2Img", selectedIcon = Icons.Filled.AddPhotoAlternate, unselectedIcon = Icons.Outlined.AddPhotoAlternate),
    TopTabsRow("Images", selectedIcon = Icons.Filled.GridView, unselectedIcon = Icons.Outlined.GridView)
)