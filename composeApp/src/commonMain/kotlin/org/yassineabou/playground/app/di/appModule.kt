package org.yassineabou.playground.app.di


import org.yassineabou.playground.feature.profile.ui.ProfileViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module


val provideViewModelModule = module {
    viewModelOf(::ProfileViewModel)
}

fun appModule() = listOf(provideViewModelModule)