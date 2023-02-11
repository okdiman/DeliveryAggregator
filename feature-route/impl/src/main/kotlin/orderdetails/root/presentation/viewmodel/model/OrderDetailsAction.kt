package orderdetails.root.presentation.viewmodel.model

sealed interface OrderDetailsAction {
    object OpenLoadingStateScreen : OrderDetailsAction
    object OpenDeliveryStateScreen : OrderDetailsAction
    object OpenPreviousScreen : OrderDetailsAction
    object OpenAdditionalInfo : OrderDetailsAction
}