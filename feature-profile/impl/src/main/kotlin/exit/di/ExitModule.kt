package exit.di

import exit.domain.ExitFromProfileUseCase
import org.koin.dsl.module

internal fun exitModule() = module {
    factory { ExitFromProfileUseCase(get(), get()) }
}