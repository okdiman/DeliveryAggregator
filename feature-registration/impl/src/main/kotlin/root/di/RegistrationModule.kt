package root.di

import org.koin.dsl.module
import user.presentation.mapper.SignUpModelMapper

fun registrationModule() = module {
    factory { SignUpModelMapper() }
}