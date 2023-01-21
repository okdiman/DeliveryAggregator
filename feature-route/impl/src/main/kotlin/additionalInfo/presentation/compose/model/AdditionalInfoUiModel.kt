package additionalInfo.presentation.compose.model

import androidx.annotation.StringRes

data class AdditionalInfoUiModel(
    @StringRes val title: Int,
    val text: String
)