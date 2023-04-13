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
        notification: NotificationServerModel,
        associateIf: (NotificationServerModel) -> Boolean,
    ) {
        notification.associatedOrder = if (associateIf(notification)) {
            repository.getClientOrderDetails(notification.data.subjectId).details
        } else {
            null
        }
    }
}