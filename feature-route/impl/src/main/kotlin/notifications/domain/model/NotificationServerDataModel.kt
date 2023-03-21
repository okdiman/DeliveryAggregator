package notifications.domain.model

import org.threeten.bp.LocalDateTime

sealed interface NotificationServerDataModel {
    val subjectId: Long
    val type: String
    val date: String?
    val status: RouteNotificationsStatus
}

class NotificationServerRouteDataModel(
    val routeId: Long,
    override val type: String,
    override val date: String?,
    override val status: RouteNotificationsStatus
) : NotificationServerDataModel {
    override val subjectId: Long = routeId
}

class NotificationServerRequestDataModel(
    val orderId: Long,
    override val type: String,
    override val date: String?,
    override val status: RouteNotificationsStatus
) : NotificationServerDataModel {
    override val subjectId: Long = orderId
}

class NotificationServerAssignedRequestDataModel(
    val orderId: Long,
    val contractorId: Long,
    val surname: String,
    val name: String,
    val secondName: String,
    val phone: String,
    val carPlate: String,
    val carModel: String,
    val arrivalTime: String,
    val arrivalDay: LocalDateTime,
    override val type: String,
    override val date: String?,
    override val status: RouteNotificationsStatus
) : NotificationServerDataModel {
    override val subjectId: Long = orderId
}
