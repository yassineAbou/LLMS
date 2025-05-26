package org.yassineabou.llms

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform