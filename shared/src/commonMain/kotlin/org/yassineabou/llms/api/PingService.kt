package org.yassineabou.llms.api

import kotlinx.rpc.annotations.Rpc

@Rpc
interface PingService {
    suspend fun ping(message: String): String
}