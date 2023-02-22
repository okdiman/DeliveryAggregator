package root.di

import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.create
import root.data.OrderApi
import root.data.OrderRequestsRepositoryImpl
import root.data.mapper.OrderMapper
import root.domain.OrderRequestsRepository
import root.domain.usecase.GetOrderRequestsUseCase
import root.presentation.mapper.OrderRequestsUiMapper

internal fun orderRequestsRootModule() = module {
    single<OrderApi> { get<Retrofit>().create() }
    factory<OrderRequestsRepository> { OrderRequestsRepositoryImpl(get(), get()) }
    factory { GetOrderRequestsUseCase(get()) }
    factory { OrderRequestsUiMapper() }
    factory { OrderMapper() }
}
