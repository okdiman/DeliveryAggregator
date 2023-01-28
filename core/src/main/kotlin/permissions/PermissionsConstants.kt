package permissions

import android.Manifest
import android.os.Build
import androidx.annotation.RequiresApi

object PermissionsConstants {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    const val Notification = Manifest.permission.POST_NOTIFICATIONS
}