package notifications.domain.usecase

import notifications.domain.repository.NotificationsRepository

class GetNotificationsListUseCase(
    private val repository: NotificationsRepository
) {
    suspend operator fun invoke() = repository.getNotifications()
}