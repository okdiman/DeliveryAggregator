package orderdetails.root.di

import orderdetails.root.domain.GetOrderDetailsUseCase
import orderdetails.root.presentation.mapper.OrderDetailsMapper
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

internal fun orderDetailsModule() = module {
    factoryOf(::GetOrderDetailsUseCase)
    factoryOf(::OrderDetailsMapper)
}