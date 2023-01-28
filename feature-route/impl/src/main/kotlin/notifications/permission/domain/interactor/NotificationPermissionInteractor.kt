package notifications.permission.domain.interactor

import notifications.permission.domain.NotificationPermissionRepository

class NotificationPermissionInteractor(
    private val repository: NotificationPermissionRepository
) {
    suspend fun isShowRationaleDismissed() = repository.isShowRationaleDismissed()
    suspend fun setRationaleDismissed() {
        repository.setRationaleDismissed()
    }
}