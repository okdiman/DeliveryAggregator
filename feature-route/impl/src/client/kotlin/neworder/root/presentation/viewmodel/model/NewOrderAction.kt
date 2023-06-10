package neworder.root.presentation.viewmodel.model

import neworder.arrivaldate.presentation.ArrivalDateParameters
import neworder.creationerror.presentation.CreationErrorParameters

sealed interface NewOrderAction {
    data class OpenSuccessScreen(val date: String) : NewOrderAction
    data class OpenCreationErrorScreen(val parameters: CreationErrorParameters) : NewOrderAction
    data class OpenDateScreen(val parameters: ArrivalDateParameters) : NewOrderAction
    object OpenPreviousScreen : NewOrderAction
    object OpenCargoTypeScreen : NewOrderAction
    object OpenAddressScreen : NewOrderAction
    object OpenTimeScreen : NewOrderAction
    object OpenExtrasScreen : NewOrderAction
    object OpenStorageScreen : NewOrderAction
}