package di
import ProfileViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val provideViewModelModule = module {
    viewModelOf(::ProfileViewModel)
}

fun appModule() = listOf(provideViewModelModule)