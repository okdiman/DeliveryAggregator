package notifications.data.mapper

import notifications.data.model.response.NotificationAssignedRequestDataDto
import notifications.data.model.response.NotificationDataDto
import notifications.data.model.response.NotificationRequestDataDto
import notifications.data.model.response.NotificationRouteDataDto
import notifications.data.model.response.NotificationsDto
import notifications.domain.model.NotificationServerAssignedRequestDataModel
import notifications.domain.model.NotificationServerDataModel
import notifications.domain.model.NotificationServerModel
import notifications.domain.model.NotificationServerRequestDataModel
import notifications.domain.model.NotificationServerRouteDataModel
import org.threeten.bp.format.DateTimeFormatter
import utils.ext.toLocalZonedDateTime

class NotificationMapper {

    fun map(dto: NotificationsDto) = dto.notifications?.map { dtoModel ->
        NotificationServerModel(
            id = dtoModel.id,
            body = dtoModel.body,
            title = dtoModel.title,
            data = mapNotificationData(dtoModel.data),
            isRead = dtoModel.isRead,
        )
    }?.toTypedArray()

    private fun mapNotificationData(dtoData: NotificationDataDto): NotificationServerDataModel {
        return when (dtoData) {
            is NotificationRouteDataDto -> NotificationServerRouteDataModel(
                routeId = dtoData.routeId,
                type = dtoData.type,
                date = dtoData.date,
                status = dtoData.status.orEmpty(),
            )
            is NotificationRequestDataDto -> NotificationServerRequestDataModel(
                orderId = dtoData.orderId,
                type = dtoData.type,
                date = dtoData.date,
                status = dtoData.status.orEmpty(),
            )
            is NotificationAssignedRequestDataDto -> NotificationServerAssignedRequestDataModel(
                orderId = dtoData.orderId,
                contractorId = dtoData.contractorId,
                surname = dtoData.surname,
                name = dtoData.name,
                secondName = dtoData.secondName,
                phone = dtoData.phone,
                carPlate = dtoData.carPlate,
                carModel = dtoData.carModel,
                arrivalTime = dtoData.arrivalTime,
                arrivalDay = dtoData.arrivalDay.toLocalZonedDateTime(DateTimeFormatter.ISO_DATE_TIME),
                type = dtoData.type,
                date = dtoData.date,
                status = dtoData.status.orEmpty(),
            )
        }
    }
}
