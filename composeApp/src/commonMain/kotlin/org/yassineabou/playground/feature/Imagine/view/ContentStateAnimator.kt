package org.yassineabou.playground.feature.Imagine.view

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.yassineabou.playground.feature.chat.model.ChatHistory

@Composable
fun ContentStateAnimator(
    contentList: List<ChatHistory>,
    contentComposable: @Composable (List<ChatHistory>) -> Unit,
    navigateToChatScreen: () -> Unit
) {
    AnimatedContent(
        targetState = contentList.isNotEmpty(),
        transitionSpec = {
            // Define how the content should enter and exit
            (slideInVertically { height -> height } + fadeIn()).togetherWith(
                slideOutVertically { height -> -height } + fadeOut()
            )
        }
    ) { hasContent ->
        if (hasContent) {
            contentComposable(contentList)
        } else {
            // Show EmptyGeneratedMessage when there's no content
            NoContentMessage(
                modifier = Modifier.fillMaxSize(),
                title = "Start a Conversation!",
                subtitle = "Explore AI-generated responses and have meaningful discussions.",
                buttonText = "Get Started",
                onButtonClick = navigateToChatScreen,
            )
        }
    }
}

