package org.yassineabou.llms

/*
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
    install(Krpc)

    installCORS(environment = Environment().cors)

    routing {
        rpc("/api") {
            rpcConfig {
                serialization {
                    json()
                }
            }

            registerService<UserService> { UserServiceImpl() }
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

 */