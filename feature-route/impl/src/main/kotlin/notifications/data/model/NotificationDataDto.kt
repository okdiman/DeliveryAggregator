package notifications.data.model

import com.google.gson.annotations.SerializedName

class NotificationDataDto(
    val type: String,
    val date: String?,
    val routeId: Int?,
    val status: String?,
    @SerializedName("is_read")
    val isRead: Boolean
)