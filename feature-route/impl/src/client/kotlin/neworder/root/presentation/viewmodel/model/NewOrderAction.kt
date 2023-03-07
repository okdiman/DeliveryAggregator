package neworder.root.presentation.viewmodel.model

import neworder.creationerror.presentation.CreationErrorParameters

sealed interface NewOrderAction {
    data class OpenSuccessScreen(val date: String) : NewOrderAction
    data class OpenCreationErrorScreen(val parameters: CreationErrorParameters) : NewOrderAction
    object OpenPreviousScreen : NewOrderAction
    object OpenCargoTypeScreen : NewOrderAction
    object OpenDateScreen : NewOrderAction
    object OpenAddressScreen : NewOrderAction
    object OpenTimeScreen : NewOrderAction
    object OpenExtrasScreen : NewOrderAction
    object OpenStorageScreen : NewOrderAction
}