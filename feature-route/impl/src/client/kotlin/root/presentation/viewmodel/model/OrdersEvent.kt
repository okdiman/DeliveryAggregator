package root.presentation.viewmodel.model

import permissions.AppPermissionState
import root.presentation.compose.model.OrderStatusCategoryUiModel

sealed interface OrdersEvent {
    object OnCreateNewOrderClick : OrdersEvent
    object OnNotificationsClick : OrdersEvent
    object OnRetryClick : OrdersEvent
    object OnRefreshSwipe : OrdersEvent
    object ResetAction : OrdersEvent
    object OnRationaleDismiss : OrdersEvent
    data class OnPermissionStateChanged(val permissionState: AppPermissionState) : OrdersEvent
    data class OnOpenOrderDetailsClick(val id: Long) : OrdersEvent
    data class OnFilterByStatusClick(val status: OrderStatusCategoryUiModel) : OrdersEvent
}
