package org.yassineabou.llms.feature.chat.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch
import org.yassineabou.llms.app.core.navigation.Navigator
import org.yassineabou.llms.feature.chat.ui.ChatViewModel
import org.yassineabou.llms.feature.chat.ui.history.RecentChatHistoryContent
import org.yassineabou.llms.feature.chat.ui.history.SavedChatHistoryContent

@Composable
fun HistoryHorizontalPager(
    modifier: Modifier = Modifier,
    chatViewModel: ChatViewModel,
    navigator: Navigator
) {
    val pagerState = rememberPagerState(pageCount = { 2 })
    val selectedTabIndex = remember { derivedStateOf { pagerState.currentPage } }

    Surface(
        color = MaterialTheme.colorScheme.surface
    ) {
        Column(
            modifier = modifier
        ) {
            HistoryTabRows(
                selectedTabIndex = selectedTabIndex,
                pagerState = pagerState
            )

            HistoryPagerContent(
                pagerState = pagerState,
                chatViewModel = chatViewModel,
                navigator = navigator
            )
        }

    }
}

@Composable
private fun HistoryTabRows(
    selectedTabIndex: State<Int>,
    pagerState: PagerState
) {
    val scope = rememberCoroutineScope()
    TabRow(
        selectedTabIndex = selectedTabIndex.value,
        modifier = Modifier.fillMaxWidth(fraction = 0.5f),
    ) {
        historyTabRows.forEachIndexed { index, currentTab ->
            Tab(
                selected = selectedTabIndex.value == index,
                selectedContentColor = MaterialTheme.colorScheme.primary,
                unselectedContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
                text = {
                    Text(
                        text = currentTab.title,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            )
        }
    }
}

@Composable
fun HistoryPagerContent(
    pagerState: PagerState,
    chatViewModel: ChatViewModel,
    navigator: Navigator
) {
    HorizontalPager(state = pagerState) { index ->
        when (index) {
            0 -> RecentChatHistoryContent(
                chatViewModel = chatViewModel,
                navigator = navigator
            )

            1 -> SavedChatHistoryContent(
                chatViewModel = chatViewModel,
                navigator = navigator
            )
        }
    }
}


