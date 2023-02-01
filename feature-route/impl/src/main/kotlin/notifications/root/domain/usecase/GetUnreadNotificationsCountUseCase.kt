package notifications.root.domain.usecase

import notifications.root.domain.repository.NotificationsRepository

class GetUnreadNotificationsCountUseCase(private val repository: NotificationsRepository) {
    suspend operator fun invoke() = repository.getUnreadNotificationsCount()
}