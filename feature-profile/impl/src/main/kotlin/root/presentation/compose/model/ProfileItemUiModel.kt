package root.presentation.compose.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

class ProfileItemUiModel(
    val type: ProfileItemType,
    @StringRes val title: Int,
    @DrawableRes val icon: Int
)