package root.di

import data.AuthLocalDataSource
import domain.AuthRepository
import domain.usecase.IsAuthorizedUseCase
import domain.usecase.SignUpUseCase
import network.domain.GetAuthTokenSyncUseCase
import network.domain.GetPushTokenSyncUseCase
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.create
import root.data.AuthApi
import root.data.AuthLocalDataSourceImpl
import root.data.AuthRepositoryImpl
import root.data.mapper.AuthSignUpMapper
import root.data.mapper.DtoInfoMapper
import root.domain.GetAuthTokenSyncUseCaseImpl
import root.domain.GetPushTokenSyncUseCaseImpl
import root.domain.IsAuthorizedUseCaseImpl
import root.domain.SignUpUseCaseImpl

internal fun authRootModule() = module {
    single<AuthApi> { get<Retrofit>().create() }
    factoryOf(::AuthRepositoryImpl) { bind<AuthRepository>() }
    factory<AuthLocalDataSource> { AuthLocalDataSourceImpl(get(), get()) }
    factory<SignUpUseCase> { SignUpUseCaseImpl(get()) }
    factory { AuthSignUpMapper() }
    factory<GetAuthTokenSyncUseCase> { GetAuthTokenSyncUseCaseImpl(get()) }
    factory<GetPushTokenSyncUseCase> { GetPushTokenSyncUseCaseImpl(get()) }
    factory<IsAuthorizedUseCase> { IsAuthorizedUseCaseImpl(get()) }
    factoryOf(::DtoInfoMapper)
}