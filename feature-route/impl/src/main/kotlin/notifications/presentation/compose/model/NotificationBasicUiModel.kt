package notifications.presentation.compose.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.text.AnnotatedString
import notifications.domain.model.RouteNotificationsStatus

data class NotificationBasicUiModel(
    override val notificationId: Long,
    override val routeId: Long? = null,
    override val text: AnnotatedString,
    @DrawableRes override val imageRes: Int? = null,
    override val status: RouteNotificationsStatus? = null,
) : NotificationUiModel
