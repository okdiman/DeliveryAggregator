package trinity_monsters.delivery_aggregator.notifications.data

import trinity_monsters.delivery_aggregator.notifications.domain.model.NotificationsChannelModel
import trinity_monsters.delivery_aggregator.notifications.domain.NotificationsRepository
import trinity_monsters.delivery_aggregator.notifications.domain.NotificationsConstant

class NotificationsChannelsRepository : NotificationsRepository {
    override fun getNotificationChannels(): List<NotificationsChannelModel> {
        return listOf(
            NotificationsChannelModel(
                NotificationsConstant.PushChannel.Route.CHANNEL_ID,
                NotificationsConstant.PushChannel.Route.CHANNEL_NAME
            ),
            NotificationsChannelModel(
                NotificationsConstant.PushChannel.Info.CHANNEL_ID,
                NotificationsConstant.PushChannel.Info.CHANNEL_NAME
            )
        )
    }
}