package departure.root.di

import departure.root.domain.AddUserAddressUseCaseImpl
import departure.root.domain.GetSuggestByQueryUseCaseImpl
import departure.root.domain.GetUserAddressesUseCaseImpl
import departure.root.domain.UpdateUserAddressUseCaseImpl
import departure.root.presentation.mapper.DepartureAddressUiMapper
import domain.usecase.GetSuggestByQueryUseCase
import domain.usecase.GetUserAddressesUseCase
import domain.usecase.UpdateUserAddressUseCase
import org.koin.dsl.module

internal fun departureModule() = module {
    factory { DepartureAddressUiMapper() }
    factory<GetSuggestByQueryUseCase> { GetSuggestByQueryUseCaseImpl(get()) }
    factory { AddUserAddressUseCaseImpl(get()) }
    factory<GetUserAddressesUseCase> { GetUserAddressesUseCaseImpl(get()) }
    factory<UpdateUserAddressUseCase> { UpdateUserAddressUseCaseImpl(get()) }
}