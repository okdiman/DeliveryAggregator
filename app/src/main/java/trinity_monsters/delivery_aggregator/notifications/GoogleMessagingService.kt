package trinity_monsters.delivery_aggregator.notifications

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import trinity_monsters.delivery_aggregator.root.domain.SavePushTokenUseCase

class GoogleMessagingService : FirebaseMessagingService(), KoinComponent {
    private val savePushToken by inject<SavePushTokenUseCase>()
    override fun onNewToken(token: String) {
        Log.e("asdasd", "$token")
        savePushToken(token)
    }

    override fun onMessageReceived(message: RemoteMessage) {

    }
}