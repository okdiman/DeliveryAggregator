package notifications.data.model.response

import kotlinx.serialization.Serializable

@Serializable
class NotificationsDto(
    val notifications: ArrayList<NotificationDto>?
)
