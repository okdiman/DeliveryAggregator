package notifications.root.data.repository

import notifications.root.data.NotificationsApi
import notifications.root.domain.repository.NotificationsRepository

class NotificationsRepositoryImpl(
    private val api: NotificationsApi
) : NotificationsRepository {
    override suspend fun getNotifications() {
        api.getNotifications()
    }
}