package screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow

class ScreenA : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        ScreenA(
            onButtonClick =  navigator:: navigateToScreenB
        )
    }

}

fun Navigator.navigateToScreenB() {
    push(
        ScreenB()
    )
}

@Composable
fun ScreenA(
    onButtonClick: () -> Unit
) {
    Surface {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                style = MaterialTheme.typography.headlineLarge,
                text = "Screen A"
            )
            Button(onClick = onButtonClick) {
                Text(
                    style = MaterialTheme.typography.bodyLarge,
                    text = "Navigate to Screen B",
                )
            }
        }
    }
}