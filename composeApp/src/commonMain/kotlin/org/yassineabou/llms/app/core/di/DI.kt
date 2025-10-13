package org.yassineabou.llms.app.core.di

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.lifecycle.ViewModel
import app.cash.sqldelight.db.SqlDriver
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.bindSingleton
import org.kodein.di.direct
import org.kodein.di.instance
import org.yassineabou.llms.LlmsDatabase
import org.yassineabou.llms.app.core.data.local.DatabaseFactory
import org.yassineabou.llms.app.core.data.local.LlmsDatabaseInterface
import org.yassineabou.llms.app.core.data.local.LlmsDatabaseRepository
import org.yassineabou.llms.app.core.data.remote.ai.AiApi
import org.yassineabou.llms.app.core.data.remote.ai.AiRepository
import org.yassineabou.llms.app.core.data.remote.ai.KtorApi
import org.yassineabou.llms.feature.chat.ui.ChatViewModel
import org.yassineabou.llms.feature.imagine.ui.ImagineViewModel
import org.yassineabou.llms.feature.you.ui.YouViewModel


/**
 * This is the main DI container for the application.
 * It is initialized once and can be accessed globally.
 */
fun appModule() = DI.Module("AppModule") {
    import(commonModule)
    import(platformModule)
}

/**
 * The common module for dependencies that are platform-agnostic.
 */
val commonModule = DI.Module("commonModule") {

    // ========================================================================================
    //                                  Network
    // ========================================================================================

    // Koin: single { ... }
    // Kodein: bindSingleton { ... }
    bindSingleton {
        Json {
            ignoreUnknownKeys = true
            isLenient = true
            encodeDefaults = false
            explicitNulls = false
        }
    }

    bindSingleton<HttpClient> {
        HttpClient {
            install(ContentNegotiation) {
                json(
                    // Koin: get() -> Kodein: instance()
                    json = instance(),
                    contentType = ContentType.Application.Json
                )
            }
            install(HttpTimeout) {
                requestTimeoutMillis = 600_000
            }
        }
    }

    bindSingleton<AiApi> {
        KtorApi(client = instance(), json = instance())
    }

    bindSingleton<AiRepository> { AiRepository(aiApi = instance()) }

    // ========================================================================================
    //                                  Database
    // ========================================================================================



    bindSingleton<SqlDriver> {
        val factory: DatabaseFactory = instance()
        factory.createDriver()
    }

    bindSingleton<LlmsDatabase> {
        LlmsDatabase(driver = instance())
    }

    bindSingleton<LlmsDatabaseInterface> {
        LlmsDatabaseRepository(llmsDatabase = instance())
    }



    // ========================================================================================
    //                                  ViewModels
    // ========================================================================================

    // Koin has a special `viewModel` scope. In Kodein, ViewModels are typically
    // not singletons. They should be created for each screen. The `provider`
    // binding is perfect for this, as it creates a new instance every time.
    bindProvider { ImagineViewModel(aiRepository = instance(), llmsDatabaseRepository = instance()) }
    bindProvider { ChatViewModel(aiRepository = instance(), llmsDatabaseRepository = instance()) }
    bindProvider { YouViewModel() }
}

/**
 * The platform-specific module. The 'actual' implementation will provide the bindings.
 */
expect val platformModule: DI.Module


val LocalDI = staticCompositionLocalOf<DI> {
    error("DI container not provided")
}

/**
 * A Composable helper function to retrieve a ViewModel instance from the local DI container.
 * This is the Kodein equivalent of Koin's `koinViewModel()`.
 *
 * It uses `remember` to ensure the ViewModel instance is retained across recompositions.
 *
 * @param T The type of the ViewModel to retrieve.
 * @return An instance of the ViewModel.
 */
@Composable
inline fun <reified T : ViewModel> kodeinViewModel(): T {
    val di = LocalDI.current
    return remember { di.direct.instance<T>() }
}









