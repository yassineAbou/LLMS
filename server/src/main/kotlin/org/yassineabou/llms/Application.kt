package org.yassineabou.llms

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.routing.*
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

    install(Krpc)
    installCORS(environment = Environment().cors)

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
        allowMethod(HttpMethod.Options)
        allowMethod(HttpMethod.Put)
        allowMethod(HttpMethod.Delete)
        allowMethod(HttpMethod.Patch)
        allowHeader(HttpHeaders.Authorization)
        allowHeader(HttpHeaders.AccessControlAllowOrigin)
        allowHeader(HttpHeaders.Upgrade)
        allowNonSimpleContentTypes = true
        allowCredentials = true
        allowSameOrigin = true

        environment.allowedHosts.forEach { host ->
            allowHost(host, listOf("http", "https", "ws", "wss"))
        }
    }
}
