package root.di

import domain.AuthRepository
import data.AuthLocalDataSource
import domain.usecase.SignUpUseCase
import network.domain.GetAuthTokenSyncUseCase
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.create
import root.data.AuthApi
import root.data.AuthLocalDataSourceImpl
import root.data.AuthRepositoryImpl
import root.data.mapper.SignUpMapper
import root.domain.GetAuthTokenSyncUseCaseImpl
import root.domain.SignUpUseCaseImpl

internal fun authRootModule() = module {
    single<AuthApi> { get<Retrofit>().create() }
    factory<AuthRepository> { AuthRepositoryImpl(get(), get(), get()) }
    factory<AuthLocalDataSource> { AuthLocalDataSourceImpl(get(), get()) }
    factory<SignUpUseCase> { SignUpUseCaseImpl(get()) }
    factory { SignUpMapper() }
    factory<GetAuthTokenSyncUseCase> { GetAuthTokenSyncUseCaseImpl(get()) }
}