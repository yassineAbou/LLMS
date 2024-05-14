import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    val icon = painterResource("drawable/icon.png")
    Window(
        onCloseRequest = ::exitApplication,
        title = "LLMS",
        icon = icon
    ) {
        App()
    }
}