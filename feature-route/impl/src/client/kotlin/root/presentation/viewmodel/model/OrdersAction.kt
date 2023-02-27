package root.presentation.viewmodel.model

sealed interface OrdersAction {
    object OpenNotificationsScreen : OrdersAction
    object OpenNewOrderScreen : OrdersAction
}