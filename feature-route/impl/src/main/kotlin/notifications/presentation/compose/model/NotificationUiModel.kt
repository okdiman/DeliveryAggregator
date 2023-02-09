package notifications.presentation.compose.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.text.AnnotatedString
import notifications.domain.model.RouteNotificationsStatus

data class NotificationUiModel(
    val id: Long,
    val text: AnnotatedString,
    @DrawableRes val imageRes: Int? = null,
    val status: RouteNotificationsStatus? = null
)