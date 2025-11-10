package org.yassineabou.llms.di

import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton
import org.yassineabou.llms.api.ChatService
import org.yassineabou.llms.api.ImageService
import org.yassineabou.llms.api.MessageService
import org.yassineabou.llms.api.UserService
import org.yassineabou.llms.service.ChatServiceImpl
import org.yassineabou.llms.service.ImageServiceImpl
import org.yassineabou.llms.service.MessageServiceImpl
import org.yassineabou.llms.service.UserServiceImpl

fun kodeinServicesModule() = DI.Module("services") {
    bind<ChatService>() with singleton {
        ChatServiceImpl(instance())
    }
    bind<ImageService>() with singleton {
        ImageServiceImpl(instance())
    }
    bind<MessageService>() with singleton {
        MessageServiceImpl(instance())
    }
    bind<UserService>() with singleton {
        UserServiceImpl(instance())
    }
}