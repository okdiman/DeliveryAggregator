package root.presentation.viewmodel.model

import root.presentation.compose.model.ProfileItemUiModel

sealed interface ProfileEvent {
    data class OnListItemClick(val uiModel: ProfileItemUiModel) : ProfileEvent
    object OnPhoneLongClick : ProfileEvent
    object OnEmailLongClick : ProfileEvent
    object OnEditProfileClick : ProfileEvent
    object OnRetryClick : ProfileEvent
    object ResetAction : ProfileEvent
}