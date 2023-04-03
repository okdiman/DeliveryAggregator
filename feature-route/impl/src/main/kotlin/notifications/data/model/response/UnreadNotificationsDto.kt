package notifications.data.model.response

import kotlinx.serialization.Serializable

@Serializable
class UnreadNotificationsDto(
    val unread: Int
)
