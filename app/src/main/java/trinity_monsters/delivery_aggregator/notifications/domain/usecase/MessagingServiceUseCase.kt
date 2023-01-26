package trinity_monsters.delivery_aggregator.notifications.domain.usecase

import trinity_monsters.delivery_aggregator.notifications.data.model.NotificationsModel
import trinity_monsters.delivery_aggregator.notifications.data.strategy.NotificationsStrategy
import trinity_monsters.delivery_aggregator.notifications.data.strategy.impl.NotificationsCommonStrategy
import trinity_monsters.delivery_aggregator.notifications.data.strategy.impl.NotificationsRouteStrategy
import notifications.NotificationsConstant

class MessagingServiceUseCase(
    private val commonStrategy: NotificationsCommonStrategy,
    private val routeStrategy: NotificationsRouteStrategy
) {
    operator fun invoke(model: NotificationsModel): NotificationsStrategy {
        return when (model.data[NotificationsConstant.PUSH_TYPE]) {
            NotificationsConstant.PushChannel.Route.CHANNEL_ID -> routeStrategy
            else -> commonStrategy
        }
    }
}