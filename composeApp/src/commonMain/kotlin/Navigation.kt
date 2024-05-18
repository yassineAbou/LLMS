import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator


@Composable
fun Navigation(
    screen: Screen
) {
     Navigator(
         screen = screen,
     ) { navigator ->
         CurrentScreen()
     }
}