package trinity_monsters.delivery_aggregator.notifications.data.manage

import android.annotation.SuppressLint
import android.app.Notification
import android.os.SystemClock
import androidx.core.app.NotificationManagerCompat

class NotificationsManager(
    private val notificationManager: NotificationManagerCompat
) {

    @SuppressLint("MissingPermission")
    fun show(notification: Notification) {
        val notificationUniqueId = SystemClock.elapsedRealtime().toInt()
        notificationManager.notify(notificationUniqueId, notification)
    }

    fun clearAllNotifications() {
        notificationManager.cancelAll()
    }
}