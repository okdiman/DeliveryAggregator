package editing.di

import domain.usecase.GetMaskedPhoneUseCase
import editing.domain.GetMaskedPhoneUseCaseImpl
import org.koin.dsl.module
import root.domain.UpdateProfileUseCase

internal fun editingModule() = module {
    factory { UpdateProfileUseCase(get()) }
    factory<GetMaskedPhoneUseCase> { GetMaskedPhoneUseCaseImpl() }
}