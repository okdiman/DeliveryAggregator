package trinity_monsters.delivery_aggregator.di

import android.content.ClipboardManager
import android.content.Context
import com.google.firebase.messaging.FirebaseMessaging
import domain.usecase.notifications.GetNewFCMTokenUseCase
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import trinity_monsters.delivery_aggregator.notifications.data.mapper.NotificationsFBMapper
import trinity_monsters.delivery_aggregator.notifications.data.strategy.impl.NotificationsCommonStrategy
import trinity_monsters.delivery_aggregator.notifications.data.strategy.impl.NotificationsRouteStrategy
import trinity_monsters.delivery_aggregator.notifications.domain.usecase.MessagingServiceUseCase
import trinity_monsters.delivery_aggregator.root.domain.GetNewFCMTokenUseCaseImpl
import trinity_monsters.delivery_aggregator.root.domain.SavePushTokenUseCase
import trinity_monsters.delivery_aggregator.root.presentation.MainViewModel
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
    viewModel { MainViewModel(get()) }
    single { androidContext().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager }
}