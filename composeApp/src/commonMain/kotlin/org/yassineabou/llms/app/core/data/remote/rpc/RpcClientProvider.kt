package org.yassineabou.llms.app.core.data.remote.rpc


import co.touchlab.kermit.Logger
import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.plugins.websocket.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.rpc.RpcClient
import kotlinx.rpc.krpc.ktor.client.rpc
import kotlinx.rpc.krpc.ktor.client.rpcConfig
import kotlinx.rpc.krpc.serialization.json.json
import kotlinx.serialization.json.Json
import kotlin.concurrent.Volatile
import kotlin.time.Duration.Companion.seconds


/**
 * Provides kRPC client services for remote communication.
 * Handles automatic reconnection when WebSocket connection is lost.
 */
class RpcClientProvider(
    private val baseUrl: String,
    private val rpcPath: String
) {
    private val logger = Logger.withTag("RpcClientProvider")

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
            logger = object : io.ktor.client.plugins.logging.Logger {
                override fun log(message: String) {
                    this@RpcClientProvider.logger.d { message }
                }
            }
            level = LogLevel.INFO
        }

        install(HttpTimeout) {
            requestTimeoutMillis = 60_000
            connectTimeoutMillis = 30_000
            socketTimeoutMillis = 60_000
        }
    }

    private val clientMutex = Mutex()

    @Volatile
    private var currentRpcClient: RpcClient? = null

    /**
     * Gets the current RPC client or creates a new one if needed.
     */
    private suspend fun getOrCreateClient(): RpcClient {
        currentRpcClient?.let { return it }

        return clientMutex.withLock {
            currentRpcClient?.let { return it }

            logger.i { "🔌 Creating new RPC connection to $baseUrl$rpcPath" }

            httpClient.rpc {
                url {
                    val urlBuilder = URLBuilder(baseUrl)
                    protocol = when (urlBuilder.protocol) {
                        URLProtocol.HTTPS -> URLProtocol.WSS
                        else -> URLProtocol.WS
                    }
                    host = urlBuilder.host
                    port = urlBuilder.port
                    encodedPath = rpcPath
                }
                rpcConfig {
                    serialization {
                        json(jsonConfig)
                    }
                }
            }.also { currentRpcClient = it }
        }
    }

    fun invalidate() {
        logger.d { "Invalidating RPC client" }
        currentRpcClient = null
    }

    suspend fun <T> execute(block: suspend RpcClient.() -> T): T {
        return try {
            getOrCreateClient().block()
        } catch (e: Exception) {
            if (isConnectionError(e)) {
                logger.w { "RPC connection lost, reconnecting... (${e.message})" }
                invalidate()
                getOrCreateClient().block()
            } else {
                throw e
            }
        }
    }

    fun <T> executeFlow(block: suspend RpcClient.() -> Flow<T>): Flow<T> = flow {
        try {
            getOrCreateClient().block().collect { emit(it) }
        } catch (e: Exception) {
            if (isConnectionError(e)) {
                logger.w { "RPC connection lost during flow, reconnecting... (${e.message})" }
                invalidate()
                getOrCreateClient().block().collect { emit(it) }
            } else {
                throw e
            }
        }
    }

    private fun isConnectionError(e: Exception): Boolean {
        val message = e.message?.lowercase() ?: ""
        return e is IllegalStateException && (
                message.contains("cancelled") ||
                        message.contains("closed") ||
                        message.contains("connection")
                )
    }

    fun close() {
        logger.i { "Closing RPC client" }
        currentRpcClient = null
        httpClient.close()
    }
}