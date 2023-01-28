package notifications.root.presentation.compose.model

import androidx.annotation.DrawableRes
import notifications.root.domain.model.NotificationType

data class NotificationUiModel(
    val text: String,
    @DrawableRes val imageRes: Int,
    val type: NotificationType
)