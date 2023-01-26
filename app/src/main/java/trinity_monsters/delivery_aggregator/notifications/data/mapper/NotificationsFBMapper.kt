package trinity_monsters.delivery_aggregator.notifications.data.mapper

import com.google.firebase.messaging.RemoteMessage
import trinity_monsters.delivery_aggregator.notifications.data.model.NotificationsModel

class NotificationsFBMapper {
    operator fun invoke(message: RemoteMessage): NotificationsModel {
        return NotificationsModel(
            data = message.data,
            pushId = message.messageId.orEmpty(),
            title = message.data[TITLE_KEY] ?: message.notification?.title.orEmpty(),
            body = message.data[BODY_KEY] ?: message.notification?.body.orEmpty()
        )
    }

    companion object {
        private const val TITLE_KEY = "title"
        private const val BODY_KEY = "body"
    }
}