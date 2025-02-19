package org.yassineabou.playground.app.core.sharedViews

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import org.yassineabou.playground.feature.imagine.model.ImageModel
import org.yassineabou.playground.feature.imagine.ui.view.ImageCarousel
import org.yassineabou.playground.feature.chat.model.TextModel
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
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            // Display Image Carousel if imageModel is provided
            if (imageModel != null && imageModel.urlExamples.isNotEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                ) {
                    ImageCarousel(
                        imageUrlExamples = imageModel.urlExamples,
                        delayTime = 2.seconds
                    )

                    // Overlay Title and Description on top of the image
                    Column(
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                            .padding(16.dp)
                    ) {
                        Text(
                            text = imageModel.title,
                            color = Color.White,
                            fontWeight = FontWeight.Medium,
                            style = MaterialTheme.typography.displaySmall
                        )
                        Text(
                            text = imageModel.description,
                            modifier = Modifier.padding(top = 16.dp),
                            color = Color.White,
                            fontWeight = FontWeight.Medium,
                        )
                    }
                }
            }

            // Display Image from textModel if no imageModel is provided
            if (textModel?.image != null && imageModel == null) {
                Image(
                    painter = painterResource(textModel.image),
                    contentDescription = "Text Model",
                    modifier = Modifier.size(65.dp)
                )
            }

            // Display Title and Description for textModel (if no imageModel is provided)
            if (imageModel == null) {
                val title = textModel?.title
                if (title != null) {
                    Text(
                        text = title,
                        fontWeight = FontWeight.Medium,
                        style = MaterialTheme.typography.displaySmall
                    )
                }

                val description = textModel?.description
                if (description != null) {
                    Text(
                        text = description,
                        fontWeight = FontWeight.Medium,
                    )
                }
            }
        }

        // Got It Button (always at the bottom)
        GotItButton(
            modifier = Modifier
                .align(Alignment.BottomCenter) // Center the button at the bottom
                .padding(bottom = 32.dp)
                .fillMaxWidth()
                .padding(horizontal = 16.dp), // Add horizontal padding
            onDismissRequest = onDismissRequest
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



