package trinity_monsters.delivery_aggregator.di

import com.google.firebase.messaging.FirebaseMessaging
import domain.usecase.notifications.GetNewFCMTokenUseCase
import org.koin.dsl.module
import trinity_monsters.delivery_aggregator.root.domain.GetNewFCMTokenUseCaseImpl
import trinity_monsters.delivery_aggregator.root.domain.SavePushTokenUseCase
import utils.processLifecycleScope

internal fun applicationRootModule() = module {
    factory { SavePushTokenUseCase(processLifecycleScope, get(), get()) }
    factory<GetNewFCMTokenUseCase> {
        GetNewFCMTokenUseCaseImpl(
            FirebaseMessaging.getInstance(),
            get()
        )
    }
}