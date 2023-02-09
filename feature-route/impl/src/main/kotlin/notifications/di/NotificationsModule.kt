package notifications.di

import notifications.data.NotificationsApi
import notifications.data.mapper.NotificationMapper
import notifications.data.mapper.RouteNotificationBodyMapper
import notifications.data.mapper.RouteNotificationIconMapper
import notifications.data.repository.NotificationsRepositoryImpl
import notifications.domain.repository.NotificationsRepository
import notifications.domain.usecase.GetNotificationsListUseCase
import notifications.domain.usecase.GetUnreadNotificationsCountUseCase
import notifications.domain.usecase.MarkNotificationsAsReadUseCase
import notifications.presentation.mapper.NotificationUiMapper
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.create

internal fun notificationsModule() = module {
    single<NotificationsApi> { get<Retrofit>().create() }
    factory<NotificationsRepository> { NotificationsRepositoryImpl(get(), get()) }
    factory { GetNotificationsListUseCase(get()) }
    factory { GetUnreadNotificationsCountUseCase(get()) }
    factory { MarkNotificationsAsReadUseCase(get()) }
    factory { RouteNotificationBodyMapper(get()) }
    factory { RouteNotificationIconMapper() }
    factory { NotificationUiMapper(get(), get()) }
    factory { NotificationMapper() }
}