package notifications.root.di

import notifications.permission.data.NotificationPermissionRepositoryImpl
import notifications.permission.data.datasource.NotificationPermissionDataSource
import notifications.permission.domain.NotificationPermissionRepository
import notifications.permission.domain.interactor.NotificationPermissionInteractor
import notifications.root.data.NotificationsApi
import notifications.root.data.repository.NotificationsRepositoryImpl
import notifications.root.domain.repository.NotificationsRepository
import notifications.root.domain.usecase.GetNotificationsListUseCase
import notifications.root.domain.usecase.RouteNotificationBodyMapper
import notifications.root.domain.usecase.RouteNotificationIconMapper
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.create

internal fun notificationsModule() = module {
    single<NotificationsApi> { get<Retrofit>().create() }
    factory<NotificationsRepository> { NotificationsRepositoryImpl(get()) }
    factory { GetNotificationsListUseCase(get()) }
    factory { RouteNotificationBodyMapper(get()) }
    factory { RouteNotificationIconMapper() }
    factory { NotificationPermissionDataSource(get(), get()) }
    factory { NotificationPermissionInteractor(get()) }
    factory<NotificationPermissionRepository> { NotificationPermissionRepositoryImpl(get()) }
}