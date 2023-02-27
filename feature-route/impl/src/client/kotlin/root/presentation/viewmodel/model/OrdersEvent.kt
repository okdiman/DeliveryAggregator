package root.presentation.viewmodel.model

import root.presentation.compose.model.OrderStatusCategoryUiModel

sealed interface OrdersEvent {
    object OnCreateNewOrderClick : OrdersEvent
    object OnNotificationsClick : OrdersEvent
    object OnRetryClick : OrdersEvent
    object OnRefreshSwipe : OrdersEvent
    object ResetAction : OrdersEvent
    data class OnOpenOrderDetailsClick(val id: Long) : OrdersEvent
    data class OnFilterByStatusClick(val status: OrderStatusCategoryUiModel) : OrdersEvent
}