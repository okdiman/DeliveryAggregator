package root.di

import data.AuthRepository
import data.datastore.AuthLocalDataStore
import domain.usecase.SignUpUseCase
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.create
import root.data.AuthApi
import root.data.AuthLocalDataStoreImpl
import root.data.AuthRepositoryImpl
import root.data.mapper.SignUpMapper
import root.domain.SignUpUseCaseImpl

fun rootModule() = module {
    single<AuthApi> { get<Retrofit>().create() }
    factory<AuthRepository> { AuthRepositoryImpl(get(), get(), get()) }
    factory<AuthLocalDataStore> { AuthLocalDataStoreImpl(get(), get()) }
    factory<SignUpUseCase> { SignUpUseCaseImpl(get()) }
    factory { SignUpMapper() }
}