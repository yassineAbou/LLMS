package org.yassineabou.playground.app.di



import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import org.yassineabou.playground.feature.imageGen.ui.ImageGenViewModel
import org.yassineabou.playground.feature.profile.ui.ProfileViewModel


val provideViewModelModule = module {
    viewModel {  ProfileViewModel() }
    viewModel {  ImageGenViewModel() }
}

fun appModule() = listOf(provideViewModelModule)