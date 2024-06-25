import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import app.App
import llms.composeapp.generated.resources.Res
import llms.composeapp.generated.resources.linux
import org.jetbrains.compose.resources.painterResource

fun main() = application {

    Window(
        onCloseRequest = ::exitApplication,
        title = "LLMS",
        icon =  painterResource(resource = Res.drawable.linux),
    ) {
        App()
    }
}