package org.yassineabou.playground.feature.chat.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch
import org.yassineabou.playground.feature.chat.model.historyTabRows
import org.yassineabou.playground.feature.chat.ui.ChatViewModel
import org.yassineabou.playground.feature.chat.ui.history.RecentChatHistoryContent
import org.yassineabou.playground.feature.chat.ui.history.SavedChatHistoryContent

@Composable
fun HistoryHorizontalPager(
    modifier: Modifier = Modifier,
    chatViewModel: ChatViewModel
) {
    val pagerState = rememberPagerState(pageCount = { 2 })
    val selectedTabIndex = remember { derivedStateOf { pagerState.currentPage } }
    Surface(
        color = MaterialTheme.colorScheme.surface
    ) {
        Column(
            modifier = modifier
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                HistoryTabRows(
                    selectedTabIndex = selectedTabIndex,
                    pagerState = pagerState
                )

                AIProvidersFilterMenu(chatViewModel = chatViewModel)
            }

            HistoryPagerContent(
                pagerState = pagerState,
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
    pagerState: PagerState
) {
    HorizontalPager(state = pagerState) { index ->
        when (index) {
            0 -> RecentChatHistoryContent()
            1 -> SavedChatHistoryContent()
        }
    }
}
