package org.yassineabou.llms

private const val DEFAULT_HOST = "0.0.0.0"
private const val DEFAULT_PORT = 8081

data class Environment(
    val http: Http = Http(),
    val cors: Cors = Cors()
) {
    data class Http(
        val host: String = System.getenv("HOST") ?: DEFAULT_HOST,
        val port: Int = System.getenv("PORT")?.toIntOrNull() ?: DEFAULT_PORT
    )

    data class Cors(
        val allowedHosts: List<String> = System.getenv("CORS_ALLOWED_HOSTS")
            ?.split(",")
            ?: listOf(
                "localhost:3000",
                "localhost:8080",
                "localhost:8081",
                "127.0.0.1:8080",
                "127.0.0.1:8081"
            )
    )
}