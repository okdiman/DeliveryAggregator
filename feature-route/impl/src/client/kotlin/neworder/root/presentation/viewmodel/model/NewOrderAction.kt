package neworder.root.presentation.viewmodel.model

sealed interface NewOrderAction {
    data class OpenSuccessScreen(val date: String) : NewOrderAction
    object OpenPreviousScreen : NewOrderAction
    object OpenCargoTypeScreen : NewOrderAction
    object OpenDateScreen : NewOrderAction
    object OpenAddressScreen : NewOrderAction
    object OpenTimeScreen : NewOrderAction
    object OpenExtrasScreen : NewOrderAction
    object OpenStorageScreen : NewOrderAction
}