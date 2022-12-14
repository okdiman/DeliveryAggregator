package auth.di

import auth.data.AuthApi
import data.AuthRepository
import auth.data.AuthRepositoryImpl
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.create

fun authModule() = module {
    single<AuthApi> { get<Retrofit>().create() }
    factory<AuthRepository> { AuthRepositoryImpl(get()) }
}