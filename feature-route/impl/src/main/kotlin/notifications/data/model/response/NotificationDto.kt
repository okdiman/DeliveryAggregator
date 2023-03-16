package notifications.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NotificationDto(
    val id: Long,
    val data: NotificationDataDto,
    val title: String?,
    val body: String?,
    @SerialName("is_read")
    val isRead: Boolean,
)
