package notifications.domain.model

class NotificationServerDataModel(
    val type: String,
    val date: String?,
    val routeId: Int?,
    val status: String,
    val isRead: Boolean
)