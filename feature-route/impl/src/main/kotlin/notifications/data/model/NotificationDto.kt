package notifications.data.model

data class NotificationDto(
    val id: Long,
    val data: NotificationDataDto,
    val body: String?,
    val title: String?
)