package trinity_monsters.delivery_aggregator.notifications.domain

import trinity_monsters.delivery_aggregator.notifications.domain.model.NotificationsChannelModel

interface NotificationsRepository {
    fun getNotificationChannels(): List<NotificationsChannelModel>
}