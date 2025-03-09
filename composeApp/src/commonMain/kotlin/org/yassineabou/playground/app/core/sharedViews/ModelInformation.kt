package org.yassineabou.playground.app.core.sharedViews

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.yassineabou.playground.app.core.theme.colorSchemeCustom
import org.yassineabou.playground.feature.chat.model.TextModel
import org.yassineabou.playground.feature.imagine.model.ImageModel
import org.yassineabou.playground.feature.imagine.ui.view.ImageExamplesCarousel
import kotlin.time.Duration.Companion.seconds

@Composable
fun ModelInformation(
    textModel: TextModel? = null,
    imageModel: ImageModel? = null,
    modifier: Modifier = Modifier,
    onDismissRequest: (Boolean) -> Unit,
) {
    Box(
        modifier = modifier
    ) {
        ModelContent(
            imageModel = imageModel,
            textModel = textModel
        )
        GotItButton(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 32.dp)
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            onDismissRequest = onDismissRequest
        )

    }
}

@Composable
private fun ModelContent(
    imageModel: ImageModel?,
    textModel: TextModel?
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        when {
            imageModel?.urlExamples?.isNotEmpty() == true -> {
                ImageCarouselWithOverlay(imageModel)
            }
            textModel?.image != null -> {
                TextModelImagePreview(textModel.image)
            }
        }

        if (imageModel == null) {
            TextModelMetadata(textModel)
        }
    }
}

@Composable
private fun ImageCarouselWithOverlay(imageModel: ImageModel) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.medium)
    ) {
        ImageExamplesCarousel(
            imageUrlExamples = imageModel.urlExamples,
            delayTime = 2.seconds
        )

        ImageCarouselTextOverlay(
            title = imageModel.title,
            description = imageModel.description,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(16.dp)
        )
    }
}

@Composable
private fun ImageCarouselTextOverlay(
    title: String,
    description: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.displaySmall,
            color = MaterialTheme.colorSchemeCustom.alwaysWhite,
            fontWeight = FontWeight.Medium
        )
        Text(
            text = description,
            modifier = Modifier.padding(top = 8.dp),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorSchemeCustom.alwaysWhite,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
private fun TextModelImagePreview(imageRes: DrawableResource) {
    Image(
        painter = painterResource(imageRes),
        contentDescription = "Model Preview Image",
        modifier = Modifier.size(65.dp),
        contentScale = ContentScale.Fit
    )
}

@Composable
private fun TextModelMetadata(textModel: TextModel?) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        textModel?.title?.let { modelTitle ->
            Text(
                text = modelTitle,
                style = MaterialTheme.typography.displaySmall,
                fontWeight = FontWeight.Medium
            )
        }

        textModel?.description?.let { modelDescription ->
            Text(
                text = modelDescription,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium
            )
        }
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

@Composable
fun InfoIconButton(
    modifier: Modifier = Modifier,
    onInfoClick: () -> Unit
) {
    IconButton(
        modifier = modifier,
        onClick = onInfoClick
    ) {
        Icon(
            imageVector = Icons.TwoTone.Info,
            contentDescription = "info",
        )
    }
}



