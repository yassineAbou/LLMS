plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.ktor)
    alias(libs.plugins.kotlinx.rpc)
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

    // Exposed ORM
    implementation(libs.exposed.core)
    implementation(libs.exposed.dao)
    implementation(libs.exposed.kotlin.datetime)
    implementation(libs.exposed.r2dbc)
    implementation(libs.r2dbc.postgresql)
    implementation(libs.r2dbc.pool)
    implementation(libs.exposed.migration.r2dbc)

    implementation(libs.kodein.di.ktor.server)

}
