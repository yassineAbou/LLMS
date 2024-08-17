@file:OptIn(ExperimentalFoundationApi::class)

package org.yassineabou.playground.feature.imageGen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import kotlinx.coroutines.launch
import org.yassineabou.playground.app.navigation.imageGenTabRows

object ImageGenHorizontalPager : Tab {
    private fun readResolve(): Any = ImageGenHorizontalPager
    override val options: TabOptions
        @Composable
        get() {
            val title = "ImageGen"
            val icon = rememberVectorPainter(Icons.Filled.Image)

            return remember {
                TabOptions(
                    index = 1u,
                    title = title,
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        ImageGenHorizontalPager()
    }
}


@Composable
private fun ImageGenHorizontalPager() {
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = { imageGenTabRows.size })
    val selectedTabIndex = remember { derivedStateOf { pagerState.currentPage } }
    Surface(
        color = MaterialTheme.colorScheme.surface
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            TabRow(
                selectedTabIndex = selectedTabIndex.value,
                modifier = Modifier.fillMaxWidth()
            ) {
                imageGenTabRows.forEachIndexed { index, currentTab ->
                    Tab(
                        selected = selectedTabIndex.value == index,
                        selectedContentColor = MaterialTheme.colorScheme.primary,
                        unselectedContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        },
                        text = { Text(text = currentTab.title) },
                        icon = {
                            Icon(
                                imageVector = if (selectedTabIndex.value == index)
                                    currentTab.selectedIcon else currentTab.unselectedIcon,
                                contentDescription = "ImageGen Tab Icon"
                            )
                        }
                    )
                }
            }

            ImageGenContent(pagerState = pagerState)
        }
    }
}

@Composable
fun ImageGenContent(pagerState: PagerState) {
    HorizontalPager(state = pagerState) { index ->
        when (index) {
            0 -> Text2ImageScreen()
            1 -> Image2ImageScreen()
            2 -> GridImagesScreen()
        }
    }
}