package org.yassineabou.llms.app.core.data.remote.rpc


import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.encodedPath
import io.ktor.http.takeFrom
import io.ktor.serialization.kotlinx.json.json
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
    private val baseUrl: String = "http://10.0.2.2:8080",
    private val rpcPath: String = "/rpc"
) {
    // Shared Ktor HttpClient - similar to the sample
    private val httpClient = HttpClient {
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