package permissions.domain.interactor

import permissions.domain.PermissionsRepository

class PermissionsInteractor(
    private val repository: PermissionsRepository
) {
    suspend fun isShowRationaleDismissed(permission: String) = repository.isShowRationaleDismissed(permission)
    suspend fun setRationaleDismissed(permission: String) {
        repository.setRationaleDismissed(permission)
    }

    fun isPermissionGranted(permission: String) = repository.isPermissionGranted(permission)
}