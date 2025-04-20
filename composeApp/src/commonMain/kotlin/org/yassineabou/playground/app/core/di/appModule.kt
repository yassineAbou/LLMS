package org.yassineabou.playground.app.core.di


import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import org.yassineabou.playground.feature.chat.data.network.ChutesAiApi
import org.yassineabou.playground.feature.chat.data.network.ChutesAiRepository
import org.yassineabou.playground.feature.chat.data.network.GitHubMarkdownApi
import org.yassineabou.playground.feature.chat.data.network.GitHubMarkdownApiImpl
import org.yassineabou.playground.feature.chat.data.network.GitHubMarkdownRepository
import org.yassineabou.playground.feature.chat.data.network.KtorChutesApi
import org.yassineabou.playground.feature.chat.ui.ChatViewModel
import org.yassineabou.playground.feature.imagine.ui.ImageGenViewModel

val provideDataModule = module {
    // 1. JSON Configuration (shared instance)
    single {
        Json {
            ignoreUnknownKeys = true
            isLenient = true
            encodeDefaults = false // Match API expectations [[8]]
        }
    }

    // 2. HTTP Client Configuration
    single<HttpClient> {
        HttpClient(CIO) {
            engine {
                requestTimeout = 0
            }
            // Specify engine explicitly [[1]][[4]]
            install(ContentNegotiation) {
                json(
                    json = get(), // Use shared JSON instance
                    contentType = ContentType.Application.Json
                )
            }
        }

    }

    // 3. API Implementation
    single<ChutesAiApi> {
        KtorChutesApi(
            client = get(),
            json = get() // Inject shared JSON instance [[5]]
        )
    }
    single<GitHubMarkdownApi> { GitHubMarkdownApiImpl(get()) }

    // 4. Repository Implementation
    single<ChutesAiRepository> {
        ChutesAiRepository(
            chutesAiApi = get()
        )
    }
    single<GitHubMarkdownRepository> {
        GitHubMarkdownRepository(
            api = get()
        )
    }
}


val provideViewModelModule = module {
    viewModel { ImageGenViewModel() }
    viewModel { ChatViewModel(get(), get()) }
}

fun appModule() = listOf(provideDataModule, provideViewModelModule)