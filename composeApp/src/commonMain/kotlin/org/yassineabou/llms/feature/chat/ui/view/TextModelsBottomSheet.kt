@file:OptIn(ExperimentalMaterial3Api::class)


package org.yassineabou.llms.feature.chat.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.yassineabou.llms.app.core.sharedViews.AppLoadingIndicator
import org.yassineabou.llms.app.core.sharedViews.BottomSheetContent
import org.yassineabou.llms.app.core.sharedViews.ModelItem
import org.yassineabou.llms.app.core.sharedViews.ModelTypeActionButtons
import org.yassineabou.llms.app.core.util.ModelGridUtils.getColumnCount
import org.yassineabou.llms.feature.chat.data.model.TextModel
import org.yassineabou.llms.feature.chat.ui.ChatViewModel

@Composable
fun TextModelsBottomSheet(
    chatViewModel: ChatViewModel,
    onDismissRequest: () -> Unit,
    onAuthenticated: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val tempSelectedTextModel by chatViewModel.tempSelectedTextModel.collectAsState()
    val availableModels by chatViewModel.availableTextModels.collectAsState()
    val isLoadingModels by chatViewModel.isLoadingModels.collectAsState()
    val modelsLoadError by chatViewModel.modelsLoadError.collectAsState()

    LaunchedEffect(Unit) {
        chatViewModel.setTempSelectedToSelected()
    }

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState
    ) {
        BottomSheetContent(
            title = {
                ModelTypeActionButtons(
                    onDismissRequest = onDismissRequest,
                    onDone = {
                        chatViewModel.handleModelSelectionChange()
                        onAuthenticated()
                    }
                )
            },
            body = {
                TextModelsContent(
                    isLoading = isLoadingModels,
                    error = modelsLoadError,
                    models = availableModels,
                    selectedModelName = tempSelectedTextModel.modelName,
                    onModelSelected = { chatViewModel.selectTempTextModel(it) },
                    onRetry = { chatViewModel.loadTextModels() }
                )
            }
        )
    }
}

@Composable
private fun TextModelsContent(
    isLoading: Boolean,
    error: String?,
    models: List<TextModel>,
    selectedModelName: String,
    onModelSelected: (TextModel) -> Unit,
    onRetry: () -> Unit
) {
    when {
        isLoading -> AppLoadingIndicator(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
        )
        error != null && models.isEmpty() -> ModelsErrorState(
            errorMessage = error,
            onRetry = onRetry
        )
        else -> ModelsGrid(
            models = models,
            selectedModelName = selectedModelName,
            onModelSelected = onModelSelected
        )
    }
}

@Composable
private fun ModelsErrorState(
    errorMessage: String,
    onRetry: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Failed to load models",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.error
        )
        Text(
            text = errorMessage,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Button(onClick = onRetry) {
            Text("Retry")
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ModelsGrid(
    models: List<TextModel>,
    selectedModelName: String,
    onModelSelected: (TextModel) -> Unit
) {
    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
    val columns = getColumnCount(windowSizeClass)

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        contentPadding = PaddingValues(
            start = 16.dp,
            end = 16.dp,
            top = 16.dp,
            bottom = 24.dp
        )
    ) {
        item {
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                maxItemsInEachRow = columns
            ) {
                models.forEach { model ->
                    ModelItem(
                        title = model.title,
                        efficiency = null,
                        isSelected = model.modelName == selectedModelName,
                        onSelected = { onModelSelected(model) },
                        modifier = Modifier.weight(1f)
                    )
                }

                val remainder = models.size % columns
                if (remainder != 0) {
                    repeat(columns - remainder) {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}



