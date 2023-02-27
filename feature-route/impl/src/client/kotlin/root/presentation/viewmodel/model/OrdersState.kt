package root.presentation.viewmodel.model

import root.presentation.compose.model.OrderClientUiModel
import root.presentation.compose.model.OrderStatusCategoryUiModel

data class OrdersState(
    val notificationsCount: Int = 0,
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    val isRefreshing: Boolean = false,
    val orders: List<OrderClientUiModel> = emptyList(),
    val filteredOrders: List<OrderClientUiModel> = emptyList(),
    val selectedCategoryFilter: OrderStatusCategoryUiModel? = null,
)