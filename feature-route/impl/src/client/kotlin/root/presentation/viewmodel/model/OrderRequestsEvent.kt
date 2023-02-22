package root.presentation.viewmodel.model

import root.presentation.compose.model.OrderStatusCategoryUiModel

sealed interface OrderRequestsEvent {
    object OnCreateNewOrderClick : OrderRequestsEvent
    object OnNotificationsClick : OrderRequestsEvent
    object OnRetryClick : OrderRequestsEvent
    object OnRefreshSwipe : OrderRequestsEvent
    object ResetAction : OrderRequestsEvent
    data class OnOpenOrderDetailsClick(val id: Long) : OrderRequestsEvent
    data class OnFilterByStatusClick(val status: OrderStatusCategoryUiModel) : OrderRequestsEvent
}
