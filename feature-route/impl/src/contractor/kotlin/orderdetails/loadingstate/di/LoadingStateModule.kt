package orderdetails.loadingstate.di

import extras.domain.GetExtrasUseCase
import extras.presentation.mapper.ExtrasUiMapper
import orderdetails.loadingstate.data.mapper.LoadingStateMapper
import orderdetails.loadingstate.domain.ConfirmLoadingStateUseCase
import org.koin.dsl.module

internal fun loadingStateModule() = module {
    factory { LoadingStateMapper() }
    factory { ConfirmLoadingStateUseCase(get()) }
    factory { GetExtrasUseCase(get()) }
    factory { ExtrasUiMapper() }
}