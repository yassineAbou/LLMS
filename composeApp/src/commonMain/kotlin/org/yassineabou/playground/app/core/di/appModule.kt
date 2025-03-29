package org.yassineabou.playground.app.core.di


import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import org.yassineabou.playground.feature.chat.ui.ChatViewModel
import org.yassineabou.playground.feature.imagine.ui.ImageGenViewModel


val provideViewModelModule = module {
    viewModel { ImageGenViewModel() }
    viewModel { ChatViewModel() }
}

fun appModule() = listOf(provideViewModelModule)