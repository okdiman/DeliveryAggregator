package orderdetails.loadingstate.di

import orderdetails.loadingstate.data.mapper.LoadingStateMapper
import orderdetails.loadingstate.domain.ConfirmLoadingStateUseCase
import org.koin.dsl.module

internal fun loadingStateModule() = module {
    factory { LoadingStateMapper() }
    factory { ConfirmLoadingStateUseCase(get()) }
}