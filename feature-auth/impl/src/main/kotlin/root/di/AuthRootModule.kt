package root.di

import root.data.AuthApi
import data.AuthLocalDataSource
import domain.AuthRepository
import domain.usecase.IsAuthorizedUseCase
import domain.usecase.SignUpUseCase
import network.domain.GetAuthTokenSyncUseCase
import network.domain.GetPushTokenSyncUseCase
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.create
import root.data.AuthLocalDataSourceImpl
import root.data.AuthRepositoryImpl
import root.data.mapper.AuthSignUpMapper
import root.domain.GetAuthTokenSyncUseCaseImpl
import root.domain.GetPushTokenSyncUseCaseImpl
import root.domain.IsAuthorizedUseCaseImpl
import root.domain.SignUpUseCaseImpl

internal fun authRootModule() = module {
    single<AuthApi> { get<Retrofit>().create() }
    factory<AuthRepository> { AuthRepositoryImpl(get(), get(), get()) }
    factory<AuthLocalDataSource> { AuthLocalDataSourceImpl(get(), get()) }
    factory<SignUpUseCase> { SignUpUseCaseImpl(get()) }
    factory { AuthSignUpMapper() }
    factory<GetAuthTokenSyncUseCase> { GetAuthTokenSyncUseCaseImpl(get()) }
    factory<GetPushTokenSyncUseCase> { GetPushTokenSyncUseCaseImpl(get()) }
    factory<IsAuthorizedUseCase> { IsAuthorizedUseCaseImpl(get()) }
}