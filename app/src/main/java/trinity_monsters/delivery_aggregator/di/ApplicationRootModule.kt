package trinity_monsters.delivery_aggregator.di

import com.google.firebase.messaging.FirebaseMessaging
import domain.usecase.notifications.GetNewFCMTokenUseCase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import trinity_monsters.delivery_aggregator.notifications.data.mapper.NotificationsFBMapper
import trinity_monsters.delivery_aggregator.notifications.data.strategy.impl.NotificationsCommonStrategy
import trinity_monsters.delivery_aggregator.notifications.data.strategy.impl.NotificationsRouteStrategy
import trinity_monsters.delivery_aggregator.notifications.domain.usecase.MessagingServiceUseCase
import trinity_monsters.delivery_aggregator.root.domain.GetNewFCMTokenUseCaseImpl
import trinity_monsters.delivery_aggregator.root.domain.SavePushTokenUseCase
import utils.ext.processLifecycleScope

internal fun applicationRootModule() = module {
    factory { SavePushTokenUseCase(processLifecycleScope, get(), get()) }
    factory { MessagingServiceUseCase(get(), get()) }
    factory { NotificationsCommonStrategy(androidApplication(), get(), get()) }
    factory { NotificationsRouteStrategy(androidApplication(), get(), get()) }
    factory { NotificationsFBMapper() }
    factory<GetNewFCMTokenUseCase> {
        GetNewFCMTokenUseCaseImpl(
            FirebaseMessaging.getInstance(),
            get()
        )
    }
}