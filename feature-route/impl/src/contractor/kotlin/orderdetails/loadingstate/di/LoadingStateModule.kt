package orderdetails.loadingstate.di

import orderdetails.loadingstate.data.mapper.LoadingStateMapper
import orderdetails.loadingstate.domain.ConfirmLoadingStateUseCase
import orderdetails.loadingstate.domain.GetExtrasUseCase
import orderdetails.loadingstate.presentation.mapper.ExtrasUiMapper
import org.koin.dsl.module

internal fun loadingStateModule() = module {
    factory { LoadingStateMapper() }
    factory { ConfirmLoadingStateUseCase(get()) }
    factory { GetExtrasUseCase(get()) }
    factory { ExtrasUiMapper() }
}