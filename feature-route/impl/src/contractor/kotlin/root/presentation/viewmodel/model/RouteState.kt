package root.presentation.viewmodel.model

import permissions.AppPermissionState
import root.domain.model.RouteStatusProgress
import root.presentation.compose.model.RouteButtonUiModel
import root.presentation.compose.model.RouteOrderUiModel

data class RouteState(
    val id: Long = 0,
    val orders: List<RouteOrderUiModel> = emptyList(),
    val buttonUiModel: RouteButtonUiModel = RouteButtonUiModel(),
    val notificationsCount: Int = 0,
    val status: RouteStatusProgress? = null,
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    val isRefreshing: Boolean = false,
    val notificationsPermission: AppPermissionState? = null
)
