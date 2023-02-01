package notifications.root.data.repository

import notifications.root.data.NotificationsApi
import notifications.root.data.mapper.NotificationMapper
import notifications.root.data.model.response.NotificationsMarkedDto
import notifications.root.domain.model.NotificationServerModel
import notifications.root.domain.repository.NotificationsRepository

class NotificationsRepositoryImpl(
    private val api: NotificationsApi,
    private val mapper: NotificationMapper
) : NotificationsRepository {

    override suspend fun getUnreadNotificationsCount() = api.getUnreadNotificationsCount().unread
    override suspend fun getNotifications(): Array<NotificationServerModel> {
        val response = api.getNotifications()
        return mapper.map(response)
    }

    override suspend fun markNotificationsAsRead(notifications: List<Long>) {
        api.markNotificationsAsRead(NotificationsMarkedDto(ids = notifications))
    }
}