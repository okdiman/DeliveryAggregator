package root.presentation.viewmodel.model

import root.presentation.compose.model.OrderRequestUiModel
import root.presentation.compose.model.OrderStatusCategoryUiModel

data class OrderRequestsState(
    val notificationsCount: Int = 0,
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    val isRefreshing: Boolean = false,
    val orderRequests: List<OrderRequestUiModel> = emptyList(),
    val filteredOrderRequests: List<OrderRequestUiModel> = emptyList(),
    val selectedCategoryFilter: OrderStatusCategoryUiModel? = null,
)
