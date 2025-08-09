package org.yassineabou.llms.app.core.di

import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.yassineabou.llms.app.core.data.local.DatabaseFactory


actual val platformModule = DI.Module("platformModule") {
    // Koin: single<DatabaseFactory> { DatabaseFactory() }
    // Kodein: bindSingleton<DatabaseFactory> { DatabaseFactory() }
    bindSingleton { DatabaseFactory() }
}