package notifications.presentation.mapper

import androidx.compose.ui.text.buildAnnotatedString
import notifications.NotificationsConstant.Route.DATE
import notifications.NotificationsConstant.Route.ROUTE_ID
import notifications.NotificationsConstant.Route.STATUS
import notifications.data.mapper.RouteNotificationBodyMapper
import notifications.data.mapper.RouteNotificationIconMapper
import notifications.domain.model.NotificationServerModel
import notifications.domain.model.RouteNotificationsStatus
import notifications.presentation.compose.model.NotificationUiModel

class NotificationUiMapper(
    private val notificationIconMapper: RouteNotificationIconMapper,
    private val notificationBodyMapper: RouteNotificationBodyMapper
) {
    fun map(model: NotificationServerModel): NotificationUiModel {
        return when (model.data.type) {
            ROUTE_TYPE -> mapToRouteNotification(model)
            else -> mapToCommonNotification(model)
        }
    }

    private fun mapToRouteNotification(model: NotificationServerModel): NotificationUiModel {
        val notificationMap = mutableMapOf(
            STATUS to model.data.status
        )
        if (model.data.routeId != null) {
            notificationMap[ROUTE_ID] = model.data.routeId.toString()
        }
        if (model.data.date != null) {
            notificationMap[DATE] = model.data.date
        }
        return NotificationUiModel(
            id = model.id,
            text = notificationBodyMapper.mapToAnnotated(notificationMap),
            imageRes = notificationIconMapper(notificationMap),
            status = RouteNotificationsStatus.values()
                .firstOrNull { it.status == model.data.status }
        )
    }

    private fun mapToCommonNotification(model: NotificationServerModel): NotificationUiModel {
        return NotificationUiModel(
            id = model.id,
            text = buildAnnotatedString {
                append(model.body.toString())
            }
        )
    }

    private companion object {
        const val ROUTE_TYPE = "route"
    }
}