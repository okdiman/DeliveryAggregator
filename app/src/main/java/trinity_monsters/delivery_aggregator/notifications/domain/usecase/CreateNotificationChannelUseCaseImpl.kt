package trinity_monsters.delivery_aggregator.notifications.domain.usecase

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.core.app.NotificationManagerCompat
import notifications.CreateNotificationChannelUseCase
import trinity_monsters.delivery_aggregator.notifications.domain.NotificationsRepository

class CreateNotificationChannelUseCaseImpl(
    private val notificationManager: NotificationManagerCompat,
    private val repository: NotificationsRepository
) : CreateNotificationChannelUseCase {

    override fun invoke() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            repository.getNotificationChannels().forEach { channelData ->
                val channel = NotificationChannel(
                    channelData.channelId,
                    channelData.channelName,
                    NotificationManager.IMPORTANCE_HIGH
                ).apply {
                    enableVibration(true)
                }
                notificationManager.createNotificationChannel(channel)
            }
        }
    }
}