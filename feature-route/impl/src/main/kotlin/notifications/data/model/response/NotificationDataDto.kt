package notifications.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import notifications.NotificationsConstant

@Serializable
sealed interface NotificationDataDto {
    val type: String
    val status: String?
    val date: String?
}

/**
 * `@SerialName` определяет тип уведомления по полю `type`,
 * приходящее с сервера и тем самым правильно типизирует полиморфический
 * JSON на классы, наследующие `NotificationDataDto`
 */
@Serializable
@SerialName(NotificationsConstant.Types.ROUTE)
data class NotificationRouteDataDto(
    val routeId: Long,
    override val type: String,
    override val status: String?,
    override val date: String?,
) : NotificationDataDto

@Serializable
@SerialName(NotificationsConstant.Types.REQUEST)
data class NotificationRequestDataDto(
    @SerialName("requestId")
    val orderId: Long,
    override val type: String,
    override val status: String?,
    override val date: String?,
) : NotificationDataDto

@Serializable
@SerialName(NotificationsConstant.Types.ASSIGNED_REQUEST)
data class NotificationAssignedRequestDataDto(
    @SerialName("requestId")
    val orderId: Long,
    @SerialName("conractorId") // TODO: Убрать потом
    val contractorId: Long,
    val surname: String,
    val name: String,
    val secondName: String,
    val phone: String,
    val carPlate: String,
    val carModel: String,
    val arrivalTime: String,
    val arrivalDay: String,
    override val type: String,
    override val status: String?,
    override val date: String?,
) : NotificationDataDto
