package deleting.di

import deleting.domain.DeleteProfileUseCase
import org.koin.dsl.module

internal fun deletingModule() = module {
    factory { DeleteProfileUseCase(get()) }
}