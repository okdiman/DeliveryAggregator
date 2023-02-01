package notifications.root.domain.model

class NotificationServerDataModel(
    val type: String,
    val date: String?,
    val routeId: Int?,
    val status: String,
    val isRead: Boolean
)