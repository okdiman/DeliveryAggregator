package trinity_monsters.delivery_aggregator.notifications.data.manage

import android.annotation.SuppressLint
import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import trinity_monsters.delivery_aggregator.R
import trinity_monsters.delivery_aggregator.core_ui.R as R_core
import trinity_monsters.delivery_aggregator.notifications.data.model.NotificationsModel
import notifications.NotificationsConstant
import notifications.root.domain.usecase.RouteNotificationBodyMapper
import notifications.root.domain.usecase.RouteNotificationIconMapper

class NotificationsFactory(
    private val context: Context,
    private val routeNotificationIconMapper: RouteNotificationIconMapper,
    private val routeNotificationBodyMapper: RouteNotificationBodyMapper
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
            setTextViewText(R.id.vInfoTextView, routeNotificationBodyMapper(remoteMessage.data))
            setImageViewResource(
                R.id.vIconView,
                routeNotificationIconMapper(remoteMessage.data)
            )
        }
    }
}