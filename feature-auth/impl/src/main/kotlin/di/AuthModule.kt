package di

import auth.data.AuthApi
import auth.data.AuthRepositoryImpl
import auth.domain.GetVerifyCodeUseCaseImpl
import data.AuthRepository
import domain.usecase.GetVerifyCodeUseCase
import offer.domain.GetOfferUseCase
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.create

fun authModule() = module {
    single<AuthApi> { get<Retrofit>().create() }
    factory<AuthRepository> { AuthRepositoryImpl(get()) }
    factory<GetVerifyCodeUseCase> { GetVerifyCodeUseCaseImpl(get()) }
    factory { GetOfferUseCase(get(), get()) }
}