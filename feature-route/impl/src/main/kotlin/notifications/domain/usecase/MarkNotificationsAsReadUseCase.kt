package notifications.domain.usecase

import notifications.domain.repository.NotificationsRepository

class MarkNotificationsAsReadUseCase(private val repository: NotificationsRepository) {
    suspend operator fun invoke(notifications: List<Long>) {
        repository.markNotificationsAsRead(notifications)
    }
}