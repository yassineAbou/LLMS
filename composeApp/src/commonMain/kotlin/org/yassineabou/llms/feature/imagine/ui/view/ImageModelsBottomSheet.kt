@file:OptIn(ExperimentalMaterial3Api::class)

package org.yassineabou.llms.feature.imagine.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.yassineabou.llms.app.core.sharedViews.AppLoadingIndicator
import org.yassineabou.llms.app.core.sharedViews.BottomSheetContent
import org.yassineabou.llms.app.core.sharedViews.ModelTypeActionButtons
import org.yassineabou.llms.app.core.sharedViews.ModelsErrorState
import org.yassineabou.llms.app.core.sharedViews.ModelsGrid
import org.yassineabou.llms.feature.imagine.ui.ImagineViewModel

@Composable
fun ImageModelsBottomSheet(
    imagineViewModel: ImagineViewModel,
    onDismissRequest: () -> Unit,
    onAuthenticated: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val tempSelectedImageModel by imagineViewModel.tempSelectedImageModel.collectAsState()

    val availableModels by imagineViewModel.availableImageModels.collectAsState()
    val isLoadingModels by imagineViewModel.isLoadingModels.collectAsState()
    val modelsLoadError by imagineViewModel.modelsLoadError.collectAsState()

    LaunchedEffect(Unit) {
        imagineViewModel.setTempSelectedToSelected()
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
                        imagineViewModel.confirmSelectedImageModel()
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
                        onRetry = { imagineViewModel.loadImageModels() }
                    )
                    else -> ModelsGrid(
                        models = availableModels,
                        selectedModel = tempSelectedImageModel,
                        onModelSelected = { imagineViewModel.selectTempImageModel(it) },
                        modelTitle = { it.title },
                        modelKey = { it.modelName }
                    )
                }
            }
        )
    }
}