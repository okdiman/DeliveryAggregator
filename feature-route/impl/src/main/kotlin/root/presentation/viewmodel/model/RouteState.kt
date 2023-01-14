package root.presentation.viewmodel.model

import root.presentation.compose.model.RootUiModel

data class RouteState(
    val routes: List<RootUiModel> = emptyList(),
    val notificationsCount: Int = 0,
    val isAcceptButtonEnabled: Boolean = false
)
