package root.di

import domain.AddressRepository
import domain.usecase.AddUserAddressUseCase
import domain.usecase.GetAuthSuggestByQueryUseCase
import domain.usecase.GetSuggestByQueryUseCase
import domain.usecase.GetUserAddressesUseCase
import domain.usecase.UpdateUserAddressUseCase
import org.koin.dsl.module
import presentation.mapper.AddressUiMapper
import retrofit2.Retrofit
import retrofit2.create
import root.data.AddressApi
import root.data.AddressRepositoryImpl
import root.data.mapper.AddressMapper
import root.data.mapper.AddressSuggestMapper
import root.domain.AddUserAddressUseCaseImpl
import root.domain.GetAuthSuggestByQueryUseCaseImpl
import root.domain.GetSuggestByQueryUseCaseImpl
import root.domain.GetUserAddressesUseCaseImpl
import root.domain.UpdateUserAddressUseCaseImpl
import root.presentation.mapper.AddressSuggestUiMapper

fun addressModule() = module {
    single<AddressApi> { get<Retrofit>().create() }
    factory<AddressRepository> { AddressRepositoryImpl(get(), get(), get()) }
    factory { AddressSuggestMapper() }
    factory { AddressMapper() }
    factory { AddressSuggestUiMapper() }
    factory<GetAuthSuggestByQueryUseCase> { GetAuthSuggestByQueryUseCaseImpl(get()) }
    factory<GetSuggestByQueryUseCase> { GetSuggestByQueryUseCaseImpl(get()) }
    factory<AddUserAddressUseCase> { AddUserAddressUseCaseImpl(get()) }
    factory<GetUserAddressesUseCase> { GetUserAddressesUseCaseImpl(get()) }
    factory<UpdateUserAddressUseCase> { UpdateUserAddressUseCaseImpl(get()) }
    factory { AddressUiMapper() }
}