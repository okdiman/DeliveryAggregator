package neworder.storage.di

import neworder.storage.domain.GetStoragesUseCase
import org.koin.dsl.module

internal fun newOrderStorageModule() = module {
    factory { GetStoragesUseCase(get()) }
}