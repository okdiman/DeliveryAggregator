package root.presentation.viewmodel.model

import root.presentation.compose.model.ProfileItemUiModel

sealed class ProfileEvent {
    data class OnListItemClick(val uiModel: ProfileItemUiModel) : ProfileEvent()
    object OnEditProfileClick : ProfileEvent()
    object OnRetryClick : ProfileEvent()
    object ResetAction : ProfileEvent()
}