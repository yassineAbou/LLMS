package org.yassineabou.playground.feature.chat.ui.history

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.yassineabou.playground.app.ui.navigation.Screen
import org.yassineabou.playground.app.ui.view.FullScreenBackIcon
import org.yassineabou.playground.feature.chat.listDetailPane.ListDetailPane
import org.yassineabou.playground.feature.chat.ui.ChatViewModel
import org.yassineabou.playground.feature.chat.ui.view.ClearHistoryDialog
import org.yassineabou.playground.feature.chat.ui.view.HistoryHorizontalPager


@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun ChatHistoryScreen(
    navController: NavController,
    chatViewModel: ChatViewModel
) {
    var showClearHistoryDialog by remember { mutableStateOf(false) }
    // Get the window size class to determine the screen size
    val windowSizeClass = calculateWindowSizeClass()

    // Check if the screen width is medium or larger
    val isMediumOrLarger = windowSizeClass.widthSizeClass > WindowWidthSizeClass.Medium

    if (isMediumOrLarger) {
        // Show ListDetailPane for medium or larger screens
        ListDetailPane(chatViewModel = chatViewModel)
    } else {
        Column(
            modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            HistoryTopBar(
                modifier = Modifier.fillMaxWidth().statusBarsPadding(),
                onBackPress = { navController.popBackStack() },
                onClearHistory = { showClearHistoryDialog = true }
            )
            HistoryHeaderRow(onHistorySearch = { navController.navigate(Screen.SearchHistoryScreen.route) })

            HistoryHorizontalPager(chatViewModel = chatViewModel, navController = navController)

            if (showClearHistoryDialog) {
                ClearHistoryDialog(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(375.dp),
                    onDismiss = { showClearHistoryDialog = false },
                    onConfirm = {
                        chatViewModel.clearChatHistory()
                        showClearHistoryDialog = false
                    }
                )
            }
        }
    }


}

@Composable
private fun HistoryTopBar(
    modifier: Modifier = Modifier,
    onBackPress: () -> Unit,
    onClearHistory: () -> Unit
) {
    Row(
        modifier = modifier,
    ) {
        FullScreenBackIcon(
            onBackPress = onBackPress
        )
        Spacer(modifier = Modifier.weight(1f))
        TextButton(
            modifier = Modifier.padding(top = 12.dp),
            onClick = onClearHistory
        ) {
            Text(
                text = "Clear",
                style = MaterialTheme.typography.titleMedium,
            )
        }
    }
}

@Composable
private fun HistoryHeaderRow(
    modifier: Modifier = Modifier,
    onHistorySearch: () -> Unit
) {
    Row(modifier = modifier) {
        Text(
            text = "History",
            style = MaterialTheme.typography.displaySmall,
            fontWeight = FontWeight.W900,
            modifier = Modifier.padding(start = 8.dp)
        )
        Spacer(modifier = Modifier.weight(1F))
        IconButton(onClick = onHistorySearch) {
            Icon(
                imageVector = Icons.Outlined.Search,
                contentDescription = "Search",
                modifier = Modifier.size(40.dp)
            )
        }
    }
}