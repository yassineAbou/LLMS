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
import org.yassineabou.llms.app.core.sharedViews.ModelsErrorState
import org.yassineabou.llms.app.core.sharedViews.ModelsGrid
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
                when {
                    isLoadingModels -> AppLoadingIndicator(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)

                    )
                    modelsLoadError != null && availableModels.isEmpty() -> ModelsErrorState(
                        errorMessage = modelsLoadError ?: "",
                        onRetry = { chatViewModel.loadTextModels() }
                    )
                    else -> ModelsGrid(
                        models = availableModels,
                        selectedModel = tempSelectedTextModel,
                        onModelSelected = { chatViewModel.selectTempTextModel(it) },
                        modelTitle = { it.title },
                        modelKey = { it.modelName }
                    )
                }
            }
        )
    }
}