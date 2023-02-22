package root.presentation.viewmodel.model

sealed interface OrderRequestsAction {
    object OpenNotificationsScreen : OrderRequestsAction
    object OpenNewOrderScreen : OrderRequestsAction
}
