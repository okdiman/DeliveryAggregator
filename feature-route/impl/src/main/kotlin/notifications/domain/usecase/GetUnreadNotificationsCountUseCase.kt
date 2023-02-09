package notifications.domain.usecase

import notifications.domain.repository.NotificationsRepository

class GetUnreadNotificationsCountUseCase(private val repository: NotificationsRepository) {
    suspend operator fun invoke() = repository.getUnreadNotificationsCount()
}