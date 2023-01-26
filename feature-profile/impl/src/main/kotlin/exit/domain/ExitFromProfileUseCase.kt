package exit.domain

import domain.ProfileRepository
import domain.usecase.ClearAllNotificationsUseCase

class ExitFromProfileUseCase(
    private val clearAllNotifications: ClearAllNotificationsUseCase,
    private val repository: ProfileRepository
) {
    suspend operator fun invoke(isDeleting: Boolean = false) {
        clearAllNotifications()
        if (isDeleting) {
            repository.deleteProfile()
        } else {
            repository.exitFromProfile()
        }
    }
}