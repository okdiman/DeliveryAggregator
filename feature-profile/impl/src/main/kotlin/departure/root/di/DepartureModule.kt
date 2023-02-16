package departure.root.di

import departure.domain.AddressModelCopyUseCase
import departure.presentation.mapper.DepartureAddressUiMapper
import departure.root.domain.AddUserAddressUseCaseImpl
import departure.root.domain.GetSuggestByQueryUseCaseImpl
import departure.root.domain.GetUserAddressesUseCaseImpl
import departure.root.domain.UpdateUserAddressUseCaseImpl
import domain.usecase.GetSuggestByQueryUseCase
import domain.usecase.GetUserAddressesUseCase
import domain.usecase.UpdateUserAddressUseCase
import org.koin.dsl.module

internal fun departureModule() = module {
    factory { DepartureAddressUiMapper() }
    factory { AddressModelCopyUseCase() }
    factory<GetSuggestByQueryUseCase> { GetSuggestByQueryUseCaseImpl(get()) }
    factory { AddUserAddressUseCaseImpl(get()) }
    factory<GetUserAddressesUseCase> { GetUserAddressesUseCaseImpl(get()) }
    factory<UpdateUserAddressUseCase> { UpdateUserAddressUseCaseImpl(get()) }
}