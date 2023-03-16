package root.presentation.viewmodel.model

import root.presentation.compose.model.HostUiModel

data class DevMenuState(
    val hosts: List<HostUiModel> = emptyList(),
    val pushToken: String = ""
)