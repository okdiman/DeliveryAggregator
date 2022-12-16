package login.di

import data.AuthRepository
import domain.usecase.GetCodeUseCase
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.create
import root.data.AuthApi
import root.data.AuthRepositoryImpl
import login.domain.GetCodeUseCaseImpl

fun startModule() = module {
    single<AuthApi> { get<Retrofit>().create() }
    factory<AuthRepository> { AuthRepositoryImpl(get()) }
    factory<GetCodeUseCase> { GetCodeUseCaseImpl(get()) }
}