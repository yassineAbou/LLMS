package org.yassineabou.llms

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.routing.*
import kotlinx.coroutines.runBlocking
import kotlinx.rpc.krpc.ktor.server.Krpc
import kotlinx.rpc.krpc.ktor.server.rpc
import kotlinx.rpc.krpc.serialization.json.json
import org.kodein.di.direct
import org.kodein.di.instance
import org.kodein.di.ktor.closestDI
import org.kodein.di.ktor.di
import org.yassineabou.llms.api.*
import org.yassineabou.llms.di.initializeDatabase
import org.yassineabou.llms.di.kodeinDatabaseModule
import org.yassineabou.llms.di.kodeinServicesModule
import org.yassineabou.llms.service.PingServiceImpl


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

    runBlocking {
        initializeDatabase()
    }

    installCORS(environment = Environment().cors)

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
            registerService<PingService> { PingServiceImpl() }
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
