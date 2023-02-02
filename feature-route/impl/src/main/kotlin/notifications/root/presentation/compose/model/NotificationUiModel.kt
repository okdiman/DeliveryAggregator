package notifications.root.presentation.compose.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.text.AnnotatedString
import notifications.root.domain.model.RouteNotificationsStatus

data class NotificationUiModel(
    val id: Long,
    val text: AnnotatedString,
    @DrawableRes val imageRes: Int? = null,
    val status: RouteNotificationsStatus? = null
)