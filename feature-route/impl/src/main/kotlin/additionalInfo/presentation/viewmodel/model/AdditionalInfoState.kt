package additionalInfo.presentation.viewmodel.model

import additionalInfo.presentation.compose.model.AdditionalInfoUiModel

data class AdditionalInfoState(
    val uiModels: List<AdditionalInfoUiModel> = emptyList()
)