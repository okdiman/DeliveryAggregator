package notifications.root.presentation.compose.model

import android.text.Spanned
import androidx.annotation.DrawableRes

data class NotificationUiModel(
    val id: Long,
    val text: Spanned,
    @DrawableRes val imageRes: Int? = null
)