package notifications.data.mapper

import notifications.data.model.NotificationsDto
import notifications.domain.model.NotificationServerDataModel
import notifications.domain.model.NotificationServerModel

class NotificationMapper {
    fun map(dto: NotificationsDto) = dto.notifications?.map { dtoModel ->
        NotificationServerModel(
            id = dtoModel.id,
            body = dtoModel.body,
            title = dtoModel.title,
            data = NotificationServerDataModel(
                type = dtoModel.data.type,
                status = dtoModel.data.status.orEmpty(),
                isRead = dtoModel.data.isRead,
                date = dtoModel.data.date,
                routeId = dtoModel.data.routeId
            )
        )
    }?.toTypedArray()
}