package org.yassineabou.playground.feature.Imagine.supportingPane

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember

class SupportingPaneNavigator(initialScreen: SupportingPaneScreen) {
    private val _backStack = mutableStateListOf(initialScreen)
    private var _isForward = true // Track navigation direction

    val currentScreen: SupportingPaneScreen
        get() = _backStack.last()

    val isForward: Boolean
        get() = _isForward

    fun navigate(screen: SupportingPaneScreen) {
        _isForward = true // Forward navigation
        _backStack.add(screen)
    }

    fun popBackStack(): Boolean {
        if (_backStack.size > 1) {
            _isForward = false // Backward navigation
            _backStack.removeAt(_backStack.size - 1)
            return true
        }
        return false
    }
}

@Composable
fun rememberSupportingPaneNavigator(
    initialScreen: SupportingPaneScreen = SupportingPaneScreen.GeneratedImages
): SupportingPaneNavigator {
    return remember { SupportingPaneNavigator(initialScreen) }
}