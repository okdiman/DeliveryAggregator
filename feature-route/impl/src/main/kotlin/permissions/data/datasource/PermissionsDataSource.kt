package permissions.data.datasource

import android.content.Context
import android.content.SharedPreferences
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import coroutines.AppDispatchers
import kotlinx.coroutines.withContext

class PermissionsDataSource(
    private val context: Context,
    private val sharedPreferences: SharedPreferences,
    private val dispatchers: AppDispatchers
) {
    suspend fun setRationaleDismissed(permission: String) {
        return withContext(dispatchers.storage) {
            sharedPreferences.edit().putBoolean(permission, true).apply()
        }
    }

    suspend fun isShowRationaleDismissed(permission: String): Boolean {
        return withContext(dispatchers.storage) {
            sharedPreferences.getBoolean(permission, false)
        }
    }

    fun isPermissionGranted(permission: String) =
        ActivityCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED
}