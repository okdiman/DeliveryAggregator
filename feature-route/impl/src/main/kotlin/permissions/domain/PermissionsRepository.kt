package permissions.domain

interface PermissionsRepository {
    suspend fun setRationaleDismissed(permission: String)
    suspend fun isShowRationaleDismissed(permission: String): Boolean
    fun isPermissionGranted(permission: String): Boolean
}