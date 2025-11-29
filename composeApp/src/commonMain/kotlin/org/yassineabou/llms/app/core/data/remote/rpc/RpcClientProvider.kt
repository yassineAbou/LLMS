package org.yassineabou.llms.app.core.data.remote.rpc


import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.plugins.websocket.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.rpc.krpc.ktor.client.rpc
import kotlinx.rpc.krpc.ktor.client.rpcConfig
import kotlinx.rpc.krpc.serialization.json.json
import kotlinx.rpc.withService
import kotlinx.serialization.json.Json
import org.yassineabou.llms.api.ChatService
import org.yassineabou.llms.api.ImageService
import org.yassineabou.llms.api.MessageService
import org.yassineabou.llms.api.UserService

class RpcClientProvider(
    private val baseUrl: String = "PUT YOUR LOCAL MACHINE ADRESSS HERE",
    private val rpcPath: String = "/api"
) {
    // Shared Ktor HttpClient - similar to the sample
    private val httpClient = HttpClient {

        install(WebSockets)



        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                  co.touchlab.kermit.Logger.withTag("KtorClient").i { message }
                }
            }
            level = LogLevel.ALL
        }


        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                encodeDefaults = true
            })
        }
        // Optional: Add logging, timeouts, etc.
    }

    // Create RPC client using the same pattern as the sample
    private val rpcClient = httpClient.rpc {
        url {
            // Parse baseUrl to extract host, port, etc.
            takeFrom(baseUrl)
            encodedPath = rpcPath
        }

        rpcConfig {
            serialization {
                json(Json {
                    ignoreUnknownKeys = true
                    encodeDefaults = true
                })
            }
        }
    }

    // Expose typed service interfaces using the same approach
    val chatService: ChatService by lazy { rpcClient.withService<ChatService>() }
    val imageService: ImageService by lazy { rpcClient.withService<ImageService>() }
    val messageService: MessageService by lazy { rpcClient.withService<MessageService>() }
    val userService: UserService by lazy { rpcClient.withService<UserService>() }

    // Cleanup
    fun close() {
        httpClient.close()
    }
}