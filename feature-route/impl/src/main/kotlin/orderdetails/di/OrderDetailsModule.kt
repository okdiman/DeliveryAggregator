package orderdetails.di

import orderdetails.domain.GetOrderDetailsUseCase
import orderdetails.presentation.mapper.OrderDetailsMapper
import org.koin.dsl.module

internal fun orderDetailsModule() = module {
    factory { GetOrderDetailsUseCase(get()) }
    factory { OrderDetailsMapper() }
}