package root.di

import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.create
import root.DeeplinkNavigatorHandler
import root.data.RouteApi
import root.data.RouteRepositoryImpl
import root.data.mapper.RouteMapper
import root.data.mapper.RouteOrderMapper
import root.domain.RouteRepository
import root.domain.usecase.AcceptRouteUseCase
import root.domain.usecase.GetActiveRouteUseCase
import root.presentation.mapper.RouteButtonUiModelMapper
import root.presentation.mapper.RouteUiMapper

internal fun routeRootModule() = module {
    single<RouteApi> { get<Retrofit>().create() }
    factory<RouteRepository> { RouteRepositoryImpl(get(), get(), get(), get(), get()) }
    factory { GetActiveRouteUseCase(get()) }
    factory { AcceptRouteUseCase(get()) }
    factory { RouteOrderMapper() }
    factory { RouteMapper(get()) }
    factory { RouteUiMapper() }
    factory { RouteButtonUiModelMapper() }
    single { DeeplinkNavigatorHandler() }
}
