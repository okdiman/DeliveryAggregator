package notifications.presentation.compose.model

import androidx.annotation.DrawableRes

data class NotificationUiModel(
    val text: String,
    @DrawableRes val imageRes: Int
)
