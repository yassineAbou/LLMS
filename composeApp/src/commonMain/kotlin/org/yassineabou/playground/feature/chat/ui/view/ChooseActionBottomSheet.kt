package org.yassineabou.playground.feature.chat.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.yassineabou.playground.app.core.theme.colorSchemeCustom
import org.yassineabou.playground.app.core.sharedViews.BottomSheetButton
import org.yassineabou.playground.app.core.sharedViews.BottomSheetContent
import org.yassineabou.playground.app.core.sharedViews.BottomSheetTitle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChooseActionBottomSheet(
    onDismissRequest: () -> Unit,
    onActionSelected: () -> Unit
) {
    val sheetState: SheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState
    ) {

        BottomSheetContent(
            title = {
                BottomSheetTitle(
                    text = "Choose Action",
                    style = MaterialTheme.typography.headlineSmall
                )
            },
            body = {
                ActionsButtons(onActionSelected = onActionSelected)
            }
        )
    }

}


@Composable
fun ActionsButtons(onActionSelected: () -> Unit) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.padding(16.dp)
    ) {
        BottomSheetButton(
            text = "Open Camera",
            icon = Icons.Filled.PhotoCamera,
            style = MaterialTheme.typography.titleMedium,
            containerColor = MaterialTheme.colorSchemeCustom.askAnythingBgColor,
            contentColor = MaterialTheme.colorScheme.onBackground,
            onAuthenticated = onActionSelected
        )
        BottomSheetButton(
            text = "Go to Gallery",
            icon = Icons.Filled.Image,
            style = MaterialTheme.typography.titleMedium,
            containerColor = MaterialTheme.colorSchemeCustom.askAnythingBgColor,
            contentColor = MaterialTheme.colorScheme.onBackground,
            onAuthenticated = onActionSelected
        )
    }
}
