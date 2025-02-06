package org.yassineabou.playground.feature.chat.ui.chat

import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import org.yassineabou.playground.app.ui.navigation.Screen
import org.yassineabou.playground.feature.chat.listDetailPane.ListDetailPane
import org.yassineabou.playground.feature.chat.ui.ChatViewModel


@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun ChatScreen(
    navController: NavController,
    chatViewModel: ChatViewModel
) {
    // Get the window size class to determine the screen size
    val windowSizeClass = calculateWindowSizeClass()

    // Check if the screen width is medium or larger
    val isMediumOrLarger = windowSizeClass.widthSizeClass > WindowWidthSizeClass.Medium
    val selectedTextModel by chatViewModel.selectedTextModel.collectAsStateWithLifecycle()

    if (isMediumOrLarger) {
        // Show ListDetailPane for medium or larger screens
        ListDetailPane(chatViewModel = chatViewModel)
    } else {
        ChatContent(
            chatViewModel = chatViewModel,
            selectedTextModel = selectedTextModel,
            showAppBar = true,
            onClickHistory = { navController.navigate(Screen.ChatHistoryScreen.route) }
        )
    }
}




