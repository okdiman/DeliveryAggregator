package notifications.permission.data.datasource

import android.content.SharedPreferences
import coroutines.AppDispatchers
import kotlinx.coroutines.withContext

class NotificationPermissionDataSource(
    private val sharedPreferences: SharedPreferences,
    private val dispatchers: AppDispatchers
) {
    suspend fun setRationaleDismissed() {
        return withContext(dispatchers.storage) {
            sharedPreferences.edit().putBoolean(PERMISSION_STATE, true).apply()
        }
    }

    suspend fun isShowRationaleDismissed(): Boolean {
        return withContext(dispatchers.storage) {
            sharedPreferences.getBoolean(PERMISSION_STATE, false)
        }
    }

    private companion object {
        const val PERMISSION_STATE = "notification_rationale_dismissed"
    }
}