package notifications.root.domain.usecase

import notifications.root.domain.repository.NotificationsRepository

class MarkNotificationsAsReadUseCase(private val repository: NotificationsRepository) {
    suspend operator fun invoke(notifications: List<Long>) {
        repository.markNotificationsAsRead(notifications)
    }
}