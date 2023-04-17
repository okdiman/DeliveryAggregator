package notifications.data.mapper

import notifications.NotificationsConstant.DataKeys.STATUS
import notifications.domain.model.RouteNotificationsStatus
import trinity_monsters.delivery_aggregator.core_ui.R

class RouteNotificationIconMapper {
    operator fun invoke(data: Map<String, String>): Int {
        val remoteStatus = data[STATUS].orEmpty()
        return when (RouteNotificationsStatus.values().firstOrNull { it.status == remoteStatus }) {
            RouteNotificationsStatus.NEW -> R.drawable.notifications_issued_ic
            RouteNotificationsStatus.ASSIGNED -> R.drawable.notifications_info_ic
            RouteNotificationsStatus.DELIVERY -> R.drawable.notifications_processing_ic
            RouteNotificationsStatus.CANCELLED -> R.drawable.notifications_cancelled
            RouteNotificationsStatus.CHANGED -> R.drawable.notifications_changes_ic
            RouteNotificationsStatus.DONE -> R.drawable.notifications_done_ic
            else -> R.drawable.notifications_info_ic
        }
    }
}
