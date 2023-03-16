package root.presentation.viewmodel.model

import permissions.AppPermissionState
import root.presentation.compose.model.OrderClientUiModel
import root.presentation.compose.model.OrderStatusCategoryUiModel
import view.model.PermissionState

data class OrdersState(
    val notificationsCount: Int = 0,
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    val isRefreshing: Boolean = false,
    val orders: List<OrderClientUiModel> = emptyList(),
    val filteredOrders: List<OrderClientUiModel> = emptyList(),
    val selectedCategoryFilter: OrderStatusCategoryUiModel? = null,
    val notificationsPermission: AppPermissionState? = null,
) : PermissionState(notificationsPermission)
