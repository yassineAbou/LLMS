package org.yassineabou.llms.app.core.navigation

import androidx.navigation3.runtime.NavKey

class Navigator(val state: NavigationState) {


    fun navigate(route: NavKey) {

        if (route !in state.backStacks.keys) {
            state.backStacks[state.topLevelRoute]?.add(route)
            return
        }

        if (route != state.topLevelRoute) {
            state.topLevelRoute = route
            return
        }

        val stack = state.backStacks[route] ?: return
        while (stack.size > 1) {
            stack.removeLastOrNull()
        }
    }


    fun goBack() {
        val currentStack = state.backStacks[state.topLevelRoute]
            ?: error("Stack for ${state.topLevelRoute} not found")
        val currentRoute = currentStack.last()

        if (currentRoute == state.topLevelRoute) {

            state.topLevelRoute = state.startRoute
        } else {
            currentStack.removeLastOrNull()
        }
    }

    fun isNavigationBarVisible(): Boolean {
        val currentRoute = state.currentRoute ?: return true
        return currentRoute !in FULL_SCREEN_ROUTES
    }
}