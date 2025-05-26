package org.yassineabou.llms.app.core.util

import androidx.navigation.NavController
import org.yassineabou.llms.feature.imagine.ui.supportingPane.SupportingPaneNavigator
import org.yassineabou.llms.feature.imagine.ui.supportingPane.SupportingPaneScreen

object PaneOrScreenNavigator {

    fun navigateTo(
        supportingPaneNavigator: SupportingPaneNavigator,
        navController: NavController,
        isLargeScreen: Boolean,
        paneDestination: SupportingPaneScreen,
        screenRoute: String
    ) {
        if (isLargeScreen) {
            supportingPaneNavigator.navigate(paneDestination)
        } else {
            navController.navigate(screenRoute)
        }
    }
}