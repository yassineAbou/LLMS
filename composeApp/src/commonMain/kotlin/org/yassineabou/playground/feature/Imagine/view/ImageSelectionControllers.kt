package org.yassineabou.playground.feature.Imagine.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.CopyAll
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.yassineabou.playground.app.ui.theme.colorSchemeCustom


@Composable
fun ImageSelectionControls(
    selectedPhotoCount: Int,
    disableSelectionMode: () -> Unit,
    onDownloadClick: () -> Unit,
    onShareClick: () -> Unit,
    onDeleteClick: () -> Unit,
    onSelectAllClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        CloseButton(
            selectedPhotoCount = selectedPhotoCount,
            disableSelectionMode = disableSelectionMode
        )
        ActionButtonsRow(
            onDownloadClick = onDownloadClick,
            onShareClick = onShareClick,
            onDeleteClick = onDeleteClick,
            onSelectAllClick = onSelectAllClick
        )
    }
}

@Composable
fun CloseButton(
    disableSelectionMode: () -> Unit,
    selectedPhotoCount: Int
) {
    Surface(
        shape = RoundedCornerShape(24.dp),
        color = MaterialTheme.colorSchemeCustom.alwaysBlue,
        shadowElevation = 4.dp,
        modifier = Modifier
            .padding(12.dp)
            .clickable(onClick = disableSelectionMode)
    ) {
        Row(
            modifier = Modifier.padding(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.size(30.dp),
                imageVector = Icons.Filled.Close,
                tint = MaterialTheme.colorSchemeCustom.alwaysWhite,
                contentDescription = "Close"
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = if (selectedPhotoCount > 0) "$selectedPhotoCount" else "  ",
                color = MaterialTheme.colorSchemeCustom.alwaysWhite,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(end = 8.dp)
            )
        }

    }
}


@Composable
private fun ActionButtonsRow(
    onDownloadClick: () -> Unit,
    onShareClick: () -> Unit,
    onDeleteClick: () -> Unit,
    onSelectAllClick: () -> Unit
) {
    Row(
        modifier = Modifier.padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.End
    ) {
        IconButton(onClick = onDownloadClick) {
            Icon(imageVector = Icons.Filled.Download, contentDescription = "Download")
        }

        IconButton(onClick = onShareClick) {
            Icon(imageVector = Icons.Filled.Share, contentDescription = "Share")
        }

        IconButton(onClick = onDeleteClick) {
            Icon(imageVector = Icons.Filled.Delete, contentDescription = "Delete")
        }

        IconButton(onClick = onSelectAllClick) {
            Icon(imageVector = Icons.Filled.CopyAll, contentDescription = "SelectAll")
        }
    }
}