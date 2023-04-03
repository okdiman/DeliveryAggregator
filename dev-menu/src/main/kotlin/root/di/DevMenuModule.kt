package root.di

import devmenu.domain.DevMenuRepository
import org.koin.dsl.module
import root.data.DevMenuRepositoryImpl
import root.domain.DevMenuInteractor

fun devMenuModule() = module {
    factory<DevMenuRepository> { DevMenuRepositoryImpl(get(), get()) }
    factory { DevMenuInteractor(get(), get()) }
}