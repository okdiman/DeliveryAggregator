package login.di

import domain.usecase.GetCodeUseCase
import login.domain.GetCodeUseCaseImpl
import org.koin.dsl.module

fun loginModule() = module {
    factory<GetCodeUseCase> { GetCodeUseCaseImpl(get()) }
}