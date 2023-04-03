package notifications.data.model.request

import kotlinx.serialization.Serializable

@Serializable
class NotificationsMarkedDto(
    val ids: List<Long>
)
