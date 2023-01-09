package departure.di

import departure.domain.GetSuggestByQueryUseCaseImpl
import departure.domain.GetUserAddressesUseCaseImpl
import departure.domain.UpdateUserAddressUseCaseImpl
import departure.presentation.DepartureAddressUiMapper
import domain.usecase.GetSuggestByQueryUseCase
import domain.usecase.GetUserAddressesUseCase
import domain.usecase.UpdateUserAddressUseCase
import org.koin.dsl.module

internal fun departureModule() = module {
    factory { DepartureAddressUiMapper() }
    factory<GetSuggestByQueryUseCase> { GetSuggestByQueryUseCaseImpl(get()) }
    factory<GetUserAddressesUseCase> { GetUserAddressesUseCaseImpl(get()) }
    factory<UpdateUserAddressUseCase> { UpdateUserAddressUseCaseImpl(get()) }
}