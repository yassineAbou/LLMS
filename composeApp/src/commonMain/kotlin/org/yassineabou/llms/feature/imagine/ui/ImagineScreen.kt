package org.yassineabou.llms.feature.imagine.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.outlined.GridView
import androidx.compose.material3.*
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.window.core.layout.WindowSizeClass
import com.github.panpf.sketch.AsyncImage
import kotlinx.coroutines.launch
import org.yassineabou.llms.app.core.data.remote.ai.GenerationState
import org.yassineabou.llms.app.core.navigation.Screen
import org.yassineabou.llms.app.core.sharedViews.CustomIconButton
import org.yassineabou.llms.app.core.sharedViews.GoToFirst
import org.yassineabou.llms.app.core.sharedViews.SelectedModel
import org.yassineabou.llms.app.core.theme.colorSchemeCustom
import org.yassineabou.llms.app.core.util.PaneOrScreenNavigator
import org.yassineabou.llms.app.core.util.draggableScrollModifier
import org.yassineabou.llms.feature.imagine.data.model.UrlExample
import org.yassineabou.llms.feature.imagine.ui.supportingPane.SupportingPaneNavigator
import org.yassineabou.llms.feature.imagine.ui.supportingPane.SupportingPaneScreen
import org.yassineabou.llms.feature.imagine.ui.util.rememberIsLargeScreen
import org.yassineabou.llms.feature.imagine.ui.view.DropDownDialog
import org.yassineabou.llms.feature.imagine.ui.view.ImageDialogContent
import org.yassineabou.llms.feature.imagine.ui.view.ImageModelsBottomSheet


@Composable
fun ImagineScreen(
    navController: NavController,
    imagineViewModel: ImagineViewModel,
    supportingPaneNavigator: SupportingPaneNavigator,
    shouldShowSupportingPaneButton: Boolean,
    modifier: Modifier = Modifier
) {
    var ideaText by remember { mutableStateOf("") }
    var selectModelClicked by remember { mutableStateOf(false) }
    val selectedImageModel by imagineViewModel.selectedImageModel.collectAsStateWithLifecycle()
    val imageGenerationState by imagineViewModel.imageGenerationState.collectAsStateWithLifecycle()
    val loadedInspiration by imagineViewModel.loadedInspiration.collectAsStateWithLifecycle()
    val isLargeScreen = rememberIsLargeScreen()

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        if (shouldShowSupportingPaneButton) {
            ImagineAppBar(
                title = selectedImageModel.title,
                modifier = Modifier
                    .weight(0.1f)
                    .fillMaxSize()
                    .align(alignment = Alignment.Start)
                    .padding(start = 8.dp),
                onClick = { navController.navigate(Screen.GeneratedImagesScreen.route)},
                onSelect = { selectModelClicked = true  }
            )
        } else {
            Box(
                modifier = Modifier
                    .weight(0.1f)
                    .fillMaxSize()
            ) {
                SelectedModel(
                    title = selectedImageModel.title,
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .padding(8.dp)
                        .clickable { selectModelClicked = true }
                        .background(
                            color = MaterialTheme.colorSchemeCustom.alwaysBlue.copy(alpha = 0.5f),
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(6.dp)
                )
            }
        }
        TypeIdeaForm(
            ideaText = ideaText,
            modifier = Modifier
                .weight(0.26F)
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 16.dp)
                .border(
                    width = 2.dp,
                    color = MaterialTheme.colorSchemeCustom.alwaysBlue,
                    shape = MaterialTheme.shapes.medium
                ),
            onIdeaTextChange = { ideaText = it },
        )
        Inspirations(
            loadedInspiration = loadedInspiration,
            onIdeaTextChange = { ideaText = it },
            loadNextPage = { imagineViewModel.loadNextInspirationPage() },
            modifier = Modifier
                .weight(0.38f)
                .fillMaxWidth(),
        )
        CreateImageButton(
            enabled = ideaText.isNotEmpty() && imageGenerationState !is GenerationState.Loading,
            modifier = Modifier
                .weight(0.1f)
                .width(400.dp)
                .padding(horizontal = 16.dp)
                .align(Alignment.CenterHorizontally),
            onGenerateClick = {
                PaneOrScreenNavigator.navigateTo(
                    supportingPaneNavigator = supportingPaneNavigator,
                    navController = navController,
                    isLargeScreen = isLargeScreen,
                    paneDestination = SupportingPaneScreen.ImageGenerationLoading,
                    screenRoute = Screen.ImageGenerationLoadingScreen.route
                )
                imagineViewModel.generateImage(ideaText)
            }
        )
        if (selectModelClicked) {
            ImageModelsBottomSheet(
                imagineViewModel = imagineViewModel,
                onDismissRequest = { selectModelClicked = false },
                onAuthenticated = { selectModelClicked = false }
            )
        }
    }
}

@Composable
private fun ImagineAppBar(
    title: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    onSelect: () -> Unit
) {
    Box(modifier = modifier) {

        CustomIconButton(
            icon = Icons.Outlined.GridView,
            contentDescription = "Generated images",
            modifier = Modifier.align(Alignment.TopStart),
            onClick = onClick,
        )

        SelectedModel(
            title = title,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(8.dp)
                .clickable { onSelect() }
                .background(
                    color = MaterialTheme.colorSchemeCustom.alwaysBlue.copy(alpha = 0.5f),
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(6.dp)
        )
    }
}

@Composable
private fun TypeIdeaForm(
    ideaText: String,
    modifier: Modifier = Modifier,
    onIdeaTextChange: (String) -> Unit,
) {
    val focusManager = LocalFocusManager.current
    Box(modifier = modifier) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = ideaText,
            placeholder = { Text(text = "Describe the text you want to create or choose from inspirations") },
            onValueChange = onIdeaTextChange,
            trailingIcon = {
                if (ideaText.isNotEmpty()) {
                    IconButton(onClick = { onIdeaTextChange("") }) {
                        Icon(Icons.Filled.Clear, contentDescription = "Clear Text")
                    }
                }
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done), // Static action
            keyboardActions = KeyboardActions(
                onDone = { focusManager.clearFocus() }
            ),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                cursorColor = MaterialTheme.colorSchemeCustom.alwaysBlue,
                focusedLabelColor = MaterialTheme.colorSchemeCustom.alwaysBlue,
                unfocusedLabelColor = MaterialTheme.colorSchemeCustom.alwaysBlue.copy(alpha = 0.5f)
            )
        )
    }
}

@Composable
private fun Inspirations(
    loadedInspiration: List<UrlExample>,
    loadNextPage: () -> Unit,
    onIdeaTextChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    var showImageDialog by remember { mutableStateOf(false) }
    var dialogUrlExample by remember { mutableStateOf(UrlExample()) }


    val shouldLoadMore by remember(scrollState, loadedInspiration) {
        derivedStateOf {
            val layoutInfo = scrollState.layoutInfo
            val visibleItems = layoutInfo.visibleItemsInfo

            if (loadedInspiration.isEmpty()) return@derivedStateOf false

            val lastVisibleIndex = visibleItems.lastOrNull()?.index ?: 0
            val totalItemsCount = loadedInspiration.size // Directly use the input list size

            // Trigger load when reaching last 2 items (pageSize = 5 in ViewModel)
            lastVisibleIndex >= totalItemsCount - 2 &&
                    !scrollState.isScrollInProgress
        }
    }

    LaunchedEffect(shouldLoadMore) {
        if (shouldLoadMore) {
            loadNextPage()
        }
    }


    Box(modifier = modifier) {
        Column {
            Text(
                text = "Inspirations",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            LazyRow(
                state = scrollState,
                modifier = Modifier
                    .padding(top = 8.dp)
                    .draggableScrollModifier(scrollState), // Apply the reusable drag modifier for desktop and wasm
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(
                    items = loadedInspiration,
                    key = { item -> item.id }
                ) { inspiration ->
                    InspirationItem(
                        url = inspiration.url,
                        onItemClick = {
                            dialogUrlExample = inspiration
                            showImageDialog = true
                        }
                    )
                }
            }
        }

        // Show "Go to First" button if not at the start
        if (scrollState.firstVisibleItemIndex > 0) {
            GoToFirst(
                modifier = Modifier
                    .padding(16.dp)
                    .size(37.dp)
                    .align(Alignment.BottomEnd),
                icon = Icons.AutoMirrored.Filled.ArrowBack,
                onClick = {
                    coroutineScope.launch {
                        scrollState.animateScrollToItem(0) // Scroll to the first item
                    }
                }
            )
        }

        // Show image dialog if triggered
        if (showImageDialog) {

            DropDownDialog(
                onDismissRequest = {
                    showImageDialog = false
                }
            ) {
                ImageDialogContent(
                    urlExample = dialogUrlExample,
                    onIdeaTextChange = onIdeaTextChange,
                    onDismiss = { showImageDialog = false }
                )
            }
        }
    }
}



@Composable
private fun InspirationItem(
    url: String,
    onItemClick: () -> Unit
) {
    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
    val imageWidth = when {
        windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_EXPANDED_LOWER_BOUND) -> 225.dp
        windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_MEDIUM_LOWER_BOUND) -> 175.dp
        else -> 125.dp // COMPACT (< 600 dp)
    }
    AsyncImage(
        uri = url,
        contentScale = ContentScale.FillBounds,
        modifier = Modifier
            .fillMaxHeight()
            .clip(RoundedCornerShape(16.dp))
            .width(imageWidth)
            .clickable { onItemClick() },
        contentDescription = "InspirationItem"
    )
}


@Composable
private fun CreateImageButton(
    enabled: Boolean,
    modifier: Modifier = Modifier,
    onGenerateClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
    ) {
        Button(
            enabled = enabled,
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorSchemeCustom.alwaysBlue),
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            contentPadding = PaddingValues(12.dp),
            onClick = onGenerateClick
        ) {
            Text(
                text = "Create",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White
            )
        }
    }
}

