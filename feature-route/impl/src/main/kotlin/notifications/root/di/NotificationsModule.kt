package notifications.root.di

import notifications.permission.data.NotificationPermissionRepositoryImpl
import notifications.permission.data.datasource.NotificationPermissionDataSource
import notifications.permission.domain.NotificationPermissionRepository
import notifications.permission.domain.interactor.NotificationPermissionInteractor
import notifications.root.data.NotificationsApi
import notifications.root.data.mapper.NotificationMapper
import notifications.root.data.mapper.RouteNotificationBodyMapper
import notifications.root.data.mapper.RouteNotificationIconMapper
import notifications.root.data.repository.NotificationsRepositoryImpl
import notifications.root.domain.repository.NotificationsRepository
import notifications.root.domain.usecase.GetNotificationsListUseCase
import notifications.root.domain.usecase.GetUnreadNotificationsCountUseCase
import notifications.root.domain.usecase.MarkNotificationsAsReadUseCase
import notifications.root.presentation.mapper.NotificationUiMapper
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
    factory { NotificationPermissionDataSource(get(), get()) }
    factory { NotificationPermissionInteractor(get()) }
    factory<NotificationPermissionRepository> { NotificationPermissionRepositoryImpl(get()) }
    factory { NotificationUiMapper(get(), get()) }
    factory { NotificationMapper() }
}