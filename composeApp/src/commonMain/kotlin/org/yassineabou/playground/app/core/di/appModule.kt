package org.yassineabou.playground.app.core.di


import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import org.yassineabou.playground.feature.Imagine.ui.ImageGenViewModel
import org.yassineabou.playground.feature.chat.ui.ChatViewModel
import org.yassineabou.playground.feature.profile.ui.ProfileViewModel


val provideViewModelModule = module {
    viewModel { ProfileViewModel() }
    viewModel { ImageGenViewModel() }
    viewModel { ChatViewModel() }
}

fun appModule() = listOf(provideViewModelModule)