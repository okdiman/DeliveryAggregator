package orderdetails.root.di

import deleteorder.domain.DeleteOrderUseCase
import orderdetails.root.domain.GetClientOrderDetailsUseCase
import orderdetails.root.presentation.mapper.OrderDetailsMapper
import org.koin.dsl.module

internal fun orderDetailsModule() = module {
    factory { GetClientOrderDetailsUseCase(get()) }
    factory { OrderDetailsMapper() }
    factory { DeleteOrderUseCase(get()) }
}