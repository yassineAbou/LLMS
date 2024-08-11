package org.yassineabou.playground

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform