package notifications.presentation.mapper

import notifications.NotificationsConstant
import notifications.data.mapper.RouteNotificationBodyMapper
import notifications.data.mapper.RouteNotificationIconMapper
import notifications.domain.model.NotificationServerAssignedRequestDataModel
import notifications.domain.model.NotificationServerDataModel
import notifications.domain.model.NotificationServerModel
import notifications.domain.model.NotificationServerRequestDataModel
import notifications.domain.model.NotificationServerRouteDataModel
import notifications.domain.model.RouteNotificationsStatus
import notifications.presentation.compose.model.NotificationAssignedRequestUiModel
import notifications.presentation.compose.model.NotificationBasicUiModel
import notifications.presentation.compose.model.NotificationUiModel
import utils.CommonConstants.Helpers.SPACER
import utils.ext.DateFormats
import utils.ext.toString

class NotificationUiMapper(
    private val notificationIconMapper: RouteNotificationIconMapper,
    private val notificationBodyMapper: RouteNotificationBodyMapper
) {
    fun map(model: NotificationServerModel): NotificationUiModel {
        return when (model.data) {
            is NotificationServerRouteDataModel -> mapToRouteNotification(model)
            is NotificationServerRequestDataModel -> mapToRequestNotification(model)
            is NotificationServerAssignedRequestDataModel -> mapToAssignedRequestNotification(model)
        }
    }

    private fun mapToRequestNotification(model: NotificationServerModel): NotificationBasicUiModel {
        val data = model.data as NotificationServerRequestDataModel
        val notificationMap = buildNotificationMap(data.orderId, data)
        return NotificationBasicUiModel(
            notificationId = model.id,
            routeId = data.orderId,
            text = notificationBodyMapper.mapToAnnotated(notificationMap),
            imageRes = notificationIconMapper(notificationMap),
            status = mapStatus(data.status),
        )
    }

    private fun mapToAssignedRequestNotification(model: NotificationServerModel): NotificationAssignedRequestUiModel {
        val data = model.data as NotificationServerAssignedRequestDataModel
        val notificationMap = buildNotificationMap(data.orderId, data)
        return NotificationAssignedRequestUiModel(
            notificationId = model.id,
            routeId = data.orderId,
            text = notificationBodyMapper.mapToAnnotated(notificationMap),
            imageRes = notificationIconMapper(notificationMap),
            status = mapStatus(data.status),
            fullName = buildString { append(data.surname + SPACER + data.name + SPACER + data.secondName) },
            phone = data.phone,
            carPlate = data.carPlate,
            carModel = data.carModel,
            arrivalTime = data.arrivalTime,
            arrivalDay = data.arrivalDay.toString(DateFormats.DOT_DAY_FORMAT)
        )
    }

    private fun mapToRouteNotification(model: NotificationServerModel): NotificationBasicUiModel {
        val data = model.data as NotificationServerRouteDataModel
        val notificationMap = buildNotificationMap(data.routeId, data)
        return NotificationBasicUiModel(
            notificationId = model.id,
            routeId = data.routeId,
            text = notificationBodyMapper.mapToAnnotated(notificationMap),
            imageRes = notificationIconMapper(notificationMap),
            status = mapStatus(data.status),
        )
    }

    private fun mapStatus(status: String?) = RouteNotificationsStatus.values().firstOrNull { it.status == status }

    private fun buildNotificationMap(routeId: Long?, data: NotificationServerDataModel): Map<String, String> {
        val notificationMap = mutableMapOf(
            NotificationsConstant.Route.STATUS to data.status
        )
        routeId?.let {
            notificationMap[NotificationsConstant.Route.ROUTE_ID] = it.toString()
        }
        data.date?.let {
            notificationMap[NotificationsConstant.Route.DATE] = it
        }
        return notificationMap
    }
}
