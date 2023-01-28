package notifications.root.domain.repository

interface NotificationsRepository {
    suspend fun getNotifications()
}