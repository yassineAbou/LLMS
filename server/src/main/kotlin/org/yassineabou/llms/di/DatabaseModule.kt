package org.yassineabou.llms.di

import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton
import org.yassineabou.llms.database.DatabaseFactory
import org.yassineabou.llms.repository.ChatRepository
import org.yassineabou.llms.repository.ImageRepository
import org.yassineabou.llms.repository.MessageRepository
import org.yassineabou.llms.repository.UserRepository


suspend fun initializeDatabase() {
    DatabaseFactory.init()
}

fun kodeinDatabaseModule() = DI.Module("database") {
    bind<ChatRepository>() with singleton { ChatRepository() }
    bind<ImageRepository>() with singleton { ImageRepository() }
    bind<MessageRepository>() with singleton { MessageRepository() }
    bind<UserRepository>() with singleton { UserRepository() }
}