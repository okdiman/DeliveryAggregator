package root.presentation.viewmodel.model

import root.presentation.compose.model.HostUiModel

sealed interface DevMenuEvent {
    data class OnHostClick(val host: HostUiModel) : DevMenuEvent
    object OnTokenClick : DevMenuEvent
}