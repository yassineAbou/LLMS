package org.yassineabou.llms

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.WebSockets
import io.ktor.server.websocket.pingPeriod
import io.ktor.server.websocket.timeout
import kotlinx.coroutines.launch
import kotlinx.rpc.krpc.ktor.server.Krpc
import kotlinx.rpc.krpc.ktor.server.rpc
import kotlinx.rpc.krpc.serialization.json.json
import org.kodein.di.direct
import org.kodein.di.instance
import org.kodein.di.ktor.closestDI
import org.kodein.di.ktor.di
import org.yassineabou.llms.api.ChatService
import org.yassineabou.llms.api.ImageService
import org.yassineabou.llms.api.MessageService
import org.yassineabou.llms.api.UserService
import org.yassineabou.llms.di.initializeDatabase
import org.yassineabou.llms.di.kodeinDatabaseModule
import org.yassineabou.llms.di.kodeinServicesModule
import kotlin.time.Duration.Companion.seconds


fun main() {
    val environment = Environment()
    embeddedServer(
        factory = Netty,
        port = environment.http.port,
        host = environment.http.host,
        module = Application::module
    ).start(wait = true)
}

@Suppress("unused")
fun Application.module() {
    di {

        import(kodeinDatabaseModule())
        import(kodeinServicesModule())
    }

    launch {
        initializeDatabase()
    }

    installCORS(environment = Environment().cors)

    install(WebSockets) {
        pingPeriod = 15.seconds
        timeout = 15.seconds
        maxFrameSize = Long.MAX_VALUE
        masking = false
    }

    install(Krpc)

    routing {
        rpc("/api") {
            rpcConfig {
                serialization {
                    json()
                }
            }

            val di = closestDI()

            registerService<ChatService> { di.direct.instance() }
            registerService<ImageService> { di.direct.instance() }
            registerService<MessageService> { di.direct.instance() }
            registerService<UserService> { di.direct.instance() }
        }
    }
}

fun Application.installCORS(environment: Environment.Cors) {
    install(CORS) {
        // HTTP Methods
        allowMethod(HttpMethod.Options)
        allowMethod(HttpMethod.Get)
        allowMethod(HttpMethod.Post)
        allowMethod(HttpMethod.Put)
        allowMethod(HttpMethod.Delete)
        allowMethod(HttpMethod.Patch)

        // Standard Headers
        allowHeader(HttpHeaders.Authorization)
        allowHeader(HttpHeaders.ContentType)
        allowHeader(HttpHeaders.Accept)
        allowHeader(HttpHeaders.Origin)
        allowHeader(HttpHeaders.AccessControlAllowOrigin)
        allowHeader(HttpHeaders.AccessControlAllowHeaders)
        allowHeader(HttpHeaders.AccessControlAllowMethods)

        // WebSocket Headers (CRITICAL for kRPC!)
        allowHeader(HttpHeaders.Upgrade)
        allowHeader(HttpHeaders.Connection)
        allowHeader(HttpHeaders.SecWebSocketKey)
        allowHeader(HttpHeaders.SecWebSocketVersion)
        allowHeader(HttpHeaders.SecWebSocketExtensions)
        allowHeader(HttpHeaders.SecWebSocketProtocol)
        allowHeader(HttpHeaders.SecWebSocketAccept)

        // Custom Headers
        allowHeader("X-Requested-With")

        // Expose headers
        exposeHeader(HttpHeaders.AccessControlAllowOrigin)
        exposeHeader(HttpHeaders.AccessControlAllowHeaders)

        // Settings
        allowNonSimpleContentTypes = true
        allowCredentials = true
        allowSameOrigin = true

        environment.allowedHosts.forEach { host ->
            allowHost(host, listOf("http", "https", "ws", "wss"))
        }

    }
}
