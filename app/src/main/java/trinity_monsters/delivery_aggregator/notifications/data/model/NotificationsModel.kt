package trinity_monsters.delivery_aggregator.notifications.data.model

import notifications.NotificationsConstant.DataKeys.REQUEST_ID
import notifications.NotificationsConstant.DataKeys.ROUTE_ID

data class NotificationsModel(
    val data: Map<String, String>,
    val pushId: String,
    val title: String,
    val body: String
) {
    fun id(): Long? = (data[ROUTE_ID] ?: data[REQUEST_ID])?.toLongOrNull()
}
