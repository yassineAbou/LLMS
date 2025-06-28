package org.yassineabou.llms.app.core.sharedViews

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import llms.composeapp.generated.resources.Res
import llms.composeapp.generated.resources.linux
import org.jetbrains.compose.resources.painterResource
import org.yassineabou.llms.app.core.theme.colorSchemeCustom

@Composable
fun LoadingContent(
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Image(
            painter = painterResource(Res.drawable.linux),
            contentDescription = null,
            modifier = Modifier
                .padding(bottom = 32.dp)
                .size(40.dp)
                .alpha(0.9f)
        )

        Text(
            text = "Loading...",
            fontSize = 18.sp,
            color = MaterialTheme.colorSchemeCustom.alwaysOrange,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            text = "This is a learning project with free models. Response may take a few seconds",
            fontSize = 15.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 24.dp, start = 12.dp, end = 12.dp)
        )

        BlockLoader(
            modifier = Modifier
                .padding(horizontal = 32.dp)
                .shadow(8.dp, shape = RectangleShape)
        )
    }
}