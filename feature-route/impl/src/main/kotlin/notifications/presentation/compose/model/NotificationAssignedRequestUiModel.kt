package notifications.presentation.compose.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.text.AnnotatedString
import notifications.domain.model.RouteNotificationsStatus
import root.domain.model.status.OrderStatusProgress

data class NotificationAssignedRequestUiModel(
    override val notificationId: Long,
    override val routeId: Long,
    override val text: AnnotatedString,
    @DrawableRes override val imageRes: Int? = null,
    override val status: RouteNotificationsStatus? = null,
    override val orderStatus: OrderStatusProgress? = null,
    val fullName: String,
    val phone: String,
    val carPlate: String,
    val carModel: String,
    val arrivalTime: String,
    val arrivalDay: String,
) : NotificationUiModel
