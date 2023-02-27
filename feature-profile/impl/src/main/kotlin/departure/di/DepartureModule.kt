package departure.di

import departure.domain.AddressModelCopyUseCase
import org.koin.dsl.module

internal fun departureModule() = module {
    factory { AddressModelCopyUseCase() }
}