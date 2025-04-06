package org.yassineabou.playground.app.core.di


import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import org.yassineabou.playground.feature.chat.data.network.AIHordeApi
import org.yassineabou.playground.feature.chat.data.network.AIHordeRepository
import org.yassineabou.playground.feature.chat.data.network.KtorAIHordeApi
import org.yassineabou.playground.feature.chat.ui.ChatViewModel
import org.yassineabou.playground.feature.imagine.ui.ImageGenViewModel

val provideDataModule = module {
    single {
        HttpClient {
            install(ContentNegotiation) {
                json(json = Json { ignoreUnknownKeys = true }, contentType = ContentType.Any)
            }
        }
    }
    single<AIHordeApi> { KtorAIHordeApi(get()) }
    single<AIHordeRepository> { AIHordeRepository(get()) }
}

val provideViewModelModule = module {
    viewModel { ImageGenViewModel() }
    viewModel { ChatViewModel(get()) }
}

fun appModule() = listOf(provideDataModule, provideViewModelModule)