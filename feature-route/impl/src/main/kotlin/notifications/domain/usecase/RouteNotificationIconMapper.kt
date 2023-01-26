package notifications.domain.usecase

import notifications.NotificationsConstant
import notifications.domain.model.RouteNotificationsStatus
import trinity_monsters.delivery_aggregator.core_ui.R

class RouteNotificationIconMapper {
    operator fun invoke(data: Map<String, String>): Int {
        val remoteStatus = data[NotificationsConstant.Route.STATUS].orEmpty()
        return when (RouteNotificationsStatus.values().firstOrNull { it.status == remoteStatus }) {
            RouteNotificationsStatus.ASSIGNED -> R.drawable.notifications_info_ic
            RouteNotificationsStatus.CANCELLED -> R.drawable.notifications_cancelled
            RouteNotificationsStatus.CHANGED -> R.drawable.notifications_changes_ic
            RouteNotificationsStatus.DONE -> R.drawable.notifications_done_ic
            else -> R.drawable.notifications_info_ic
        }
    }
}