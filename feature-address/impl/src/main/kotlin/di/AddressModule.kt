package di

import data.AddressApi
import data.AddressRepositoryImpl
import data.mapper.AddressSuggestMapper
import domain.AddressRepository
import domain.GetSuggestByQueryUseCaseImpl
import domain.usecase.GetSuggestByQueryUseCase
import org.koin.dsl.module
import presentation.mapper.AddressSuggestUiMapper
import retrofit2.Retrofit
import retrofit2.create

fun addressModule() = module {
    single<AddressApi> { get<Retrofit>().create() }
    factory<AddressRepository> { AddressRepositoryImpl(get(), get()) }
    factory { AddressSuggestMapper() }
    factory { AddressSuggestUiMapper() }
    factory<GetSuggestByQueryUseCase> { GetSuggestByQueryUseCaseImpl(get()) }
}