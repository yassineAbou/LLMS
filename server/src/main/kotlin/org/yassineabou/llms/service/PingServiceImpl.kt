package org.yassineabou.llms.service

import org.yassineabou.llms.api.PingService

class PingServiceImpl : PingService {
    override suspend fun ping(message: String): String {
        println(">>> PingService.ping() called with: $message")
        return "PONG: $message"
    }
}