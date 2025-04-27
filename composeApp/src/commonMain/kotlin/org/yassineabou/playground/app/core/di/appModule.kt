package org.yassineabou.playground.app.core.di


import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import org.yassineabou.playground.feature.chat.data.dataSource.remote.ChutesAiApi
import org.yassineabou.playground.feature.chat.data.dataSource.remote.KtorChutesApi
import org.yassineabou.playground.feature.chat.data.repository.ChutesAiRepository
import org.yassineabou.playground.feature.chat.ui.ChatViewModel
import org.yassineabou.playground.feature.imagine.ui.ImageGenViewModel

// ========================================================================================
//                                  Data Module
// ========================================================================================
val provideDataModule = module {

    single {
        Json {
            ignoreUnknownKeys = true
            isLenient = true
            encodeDefaults = false
        }
    }


    single<HttpClient> {
        HttpClient {
            install(ContentNegotiation) {
                json(
                    json = get(),
                    contentType = ContentType.Application.Json
                )
            }
            install(HttpTimeout) {
                requestTimeoutMillis = 60000
            }
        }

    }

    // 3. API Implementation
    single<ChutesAiApi> {
        KtorChutesApi(
            client = get(),
            json = get()
        )
    }


    single<ChutesAiRepository> {
        ChutesAiRepository(
            chutesAiApi = get()
        )
    }
}


// ========================================================================================
//                                  ViewModule Module
// ========================================================================================
val provideViewModelModule = module {
    viewModel { ImageGenViewModel() }
    viewModel {
      ChatViewModel(
        chutesAiRepository = get()
      )
    }
}

fun appModule() = listOf(provideDataModule, provideViewModelModule)