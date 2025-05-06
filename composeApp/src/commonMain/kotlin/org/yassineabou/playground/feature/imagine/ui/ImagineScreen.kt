package org.yassineabou.playground.feature.imagine.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
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
import com.github.panpf.sketch.AsyncImage
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.DrawableResource
import org.yassineabou.playground.app.core.data.GenerationState
import org.yassineabou.playground.app.core.navigation.Screen
import org.yassineabou.playground.app.core.sharedViews.CustomIconButton
import org.yassineabou.playground.app.core.sharedViews.GoToFirst
import org.yassineabou.playground.app.core.sharedViews.SelectedModel
import org.yassineabou.playground.app.core.theme.colorSchemeCustom
import org.yassineabou.playground.app.core.util.PaneOrScreenNavigator
import org.yassineabou.playground.app.core.util.draggableScrollModifier
import org.yassineabou.playground.feature.imagine.model.UrlExample
import org.yassineabou.playground.feature.imagine.ui.supportingPane.SupportingPaneNavigator
import org.yassineabou.playground.feature.imagine.ui.supportingPane.SupportingPaneScreen
import org.yassineabou.playground.feature.imagine.ui.util.rememberIsLargeScreen
import org.yassineabou.playground.feature.imagine.ui.view.DropDownDialog
import org.yassineabou.playground.feature.imagine.ui.view.ImageDialogContent
import org.yassineabou.playground.feature.imagine.ui.view.ImageModelsBottomSheet


@Composable
fun ImagineScreen(
    navController: NavController,
    imageGenViewModel: ImageGenViewModel,
    supportingPaneNavigator: SupportingPaneNavigator,
    shouldShowSupportingPaneButton: Boolean,
    modifier: Modifier = Modifier
) {
    var ideaText by remember { mutableStateOf("") }
    var selectModelClicked by remember { mutableStateOf(false) }
    val selectedImageModel by imageGenViewModel.selectedImageModel.collectAsStateWithLifecycle()
    val imageGenerationState by imageGenViewModel.imageGenerationState.collectAsStateWithLifecycle()
    val loadedInspiration by imageGenViewModel.loadedInspiration.collectAsStateWithLifecycle()
    val isLargeScreen = rememberIsLargeScreen()

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        if (shouldShowSupportingPaneButton) {
            ImagineAppBar(
                title = selectedImageModel.title,
                image = selectedImageModel.image,
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
                    image = selectedImageModel.image,
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
            loadNextPage = { imageGenViewModel.loadNextInspirationPage() },
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
                    paneDestination = SupportingPaneScreen.ImageCreationTimer,
                    screenRoute = Screen.ImageCreationTimerScreen.route
                )
                imageGenViewModel.generateImage(ideaText)
            }
        )
        if (selectModelClicked) {
            ImageModelsBottomSheet(
                imageGenViewModel = imageGenViewModel,
                onDismissRequest = { selectModelClicked = false },
                onAuthenticated = { selectModelClicked = false }
            )
        }
    }
}

@Composable
private fun ImagineAppBar(
    title: String,
    image: DrawableResource,
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
            image = image,
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
            keyboardOptions = KeyboardOptions(
                imeAction = if (ideaText.isNotEmpty()) ImeAction.Done else ImeAction.Default
            ),
            keyboardActions = KeyboardActions(
                onDone = { focusManager.clearFocus() }
            ),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Transparent, // Hide the default border
                unfocusedBorderColor = Color.Transparent, // Hide the default border
                cursorColor = MaterialTheme.colorSchemeCustom.alwaysBlue, // Optional: Set cursor color to blue
                focusedLabelColor = MaterialTheme.colorSchemeCustom.alwaysBlue, // Optional: Set label color to blue when focused
                unfocusedLabelColor = MaterialTheme.colorSchemeCustom.alwaysBlue.copy(alpha = 0.5f) // Optional: Set label color to semi-transparent blue when unfocused
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


@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
private fun InspirationItem(
    url: String,
    onItemClick: () -> Unit
) {
    val windowSizeClass = calculateWindowSizeClass()
    val imageWidth = when (windowSizeClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> 125.dp
        WindowWidthSizeClass.Medium -> 175.dp
        WindowWidthSizeClass.Expanded -> 225.dp
        else -> 125.dp
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
                color = MaterialTheme.colorSchemeCustom.alwaysWhite
            )
        }
    }
}

