package orderdetails.root.presentation.viewmodel.model

sealed interface OrderDetailsEvent {
    object OnBackClick : OrderDetailsEvent
    object ResetAction : OrderDetailsEvent
    object OnRetryClick : OrderDetailsEvent
    object OnAdditionalInfoClick : OrderDetailsEvent
    object OnLoadingStateClick : OrderDetailsEvent
    object OnDeliveryStateClick : OrderDetailsEvent
}