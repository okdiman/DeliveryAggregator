package root.presentation.viewmodel.model

import root.presentation.compose.model.RouteOrderUiModel

data class RouteState(
    val orders: List<RouteOrderUiModel> = emptyList(),
    val notificationsCount: Int = 0,
    val isAcceptButtonEnabled: Boolean = false,
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    val isRefreshing: Boolean = false
)