package org.yassineabou.llms.app.core.navigation

import androidx.navigation3.runtime.NavKey
import androidx.savedstate.serialization.SavedStateConfiguration
import kotlinx.serialization.Serializable
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

@Serializable
data object ChatRoute : NavKey

@Serializable
data object ChatHistoryRoute : NavKey

@Serializable
data object ImagineRoute : NavKey

@Serializable
data object GeneratedImagesRoute : NavKey

@Serializable
data object FullScreenImageRoute : NavKey

@Serializable
data object ImageGenerationLoadingRoute : NavKey

@kotlinx.serialization.Serializable
data object YouRoute : NavKey


val navConfig = SavedStateConfiguration {
    serializersModule = SerializersModule {
        polymorphic(NavKey::class) {
            subclass(ChatRoute::class, ChatRoute.serializer())
            subclass(ChatHistoryRoute::class, ChatHistoryRoute.serializer())
            subclass(ImagineRoute::class, ImagineRoute.serializer())
            subclass(GeneratedImagesRoute::class, GeneratedImagesRoute.serializer())
            subclass(FullScreenImageRoute::class, FullScreenImageRoute.serializer())
            subclass(ImageGenerationLoadingRoute::class, ImageGenerationLoadingRoute.serializer())
            subclass(YouRoute::class, YouRoute.serializer())
        }
    }
}

val TOP_LEVEL_ROUTES: Set<NavKey> = setOf(ChatRoute, ImagineRoute, YouRoute)

val FULL_SCREEN_ROUTES: Set<NavKey> = setOf(
    GeneratedImagesRoute,
    FullScreenImageRoute,
    ImageGenerationLoadingRoute,
    ChatHistoryRoute
)