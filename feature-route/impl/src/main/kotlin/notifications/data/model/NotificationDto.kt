package notifications.data.model

class NotificationDto(
    val id: Long,
    val data: NotificationDataDto,
    val body: String?,
    val title: String?
)