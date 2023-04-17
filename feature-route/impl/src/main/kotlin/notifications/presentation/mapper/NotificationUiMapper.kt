package notifications.presentation.mapper

import notifications.NotificationsConstant
import notifications.data.mapper.RouteNotificationBodyMapper
import notifications.data.mapper.RouteNotificationIconMapper
import notifications.domain.model.NotificationServerAssignedRequestDataModel
import notifications.domain.model.NotificationServerDataModel
import notifications.domain.model.NotificationServerModel
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
            is NotificationServerAssignedRequestDataModel -> mapToAssignedRequestNotification(model)
            else -> mapToBasicNotification(model)
        }
    }

    private fun mapToBasicNotification(model: NotificationServerModel): NotificationBasicUiModel {
        val notificationMap = buildNotificationMap(model.data.subjectId, model.data)
        return NotificationBasicUiModel(
            notificationId = model.id,
            routeId = model.data.subjectId,
            text = notificationBodyMapper.mapToAnnotated(notificationMap, model.associatedOrder),
            imageRes = notificationIconMapper(notificationMap),
            status = model.data.status,
            orderStatus = model.associatedOrder?.status,
        )
    }

    private fun mapToAssignedRequestNotification(model: NotificationServerModel): NotificationAssignedRequestUiModel {
        val data = model.data as NotificationServerAssignedRequestDataModel
        val notificationMap = buildNotificationMap(data.orderId, data)
        return NotificationAssignedRequestUiModel(
            notificationId = model.id,
            routeId = data.orderId,
            text = notificationBodyMapper.mapToAnnotated(notificationMap, model.associatedOrder),
            imageRes = notificationIconMapper(notificationMap),
            status = data.status,
            orderStatus = model.associatedOrder?.status,
            fullName = buildString { append(data.surname + SPACER + data.name + SPACER + data.secondName) },
            phone = data.phone,
            carPlate = data.carPlate,
            carModel = data.carModel,
            arrivalTime = data.arrivalTime,
            arrivalDay = data.arrivalDay.toString(DateFormats.DOT_DAY_FORMAT)
        )
    }

    private fun buildNotificationMap(routeId: Long?, data: NotificationServerDataModel): Map<String, String> {
        val notificationMap = mutableMapOf(
            NotificationsConstant.DataKeys.STATUS to data.status.status
        )
        routeId?.let {
            notificationMap[NotificationsConstant.DataKeys.ROUTE_ID] = it.toString()
        }
        data.date?.let {
            notificationMap[NotificationsConstant.DataKeys.DATE] = it
        }
        return notificationMap
    }
}
