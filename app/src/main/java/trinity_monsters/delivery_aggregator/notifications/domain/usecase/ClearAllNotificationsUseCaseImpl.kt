package trinity_monsters.delivery_aggregator.notifications.domain.usecase

import domain.usecase.ClearAllNotificationsUseCase
import trinity_monsters.delivery_aggregator.notifications.data.manage.NotificationsManager

class ClearAllNotificationsUseCaseImpl(
    private val notificationsManager: NotificationsManager
) : ClearAllNotificationsUseCase {
    override fun invoke() {
        notificationsManager.clearAllNotifications()
    }
}