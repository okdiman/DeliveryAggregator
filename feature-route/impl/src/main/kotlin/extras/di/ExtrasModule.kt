package extras.di

import extras.domain.GetExtrasUseCase
import extras.presentation.mapper.ExtrasUiMapper
import org.koin.dsl.module

internal fun extrasModule() = module {
    factory { GetExtrasUseCase(get()) }
    factory { ExtrasUiMapper() }
}