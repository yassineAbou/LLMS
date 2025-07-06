package org.yassineabou.llms.feature.you

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.yassineabou.llms.feature.you.auth.ForgotPasswordAuth
import org.yassineabou.llms.feature.you.auth.LoginAuth
import org.yassineabou.llms.feature.you.auth.RegistrationAuth
import org.yassineabou.llms.feature.you.model.AuthScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthBottomSheet(
    youViewModel: YouViewModel,
    currentAuthScreen: AuthScreen,
) {
    val sheetState: SheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    ModalBottomSheet(
        onDismissRequest = { youViewModel.onShowAuthSheet(false) },
        sheetState = sheetState
    ) {

        // Animated transitions between screens
        AnimatedContent(
            targetState = currentAuthScreen,
            transitionSpec = {
                // Determine animation direction based on screen order
                val direction = when {
                    targetState.order > initialState.order -> AnimatedContentTransitionScope.SlideDirection.Left
                    else -> AnimatedContentTransitionScope.SlideDirection.Right
                }
                // Define animation with slide + fade effects
                slideIntoContainer(direction) togetherWith slideOutOfContainer(direction)
            },
            modifier = Modifier.fillMaxWidth()
        ) { targetScreen ->
            when (targetScreen) {
                AuthScreen.Login -> LoginAuth(
                    onLogin = { youViewModel.onLogin() },
                    onForgotPassword = { youViewModel.navigateTo(AuthScreen.ForgotPassword) },
                    onRegister = { youViewModel.navigateTo(AuthScreen.Register) },
                    onDismiss = { youViewModel.onShowAuthSheet(false) }
                )
                AuthScreen.Register -> RegistrationAuth(
                    onRegister = { youViewModel.onLogin() },
                    onBackPressed = { youViewModel.navigateTo(AuthScreen.Login) },
                    onDismiss = { youViewModel.onShowAuthSheet(false) },
                )
                AuthScreen.ForgotPassword -> ForgotPasswordAuth(
                    onReset = { youViewModel.navigateTo(AuthScreen.Login) },
                    onDismiss = { youViewModel.onShowAuthSheet(false) },
                )
            }
        }
    }
}
