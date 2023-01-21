package additionalInfo.di

import additionalInfo.presentation.mapper.AdditionalInfoUiMapper
import org.koin.dsl.module

internal fun additionalInfoModule() = module {
    factory { AdditionalInfoUiMapper() }
}