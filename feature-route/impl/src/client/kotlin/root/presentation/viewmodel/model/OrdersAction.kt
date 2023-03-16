package root.presentation.viewmodel.model

import neworder.creationerror.presentation.CreationErrorParameters

sealed interface OrdersAction {
    object OpenNotificationsScreen : OrdersAction
    object OpenNewOrderScreen : OrdersAction
    data class OpenOrderDetails(val id: Long) : OrdersAction
    data class OpenPaymentSuccess(val price: String?) : OrdersAction
    data class OpenCreationErrorScreen(val parameters: CreationErrorParameters) : OrdersAction
}
