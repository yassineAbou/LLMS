package org.yassineabou.playground.feature.chat.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.outlined.History
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import org.yassineabou.playground.app.ui.navigation.Screen
import org.yassineabou.playground.app.ui.theme.colorSchemeCustom
import org.yassineabou.playground.feature.chat.ui.view.TextGenTypesBottomSheet


@Composable
fun ChatScreen(
    navController: NavController,
    textGenViewModel: ChatViewModel = koinViewModel(),

    ) {
    val selectedTextModel by textGenViewModel.selectedTextModel.collectAsState()
    var selectModelClicked by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        ChatAppBar(
            title = selectedTextModel.title,
            image = selectedTextModel.image,
            modifier = Modifier
                .statusBarsPadding()
                .fillMaxWidth()
                 .padding(8.dp),
            onClickHistory = { navController.navigate(Screen.ChatHistoryScreen.route) },
            onSelect = { selectModelClicked = true }
        )
        if (selectModelClicked) {
            TextGenTypesBottomSheet(
                onDismissRequest = { selectModelClicked = false },
                onAuthenticated = { selectModelClicked = false }
            )
        }
    }
}

@Composable
private fun ChatAppBar(
    title: String,
    image: DrawableResource,
    modifier: Modifier = Modifier,
    onClickHistory: () -> Unit,
    onSelect : () -> Unit
) {
    Box(
        modifier = modifier
    ) {
        HistoryIconButton(
            modifier = Modifier.align(Alignment.TopStart),
            onClick = onClickHistory
        )
        SelectedTextModel(
            title = title,
            image = image,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(8.dp)
                .clickable { onSelect() }
                .background(color = MaterialTheme.colorSchemeCustom.alwaysBlue, shape = RoundedCornerShape(8.dp))
                .padding(6.dp)

        )

    }
}

@Composable
private fun HistoryIconButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    IconButton(
        modifier = modifier,
        onClick = onClick
    ) {
        Icon(
            imageVector = Icons.Outlined.History,
            modifier = Modifier.size(35.dp),
            contentDescription = "History"
        )
    }
}

@Composable
private fun SelectedTextModel(
    title: String,
    image: DrawableResource,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(image),
            contentDescription = "Text Model",
            modifier = Modifier.size(25.dp)
        )
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorSchemeCustom.alwaysWhite
        )
        Icon(
            imageVector = Icons.Filled.KeyboardArrowDown,
            contentDescription = "Select text model",
            tint = MaterialTheme.colorSchemeCustom.alwaysWhite
        )
    }
}