package trinity_monsters.delivery_aggregator.notifications.di

import androidx.core.app.NotificationManagerCompat
import domain.usecase.ClearAllNotificationsUseCase
import notifications.CreateNotificationChannelUseCase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import trinity_monsters.delivery_aggregator.notifications.data.NotificationsChannelsRepository
import trinity_monsters.delivery_aggregator.notifications.data.manage.NotificationsFactory
import trinity_monsters.delivery_aggregator.notifications.data.manage.NotificationsManager
import trinity_monsters.delivery_aggregator.notifications.domain.NotificationsRepository
import trinity_monsters.delivery_aggregator.notifications.domain.usecase.ClearAllNotificationsUseCaseImpl
import trinity_monsters.delivery_aggregator.notifications.domain.usecase.CreateNotificationChannelUseCaseImpl

internal fun notificationsModule() = module {
    factory<NotificationsRepository> { NotificationsChannelsRepository() }
    single { NotificationManagerCompat.from(androidContext()) }
    factory<CreateNotificationChannelUseCase> {
        CreateNotificationChannelUseCaseImpl(
            notificationManager = get(),
            repository = get()
        )
    }
    factory { NotificationsFactory(androidContext()) }
    factory { NotificationsManager(get()) }
    factory<ClearAllNotificationsUseCase> { ClearAllNotificationsUseCaseImpl(get()) }
}