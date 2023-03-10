package notifications.domain.model

class NotificationServerModel(
    val id: Long,
    val data: NotificationServerDataModel,
    val body: String?,
    val title: String?,
    val isRead: Boolean,
)
