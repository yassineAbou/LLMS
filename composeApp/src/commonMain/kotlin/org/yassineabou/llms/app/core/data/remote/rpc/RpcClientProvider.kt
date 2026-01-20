package org.yassineabou.llms.app.core.data.remote.rpc


import io.ktor.client.*
import io.ktor.client.plugins.HttpTimeout
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
import kotlin.time.Duration.Companion.seconds


/**
 * Provides kRPC client services for remote communication.
 * Uses WebSockets internally for RPC calls.
 */
class RpcClientProvider(
    private val baseUrl: String,
    private val rpcPath: String
) {
    private val logger = co.touchlab.kermit.Logger.withTag("KtorClient")

    private val jsonConfig = Json {
        ignoreUnknownKeys = true
        encodeDefaults = true
        isLenient = true
        prettyPrint = false
    }


    private val httpClient = HttpClient {
        install(WebSockets) {
            pingInterval = 15.seconds
            maxFrameSize = Long.MAX_VALUE
        }

        install(ContentNegotiation) {
            json(jsonConfig)
        }


        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    this@RpcClientProvider.logger.i { message }
                }
            }
            level = LogLevel.ALL
        }


        install(HttpTimeout) {
            requestTimeoutMillis = 60_000
            connectTimeoutMillis = 30_000
            socketTimeoutMillis = 60_000
        }
    }


    private val rpcClient by lazy {
        logger.i { "🔌 Initializing RPC client: $baseUrl$rpcPath" }

        httpClient.rpc {
            url {
                // Parse the base URL
                val urlBuilder = URLBuilder(baseUrl)

                // Set WebSocket protocol based on HTTP protocol
                protocol = when (urlBuilder.protocol) {
                    URLProtocol.HTTPS -> URLProtocol.WSS
                    else -> URLProtocol.WS
                }

                host = urlBuilder.host
                port = urlBuilder.port
                encodedPath = rpcPath

                logger.i { "🔌 RPC URL: ${this.buildString()}" }
            }

            rpcConfig {
                serialization {
                    json(jsonConfig)
                }
            }
        }
    }

    // Service proxies
    val chatService: ChatService by lazy {
        logger.d { "Creating ChatService proxy" }
        rpcClient.withService<ChatService>()
    }

    val imageService: ImageService by lazy {
        logger.d { "Creating ImageService proxy" }
        rpcClient.withService<ImageService>()
    }

    val messageService: MessageService by lazy {
        logger.d { "Creating MessageService proxy" }
        rpcClient.withService<MessageService>()
    }

    val userService: UserService by lazy {
        logger.d { "Creating UserService proxy" }
        rpcClient.withService<UserService>()
    }

    fun close() {
        logger.i { "Closing RPC client" }
        httpClient.close()
    }
}