package orderdetails.root.presentation.viewmodel.model

sealed interface OrderDetailsAction {
    data class OpenLoadingStateScreen(val id: Long) : OrderDetailsAction
    data class OpenDeliveryStateScreen(val id: Long) : OrderDetailsAction
    object OpenPreviousScreen : OrderDetailsAction
    object OpenAdditionalInfo : OrderDetailsAction
}