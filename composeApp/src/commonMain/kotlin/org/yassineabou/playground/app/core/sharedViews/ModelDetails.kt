package org.yassineabou.playground.app.core.sharedViews

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource


@Composable
fun ModelDetails(
    image: DrawableResource,
    title: String,
    description: String,
    modifier: Modifier = Modifier,
    onDismissRequest: (Boolean) -> Unit,
) {
    Column(
        modifier = modifier
    ) {
        ModelContent(
            image = image,
            title = title,
            description = description,
        )
        GotItButton(
            modifier = Modifier
                .padding(bottom = 32.dp, top = 8.dp)
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            onDismissRequest = onDismissRequest
        )

    }
}

@Composable
private fun ModelContent(
    image: DrawableResource,
    title: String,
    description: String,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ) {
        Image(
            painter = painterResource(image),
            contentDescription = "Model Preview Image",
            modifier = Modifier.size(65.dp),
            contentScale = ContentScale.Fit
        )

        Text(
            text = title,
            style = MaterialTheme.typography.displaySmall,
            fontWeight = FontWeight.Medium
        )

        Text(
            text = description,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Medium
        )

    }
}

@Composable
private fun GotItButton(
    modifier: Modifier = Modifier,
    onDismissRequest: (Boolean) -> Unit
) {
    Button(
        modifier = modifier,
        onClick = { onDismissRequest(false) }
    ) {
        Text(
            text = "Got it!",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(vertical = 12.dp),
        )
    }
}




