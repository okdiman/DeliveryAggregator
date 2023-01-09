package login.di

import domain.usecase.GetCodeUseCase
import login.domain.GetCodeUseCaseImpl
import org.koin.dsl.module

internal fun loginModule() = module {
    factory<GetCodeUseCase> { GetCodeUseCaseImpl(get()) }
}