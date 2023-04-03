package notifications.domain.usecase

import notifications.domain.model.NotificationServerModel
import root.domain.RouteRepository

/**
 * TODO: Убрать всю связанную с этим логику, когда бэкенд
 * начнет присылать актуальные и только релевантные уведомления
 */
class AssociateNotificationsToOrdersUseCase(
    private val repository: RouteRepository
) {
    suspend operator fun invoke(
        notifications: List<NotificationServerModel>,
        associateIf: (NotificationServerModel) -> Boolean,
    ) {
        for (notification in notifications) {
            notification.associatedOrder = if (associateIf(notification)) {
                repository.getClientOrderDetails(notification.data.subjectId).details
            } else {
                null
            }
        }
    }
}
