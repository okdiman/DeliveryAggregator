package notifications.domain.model

import orderdetails.root.domain.model.OrderDetailsModel

class NotificationServerModel(
    val id: Long,
    val data: NotificationServerDataModel,
    val body: String?,
    val title: String?,
    val isRead: Boolean,
    var associatedOrder: OrderDetailsModel? = null,
)
