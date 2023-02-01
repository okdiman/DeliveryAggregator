package notifications.root.domain.repository

import notifications.root.domain.model.NotificationServerModel

interface NotificationsRepository {
    suspend fun getNotifications(): Array<NotificationServerModel>
    suspend fun getUnreadNotificationsCount(): Int
    suspend fun markNotificationsAsRead(notifications: List<Long>)
}