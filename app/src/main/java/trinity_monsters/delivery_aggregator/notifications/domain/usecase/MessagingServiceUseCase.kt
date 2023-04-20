package trinity_monsters.delivery_aggregator.notifications.domain.usecase

import notifications.NotificationsConstant
import notifications.NotificationsConstant.Types
import trinity_monsters.delivery_aggregator.notifications.data.model.NotificationsModel
import trinity_monsters.delivery_aggregator.notifications.data.strategy.NotificationsStrategy
import trinity_monsters.delivery_aggregator.notifications.data.strategy.impl.NotificationsCommonStrategy
import trinity_monsters.delivery_aggregator.notifications.data.strategy.impl.NotificationsRouteStrategy

class MessagingServiceUseCase(
    private val commonStrategy: NotificationsCommonStrategy,
    private val routeStrategy: NotificationsRouteStrategy
) {
    operator fun invoke(model: NotificationsModel): NotificationsStrategy {
        return when (model.data[NotificationsConstant.PUSH_TYPE]) {
            Types.ROUTE, Types.REQUEST, Types.ASSIGNED_REQUEST -> routeStrategy
            else -> commonStrategy
        }
    }
}
