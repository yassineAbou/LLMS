package org.yassineabou.llms.app.core.di


import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import org.yassineabou.llms.app.core.data.local.LlmsDatabaseRepository
import org.yassineabou.llms.app.core.data.remote.AiApi
import org.yassineabou.llms.app.core.data.remote.AiRepository
import org.yassineabou.llms.app.core.data.remote.KtorApi
import org.yassineabou.llms.feature.chat.ui.ChatViewModel
import org.yassineabou.llms.feature.imagine.ui.ImageGenViewModel


fun appModules() = listOf(commonModule, platformModule)

val commonModule = module {

// ========================================================================================
//                                  Network
// ========================================================================================

    single {
        Json {
            ignoreUnknownKeys = true
            isLenient = true
            encodeDefaults = false
            explicitNulls = false
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
                requestTimeoutMillis = 600_000
            }
        }

    }

    // 3. API Implementation
    single<AiApi> {
        KtorApi(client = get(), json = get())
    }


    single<AiRepository> { AiRepository(aiApi = get())
    }

// ========================================================================================
//                                  Database
// ========================================================================================

    single { LlmsDatabaseRepository(llmsDatabaseWrapper = get()) }


// ========================================================================================
//                                  ViewModule
// ========================================================================================

    viewModel { ImageGenViewModel(aiRepository = get(), llmsDatabaseRepository = get ()) }
    viewModel { ChatViewModel(aiRepository = get(), llmsDatabaseRepository = get()) }
}


expect val platformModule: Module

