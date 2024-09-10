@file:OptIn(ExperimentalFoundationApi::class)

package org.yassineabou.playground.feature.imageGen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
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
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import org.yassineabou.playground.app.ui.navigation.Screen
import org.yassineabou.playground.app.ui.navigation.imageGenTabRows


@Composable
fun ImageGenHorizontalPager(navController: NavController) {
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

            ImageGenContent(
                pagerState = pagerState,
                navigateToImage = { navController.navigate(Screen.FullScreenImage.route) }
            )
        }
    }
}

@Composable
fun ImageGenContent(pagerState: PagerState, navigateToImage: () -> Unit) {
    HorizontalPager(state = pagerState) { index ->
        when (index) {
            0 -> CreateImageScreen()
            1 -> GridImagesScreen(
                navigateToImage = navigateToImage
            )
        }
    }
}