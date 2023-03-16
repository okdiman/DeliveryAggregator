package notifications.data.repository

import notifications.data.NotificationsApi
import notifications.data.mapper.NotificationMapper
import notifications.data.model.request.NotificationsMarkedDto
import notifications.domain.model.NotificationServerModel
import notifications.domain.repository.NotificationsRepository

class NotificationsRepositoryImpl(
    private val api: NotificationsApi,
    private val mapper: NotificationMapper
) : NotificationsRepository {

    override suspend fun getUnreadNotificationsCount() = api.getUnreadNotificationsCount().unread

    override suspend fun getNotifications(): Array<NotificationServerModel> {
        val response = api.getNotifications()
        return mapper.map(response) ?: emptyArray()
    }

    override suspend fun markNotificationsAsRead(notifications: List<Long>) {
        api.markNotificationsAsRead(NotificationsMarkedDto(ids = notifications))
    }
}
