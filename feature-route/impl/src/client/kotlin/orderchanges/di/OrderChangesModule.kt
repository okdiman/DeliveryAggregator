package orderchanges.di

import orderchanges.data.mapper.OrderChangesMapper
import orderchanges.domain.usecase.ConfirmOrderChangesUseCase
import orderchanges.domain.usecase.GetOrderChangesUseCase
import orderchanges.presentation.mapper.OrderChangesUiMapper
import org.koin.dsl.module

internal fun orderChangesModule() = module {
    factory { GetOrderChangesUseCase(get()) }
    factory { ConfirmOrderChangesUseCase(get()) }
    factory { OrderChangesUiMapper(get()) }
    factory { OrderChangesMapper(get()) }
}
