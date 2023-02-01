package notifications.root.data.mapper

import notifications.root.data.model.NotificationsDto
import notifications.root.domain.model.NotificationServerDataModel
import notifications.root.domain.model.NotificationServerModel

class NotificationMapper {
    fun map(dto: NotificationsDto) = dto.notifications.map { dtoModel ->
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
    }.toTypedArray()
}