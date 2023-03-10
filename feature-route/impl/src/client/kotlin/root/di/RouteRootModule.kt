package root.di

import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.create
import root.data.OrdersApi
import root.data.OrdersRouteRepositoryImpl
import root.data.mapper.RouteOrderMapper
import root.domain.RouteRepository
import root.domain.usecase.GetOrdersUseCase
import root.presentation.mapper.OrdersUiMapper

internal fun routeRootModule() = module {
    single<OrdersApi> { get<Retrofit>().create() }
    factory<RouteRepository> { OrdersRouteRepositoryImpl(get(), get(), get(), get()) }
    factory { RouteOrderMapper() }
    factory { GetOrdersUseCase(get()) }
    factory { OrdersUiMapper() }
}
