package notifications.domain

interface NotificationsRepository {
    suspend fun getNotifications()
}