package trinity_monsters.delivery_aggregator.notifications.data

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import trinity_monsters.delivery_aggregator.notifications.data.mapper.NotificationsFBMapper
import trinity_monsters.delivery_aggregator.notifications.domain.usecase.MessagingServiceUseCase
import trinity_monsters.delivery_aggregator.root.domain.SavePushTokenUseCase

class NotificationsService : FirebaseMessagingService(), KoinComponent {
    private val savePushToken by inject<SavePushTokenUseCase>()
    private val messagingService by inject<MessagingServiceUseCase>()
    private val mapper by inject<NotificationsFBMapper>()

    override fun onNewToken(token: String) {
        Log.e("asdasd", token)
        savePushToken(token)
    }

    override fun onMessageReceived(message: RemoteMessage) {
        val model = mapper(message)
        messagingService(model).handle(model)
    }
}