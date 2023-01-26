package trinity_monsters.delivery_aggregator.notifications.data.manage

import android.annotation.SuppressLint
import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import notifications.domain.model.RouteNotificationsStatus
import trinity_monsters.delivery_aggregator.R
import trinity_monsters.delivery_aggregator.core_ui.R as R_core
import trinity_monsters.delivery_aggregator.notifications.data.model.NotificationsModel
import trinity_monsters.delivery_aggregator.notifications.domain.NotificationsConstant

class NotificationsFactory(
    private val context: Context
) {
    fun create(intent: Intent, remoteMessage: NotificationsModel, channelId: String): Notification {
        return when (channelId) {
            NotificationsConstant.PushChannel.Route.CHANNEL_ID -> {
                NotificationCompat.Builder(context, channelId)
                    .setSmallIcon(R_core.drawable.app_logo)
                    .setCustomContentView(
                        getNotificationView(
                            remoteMessage,
                            NotificationViewType.Small
                        )
                    )
                    .setCustomBigContentView(
                        getNotificationView(
                            remoteMessage,
                            NotificationViewType.Big
                        )
                    )
                    .setAutoCancel(true)
                    .setSound(getSoundUri())
                    .setContentIntent(getOnClickIntent(intent))
                    .build()
            }
            else -> {
                NotificationCompat.Builder(context, channelId)
                    .setSmallIcon(R_core.drawable.app_logo)
                    .setContentTitle(remoteMessage.title)
                    .setContentText(remoteMessage.body)
                    .setAutoCancel(true)
                    .setSound(getSoundUri())
                    .setContentIntent(getOnClickIntent(intent))
                    .build()
            }
        }
    }

    @SuppressLint("UnspecifiedImmutableFlag")
    private fun getOnClickIntent(intent: Intent): PendingIntent {
        val flags = PendingIntent.FLAG_CANCEL_CURRENT or PendingIntent.FLAG_IMMUTABLE
        return PendingIntent.getActivity(context, 0, intent, flags)
    }

    private fun getSoundUri() = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

    private fun getNotificationView(
        remoteMessage: NotificationsModel,
        type: NotificationViewType
    ): RemoteViews {
        val layout = when (type) {
            NotificationViewType.Small -> R.layout.notification_view_small
            NotificationViewType.Big -> R.layout.notification_view_big
        }
        return RemoteViews(context.packageName, layout).apply {
            setTextViewText(R.id.vInfoTextView, remoteMessage.body)
            when (type) {
                NotificationViewType.Small -> setTextViewCompoundDrawables(
                    R.id.vInfoTextView, getNotificationIcon(remoteMessage), 0, 0, 0
                )
                NotificationViewType.Big -> setImageViewResource(
                    R.id.vIconView,
                    getNotificationIcon(remoteMessage)
                )
            }
        }
    }

    private fun getNotificationIcon(remoteMessage: NotificationsModel): Int {
        val remoteStatus = remoteMessage.data[NotificationsConstant.Route.STATUS].orEmpty()
        return when (RouteNotificationsStatus.values().firstOrNull { it.status == remoteStatus }) {
            RouteNotificationsStatus.ASSIGNED -> R_core.drawable.notifications_info_ic
            RouteNotificationsStatus.CANCELLED -> R_core.drawable.notifications_cancelled
            RouteNotificationsStatus.CHANGED -> R_core.drawable.notifications_changes_ic
            RouteNotificationsStatus.DONE -> R_core.drawable.notifications_done_ic
            else -> R_core.drawable.notifications_info_ic
        }
    }
}