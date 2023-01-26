package notifications.data

import notifications.domain.NotificationsRepository

class NotificationsRepositoryImpl(
    private val api: NotificationsApi
) : NotificationsRepository {
    override suspend fun getNotifications() {
        api.getNotifications()
    }
}