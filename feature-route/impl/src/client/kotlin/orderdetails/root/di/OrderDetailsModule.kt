package orderdetails.root.di

import orderdetails.root.domain.GetClientOrderDetailsUseCase
import orderdetails.root.presentation.mapper.OrderDetailsMapper
import org.koin.dsl.module

internal fun orderDetailsModule() = module {
    factory { GetClientOrderDetailsUseCase(get()) }
    factory { OrderDetailsMapper() }
}
