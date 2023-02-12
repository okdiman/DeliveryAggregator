package orderdetails.deliverystate.di

import orderdetails.deliverystate.data.mapper.DeliveryStateMapper
import orderdetails.deliverystate.domain.ConfirmDeliveryStateUseCase
import org.koin.dsl.module

internal fun deliveryStateModule() = module {
    factory { DeliveryStateMapper() }
    factory { ConfirmDeliveryStateUseCase(get()) }
}