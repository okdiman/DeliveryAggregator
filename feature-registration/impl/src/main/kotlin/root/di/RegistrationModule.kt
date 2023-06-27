package root.di

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import organization.company.domain.GetCompanyInfoUseCase
import user.presentation.mapper.SignUpModelMapper

fun registrationModule() = module {
    factoryOf(::SignUpModelMapper)
    factoryOf(::GetCompanyInfoUseCase)
}