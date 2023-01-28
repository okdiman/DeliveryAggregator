package notifications.permission.domain

interface NotificationPermissionRepository {
    suspend fun setRationaleDismissed()
    suspend fun isShowRationaleDismissed(): Boolean
}