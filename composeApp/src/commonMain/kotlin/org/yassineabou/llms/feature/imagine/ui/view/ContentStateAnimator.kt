package org.yassineabou.llms.feature.imagine.ui.view

import androidx.compose.animation.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.yassineabou.llms.Chats

@Composable
fun ContentStateAnimator(
    contentList: List<Chats>,
    contentComposable: @Composable (List<Chats>) -> Unit,
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

