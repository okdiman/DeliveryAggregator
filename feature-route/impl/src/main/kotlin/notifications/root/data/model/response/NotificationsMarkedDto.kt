package notifications.root.data.model.response

import kotlinx.serialization.Serializable

@Serializable
class NotificationsMarkedDto(
    val ids: List<Long>
)