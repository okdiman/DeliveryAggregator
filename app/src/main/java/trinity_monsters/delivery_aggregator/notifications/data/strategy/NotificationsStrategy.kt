package trinity_monsters.delivery_aggregator.notifications.data.strategy

import trinity_monsters.delivery_aggregator.notifications.data.model.NotificationsModel

interface NotificationsStrategy {
    fun handle(model: NotificationsModel)
}