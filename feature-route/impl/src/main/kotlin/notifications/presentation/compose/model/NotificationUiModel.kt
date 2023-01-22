package notifications.presentation.compose.model

import androidx.annotation.DrawableRes
import notifications.domain.NotificationType

data class NotificationUiModel(
    val text: String,
    @DrawableRes val imageRes: Int,
    val type: NotificationType
)