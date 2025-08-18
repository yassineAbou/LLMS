plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.ktor)
    alias(libs.plugins.kotlinx.rpc)
    alias(libs.plugins.sqlDelight)
    application
}

group = "org.yassineabou.playground"
version = "1.0.0"
application {
    mainClass.set("org.yassineabou.playground.ApplicationKt")
    applicationDefaultJvmArgs =
        listOf("-Dio.ktor.development=${extra["io.ktor.development"] ?: "false"}")
}

dependencies {

    implementation(projects.shared)
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.netty)
    testImplementation(libs.ktor.server.tests)
    testImplementation(libs.kotlin.test.junit)

    //Rpc
    implementation(libs.logback)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.ktor.server.cors.jvm)
    implementation(libs.ktor.server.websockets.jvm)
    implementation(libs.ktor.server.host.common.jvm)
    implementation(libs.kotlinx.rpc.krpc.server)
    implementation(libs.kotlinx.rpc.krpc.serialization.json)
    implementation(libs.kotlinx.rpc.krpc.ktor.server)
    testImplementation(libs.ktor.server.test.host)
    testImplementation(libs.kotlinx.rpc.krpc.client)
    testImplementation(libs.kotlinx.rpc.krpc.ktor.client)

    // SQLDelight
    implementation(libs.sqldelight.jdbc.driver)
    implementation(libs.sqldelight.coroutines.extensions)
    implementation(libs.postgresql)
    implementation(libs.hikaricp)

    //JWT
    implementation(libs.ktor.server.auth)
    implementation(libs.ktor.server.auth.jwt)
    implementation(libs.auth0.jwt)

}


sqldelight {
    databases {
        create("ServerDatabase") {
            packageName.set("org.yassineabou.llms")
            dialect("app.cash.sqldelight:postgresql-dialect:${libs.versions.sqlDelight.get()}")
            srcDirs("src/main/sqldelight")
            deriveSchemaFromMigrations.set(true)
        }
    }
}
