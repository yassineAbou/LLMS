package org.yassineabou.playground.feature.imageGen.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel
import org.yassineabou.playground.app.ui.navigation.Screen
import org.yassineabou.playground.app.ui.theme.colorSchemeCustom
import org.yassineabou.playground.app.ui.view.GoToFirst
import org.yassineabou.playground.feature.imageGen.model.ImageGenModelList
import org.yassineabou.playground.feature.imageGen.model.UrlExample
import org.yassineabou.playground.feature.imageGen.view.ImageDialog
import org.yassineabou.playground.feature.imageGen.view.ImageGenTypesBottomSheet

@Composable
fun CreateImageScreen(
    navController: NavController,
    imageGenViewModel: ImageGenViewModel = koinViewModel()
) {
    var ideaText by remember { mutableStateOf("") }
    var selectModelClicked by remember { mutableStateOf(false) }
    val selectedImageModel by imageGenViewModel.selectedImageModel.collectAsState()
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        TypeIdeaForm(
            ideaText = ideaText,
            modifier = Modifier
                .weight(0.28F)
                .fillMaxWidth(),
            onIdeaTextChange = { ideaText = it },
        )
        SelectImageModels(
            modelName = selectedImageModel.title,
            modifier = Modifier
                .weight(0.18f)
                .fillMaxWidth(),
            changeSelectModelClicked = { selectModelClicked = it }
        )
        Inspirations(
            listInspiration = ImageGenModelList.inspiration,
            modifier = Modifier
                .weight(0.42f)
                .fillMaxWidth(),
            onIdeaTextChange = { ideaText = it },
        )
        CreateImageButton(
            enabled = ideaText.isNotEmpty(),
            modifier = Modifier
                .weight(0.12f)
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            onGenerateClick = { navController.navigate(Screen.ImageProcessingScreen.route) }
        )
        if (selectModelClicked) {
            ImageGenTypesBottomSheet(
                onDismissRequest = { selectModelClicked = false },
                onAuthenticated = { selectModelClicked = false }
            )
        }
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
        Column {
            IdeaSelectionHeader(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.20f),
            )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 16.dp)
                    .weight(0.65f),
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
                )
            )

            AssistChip(
                label = {
                    Text(
                        text = "Advance",
                        color = MaterialTheme.colorScheme.background
                    )
                },
                colors = AssistChipDefaults.assistChipColors(containerColor = MaterialTheme.colorScheme.onBackground),
                modifier = Modifier
                    .weight(0.15f)
                    .align(Alignment.End)
                    .padding(end = 16.dp),
                onClick = {  },
            )
        }
    }
}



@Composable
private fun IdeaSelectionHeader(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = Modifier.padding(top = 10.dp, start = 16.dp),
            text = "Type your idea",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Composable
private fun SelectImageModels(
    modelName: String,
    modifier: Modifier = Modifier,
    changeSelectModelClicked: (Boolean) -> Unit,
) {
    Box(modifier = modifier) {
        Column {
            Text(
                text = "Choose your model",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
            ImageModel(
                modelName = modelName,
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .border(
                        width = (5.dp),
                        color = MaterialTheme.colorSchemeCustom.alwaysBlue,
                        shape = RoundedCornerShape(10)
                    )
                    .clickable { changeSelectModelClicked(true) }
            )
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
private fun ImageModel(
    modelName: String,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
    ) {
        Text(
            text = modelName,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(vertical = 16.dp, horizontal = 16.dp)
        )
        Icon(
            imageVector = Icons.Filled.KeyboardArrowDown,
            contentDescription = "",
            modifier = Modifier
                .padding(vertical = 12.dp, horizontal = 16.dp)
                .size(35.dp)
        )
    }
}


@Composable
private fun Inspirations(
    listInspiration: List<UrlExample>,
    onIdeaTextChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    var showImageDialog by remember { mutableStateOf(false) }
    var dialogUrlExample by remember { mutableStateOf(UrlExample()) }
    Box(
        modifier = modifier
    ) {
        Column {

            Text(
                text = "Inspirations",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            LazyRow(
                state = scrollState,
                modifier = Modifier.padding(top = 8.dp),
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
               items(listInspiration) { inspiration ->
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

        if (scrollState.firstVisibleItemIndex > 0 ){
            GoToFirst(
                modifier = Modifier
                    .padding(16.dp)
                    .size(37.dp)
                    .align(Alignment.BottomEnd),
                icon = Icons.AutoMirrored.Filled.ArrowBack,
                onClick = {
                    coroutineScope.launch {
                        scrollState.animateScrollToItem(0)
                    }
                }
            )
        }

        if (showImageDialog) {
            ImageDialog(
                urlExample = dialogUrlExample,
                modifier = Modifier
                    .size(400.dp)
                    .align(Alignment.Center)
                    .clip(RoundedCornerShape(16.dp)),
                onIdeaTextChange = onIdeaTextChange,
                onDismiss = { showImageDialog = false }
            )
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
    KamelImage(
        resource = { asyncPainterResource(data = url) },
        contentScale = ContentScale.FillBounds,
        modifier = Modifier
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

