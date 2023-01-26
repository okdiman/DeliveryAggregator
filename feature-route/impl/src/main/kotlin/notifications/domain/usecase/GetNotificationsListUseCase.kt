package notifications.domain.usecase

import notifications.domain.NotificationsRepository

class GetNotificationsListUseCase(
    private val repository: NotificationsRepository
) {
    suspend operator fun invoke() {
        repository.getNotifications()
    }
}