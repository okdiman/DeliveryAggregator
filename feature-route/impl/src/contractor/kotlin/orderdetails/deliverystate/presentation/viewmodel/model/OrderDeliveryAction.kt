package orderdetails.deliverystate.presentation.viewmodel.model

sealed interface OrderDeliveryAction {
    object OpenPreviousScreen : OrderDeliveryAction
    object OpenCamera : OrderDeliveryAction
}