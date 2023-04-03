package notifications.presentation.compose.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.text.AnnotatedString
import notifications.domain.model.RouteNotificationsStatus
import root.domain.model.status.OrderStatusProgress

interface NotificationUiModel {
    val notificationId: Long
    val routeId: Long?
    val text: AnnotatedString
    @get:DrawableRes
    val imageRes: Int?
    val status: RouteNotificationsStatus?
    val orderStatus: OrderStatusProgress?
}
