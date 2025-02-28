package org.yassineabou.playground.feature.chat.ui.history

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
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
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.yassineabou.playground.app.core.sharedViews.FullScreenBackIcon
import org.yassineabou.playground.feature.chat.model.ChatHistory
import org.yassineabou.playground.feature.imagine.ui.view.DropDownDialog
import org.yassineabou.playground.feature.chat.ui.listDetailPane.ListDetailPane
import org.yassineabou.playground.feature.chat.ui.ChatViewModel
import org.yassineabou.playground.feature.chat.ui.view.ClearHistoryDialogContent
import org.yassineabou.playground.feature.chat.ui.view.HistoryHorizontalPager
import org.yassineabou.playground.feature.imagine.ui.util.rememberIsLargeScreen


@Composable
fun ChatHistoryScreen(
    navController: NavController,
    chatViewModel: ChatViewModel
) {

    val isLargeScreen = rememberIsLargeScreen()

    if (isLargeScreen) {
        ListDetailPane(chatViewModel = chatViewModel)
    } else {
        ChatHistoryContent(navController = navController, chatViewModel = chatViewModel)
    }
}

@Composable
private fun ChatHistoryContent(
    navController: NavController,
    chatViewModel: ChatViewModel
) {
    var showClearHistoryDialog by remember { mutableStateOf(false) }
    val chatHistoryList = chatViewModel.chatHistoryList

    Column(
        modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        HistoryTopBar(
            modifier = Modifier.fillMaxWidth().statusBarsPadding(),
            onBackPress = { navController.popBackStack() },
            onClearHistory = {
                if (chatHistoryList.isNotEmpty()) {
                    showClearHistoryDialog = true
                }
            }
        )

        HistoryHeader()

        HistoryHorizontalPager(chatViewModel = chatViewModel, navController = navController)

        if (showClearHistoryDialog) {
            DropDownDialog(
                onDismissRequest = {
                    showClearHistoryDialog = false
                }
            ) {
                ClearHistoryDialogContent(
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
private fun HistoryHeader(
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        Text(
            text = "History",
            style = MaterialTheme.typography.displaySmall,
            fontWeight = FontWeight.W900,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}