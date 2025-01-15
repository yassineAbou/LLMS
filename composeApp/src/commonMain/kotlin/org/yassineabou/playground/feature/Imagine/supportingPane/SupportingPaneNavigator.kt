package org.yassineabou.playground.feature.Imagine.supportingPane

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember

class SupportingPaneNavigator(initialScreen: SupportingPaneScreen) {
    private val _backStack = mutableStateListOf(initialScreen)

    val currentScreen: SupportingPaneScreen
        get() = _backStack.last()

    fun navigate(screen: SupportingPaneScreen) {
        _backStack.add(screen)
    }

    fun popBackStack(): Boolean {
        if (_backStack.size > 1) {
            _backStack.removeAt(_backStack.size - 1)
            return true
        }
        return false
    }
}

@Composable
fun rememberSupportingPaneNavigator(initialScreen: SupportingPaneScreen = SupportingPaneScreen.GeneratedImages): SupportingPaneNavigator {
    return remember { SupportingPaneNavigator(initialScreen) }
}