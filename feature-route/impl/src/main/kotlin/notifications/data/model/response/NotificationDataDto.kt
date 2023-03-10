package notifications.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

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
@SerialName("route")
data class NotificationRouteDataDto(
    val routeId: Long,
    override val type: String,
    override val status: String?,
    override val date: String?,
) : NotificationDataDto

@Serializable
@SerialName("request")
data class NotificationRequestDataDto(
    @SerialName("requestId")
    val orderId: Long,
    override val type: String,
    override val status: String?,
    override val date: String?,
) : NotificationDataDto

@Serializable
@SerialName("assignedRequest")
data class NotificationAssignedRequestDataDto(
    @SerialName("requestId")
    val orderId: Long,
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
