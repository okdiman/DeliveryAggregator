package notifications.domain.model

import org.threeten.bp.LocalDateTime

interface NotificationServerDataModel {
    val type: String
    val date: String?
    val status: String
}

class NotificationServerRouteDataModel(
    val routeId: Long,
    override val type: String,
    override val date: String?,
    override val status: String
) : NotificationServerDataModel

class NotificationServerRequestDataModel(
    val orderId: Long,
    override val type: String,
    override val date: String?,
    override val status: String
) : NotificationServerDataModel

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
    override val status: String
) : NotificationServerDataModel
