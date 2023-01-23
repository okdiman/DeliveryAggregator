package trinity_monsters.delivery_aggregator.notifications

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class GoogleMessagingService : FirebaseMessagingService() {
    override fun onNewToken(token: String) {
        Log.e("asdasd", "$token")
    }

    override fun onMessageReceived(message: RemoteMessage) {

    }
}