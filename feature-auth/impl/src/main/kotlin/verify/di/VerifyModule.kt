package verify.di

import domain.usecase.SignInUseCase
import org.koin.dsl.module
import verify.domain.GetVerifyTitleUseCase
import verify.domain.SignInUseCaseImpl

internal fun verifyModule() = module {
    factory { GetVerifyTitleUseCase(get()) }
    factory<SignInUseCase> { SignInUseCaseImpl(get()) }
}