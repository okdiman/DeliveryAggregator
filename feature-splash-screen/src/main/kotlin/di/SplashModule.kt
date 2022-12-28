package di

import domain.GetAuthInfoUseCaseImpl
import domain.usecase.GetAuthInfoUseCase
import org.koin.dsl.module

fun splashModule() = module {
    factory<GetAuthInfoUseCase> { GetAuthInfoUseCaseImpl(get()) }
}