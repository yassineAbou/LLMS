package org.yassineabou.llms.app.core.util

import io.ktor.client.HttpClient
import io.ktor.client.plugins.timeout
import io.ktor.http.encodedPath
import kotlinx.rpc.krpc.ktor.client.installKrpc
import kotlinx.rpc.krpc.ktor.client.rpc
import kotlinx.rpc.krpc.ktor.client.rpcConfig
import kotlinx.rpc.krpc.serialization.json.json
import kotlinx.rpc.withService
import org.yassineabou.llms.api.UserService

// commonMain/kotlin/RpcConfig.kt

const val DEV_SERVER_HOST: String = ""

val client by lazy {
    HttpClient {
        installKrpc()
    }
}

fun setupRPC(): UserService = client.rpc {
    url {
        host = DEV_SERVER_HOST
        port = 8080
        encodedPath = "/api"
    }

    rpcConfig {
        serialization {
            json {
                ignoreUnknownKeys = true
                isLenient = true
            }
        }
        timeout {
            requestTimeoutMillis = 3000
            socketTimeoutMillis = 3000
            connectTimeoutMillis = 3000
        }

        waitForServices = true
    }
}.withService()