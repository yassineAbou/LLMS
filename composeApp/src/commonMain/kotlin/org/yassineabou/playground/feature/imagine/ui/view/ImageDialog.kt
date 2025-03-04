@file:OptIn(ExperimentalMaterial3Api::class)

package org.yassineabou.playground.feature.imagine.ui.view


import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import org.yassineabou.playground.app.core.theme.colorSchemeCustom
import org.yassineabou.playground.app.core.sharedViews.PyramidTextFormat
import org.yassineabou.playground.feature.imagine.model.UrlExample

@Composable
fun ImageDialogContent(
    urlExample: UrlExample,
    onIdeaTextChange: (String) -> Unit,
    onDismiss: () -> Unit,
) {
    val scrollState = rememberScrollState(0)
    Box {
        AsyncImage(
            model = urlExample.url,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize(),
            contentDescription = "dialog image background"
        )
        CloseButton(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(8.dp),
            onDismiss = onDismiss
        )

        DescriptionWithTryButton(
            description = urlExample.description,
            scrollState = scrollState,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp),
            onIdeaTextChange = onIdeaTextChange,
            onDismiss = onDismiss
        )
    }
}

@Composable
fun CloseButton(
    modifier: Modifier,
    onDismiss: () -> Unit
) {
    IconButton(
        modifier = modifier,
        onClick = onDismiss
    ) {
        Icon(
            modifier = Modifier
                .clip(CircleShape)
                .background(MaterialTheme.colorSchemeCustom.alwaysWhite)
                .padding(5.dp),
            imageVector = Icons.Default.Close,
            tint = MaterialTheme.colorSchemeCustom.alwaysBlack,
            contentDescription = "Close",
        )
    }
}

@Composable
fun DescriptionWithTryButton(
    description: String,
    scrollState: ScrollState,
    modifier: Modifier = Modifier,
    onIdeaTextChange: (String) -> Unit,
    onDismiss: () -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Box(modifier = Modifier.height(100.dp)) {
            PyramidTextFormat(
                text = description,
                color = MaterialTheme.colorSchemeCustom.alwaysWhite,
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.verticalScroll(scrollState)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorSchemeCustom.alwaysWhite,
                contentColor = MaterialTheme.colorSchemeCustom.alwaysBlack
            ),
            onClick = {
                onIdeaTextChange("")
                onIdeaTextChange(description)
                onDismiss()
            }
        ) {
            Text("Try this")
        }

    }
}