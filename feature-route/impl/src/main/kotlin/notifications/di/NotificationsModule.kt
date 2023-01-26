package notifications.di

import notifications.data.NotificationsApi
import notifications.data.NotificationsRepositoryImpl
import notifications.domain.NotificationsRepository
import notifications.domain.usecase.GetNotificationsListUseCase
import notifications.domain.usecase.RouteNotificationBodyMapper
import notifications.domain.usecase.RouteNotificationIconMapper
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.create

internal fun notificationsModule() = module {
    single<NotificationsApi> { get<Retrofit>().create() }
    factory<NotificationsRepository> { NotificationsRepositoryImpl(get()) }
    factory { GetNotificationsListUseCase(get()) }
    factory { RouteNotificationBodyMapper(get()) }
    factory { RouteNotificationIconMapper() }
}