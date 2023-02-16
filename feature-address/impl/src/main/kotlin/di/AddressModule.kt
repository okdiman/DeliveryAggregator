package di

import data.AddressApi
import data.AddressRepositoryImpl
import data.mapper.AddressSuggestMapper
import domain.AddressRepository
import domain.GetAuthSuggestByQueryUseCase
import org.koin.dsl.module
import presentation.mapper.AddressSuggestUiMapper
import retrofit2.Retrofit
import retrofit2.create
import root.data.mapper.AddressMapper

fun addressModule() = module {
    single<AddressApi> { get<Retrofit>().create() }
    factory<AddressRepository> { AddressRepositoryImpl(get(), get(), get()) }
    factory { AddressSuggestMapper() }
    factory { AddressMapper() }
    factory { AddressSuggestUiMapper() }
    factory { GetAuthSuggestByQueryUseCase(get()) }
}