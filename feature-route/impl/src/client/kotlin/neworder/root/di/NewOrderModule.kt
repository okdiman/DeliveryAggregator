package neworder.root.di

import neworder.payment.domain.GetPaymentUriUseCase
import neworder.root.data.mapper.NewOrderMapper
import neworder.root.domain.NewOrderInteractor
import neworder.root.presentation.mapper.NewOrderUiMapper
import org.koin.dsl.module

internal fun newOrderModule() = module {
    factory { NewOrderMapper() }
    factory { NewOrderUiMapper() }
    factory { NewOrderInteractor(get()) }
    factory { GetPaymentUriUseCase(get()) }
}