package org.yassineabou.llms.feature.imagine.ui.supportingPane

import androidx.navigation3.runtime.NavKey
import org.yassineabou.llms.app.core.navigation.Navigator

object PaneOrScreenNavigator {

    fun navigateTo(
        supportingPaneNavigator: SupportingPaneNavigator,
        navigator: Navigator,
        isLargeScreen: Boolean,
        paneDestination: SupportingPaneScreen,
        screenRoute: NavKey
    ) {
        if (isLargeScreen) {
            supportingPaneNavigator.navigate(paneDestination)
        } else {
            navigator.navigate(screenRoute)
        }
    }
}