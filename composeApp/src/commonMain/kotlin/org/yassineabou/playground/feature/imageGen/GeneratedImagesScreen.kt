package org.yassineabou.playground.feature.imageGen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import llms.composeapp.generated.resources.Res
import llms.composeapp.generated.resources.eight
import llms.composeapp.generated.resources.eighteen
import llms.composeapp.generated.resources.eleven
import llms.composeapp.generated.resources.fifteen
import llms.composeapp.generated.resources.five
import llms.composeapp.generated.resources.four
import llms.composeapp.generated.resources.fourteen
import llms.composeapp.generated.resources.nine
import llms.composeapp.generated.resources.nineteen
import llms.composeapp.generated.resources.one
import llms.composeapp.generated.resources.seven
import llms.composeapp.generated.resources.seventeen
import llms.composeapp.generated.resources.six
import llms.composeapp.generated.resources.sixteen
import llms.composeapp.generated.resources.ten
import llms.composeapp.generated.resources.thirteen
import llms.composeapp.generated.resources.three
import llms.composeapp.generated.resources.twelve
import llms.composeapp.generated.resources.twenty
import llms.composeapp.generated.resources.two
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun GridImagesScreen(navigateToImage: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp)
    ) {
        val windowSizeClass = calculateWindowSizeClass()
        val columnCount = when (windowSizeClass.widthSizeClass) {
            WindowWidthSizeClass.Compact -> 2
            WindowWidthSizeClass.Medium -> 3
            WindowWidthSizeClass.Expanded -> 5
            else -> 2
        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(count = columnCount),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            content = {
                items(randomSizedPhotos) { photo ->
                    PhotoItem(
                        photo = photo,
                        onClick = navigateToImage
                    )
                }
            },
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun PhotoItem(
    photo: DrawableResource,
    onClick: () -> Unit
) {
    Image(
        painter = painterResource(photo),
        contentScale = ContentScale.FillBounds,
        contentDescription = null,
        modifier = Modifier
            .size(220.dp)
            .clickable { onClick() }
    )
}


val randomSizedPhotos = listOf(
    Res.drawable.one,
    Res.drawable.two,
    Res.drawable.three,
    Res.drawable.four,
    Res.drawable.five,
    Res.drawable.six,
    Res.drawable.seven,
    Res.drawable.eight,
    Res.drawable.nine,
    Res.drawable.ten,
    Res.drawable.eleven,
    Res.drawable.twelve,
    Res.drawable.thirteen,
    Res.drawable.fourteen,
    Res.drawable.fifteen,
    Res.drawable.sixteen,
    Res.drawable.seventeen,
    Res.drawable.eighteen,
    Res.drawable.nineteen,
    Res.drawable.twenty
)