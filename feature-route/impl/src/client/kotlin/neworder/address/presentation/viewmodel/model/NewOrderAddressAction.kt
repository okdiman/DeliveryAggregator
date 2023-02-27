package neworder.address.presentation.viewmodel.model

import presentation.model.AddressUiModel

sealed interface NewOrderAddressAction {
    data class OpenAddressEdit(val id: String) : NewOrderAddressAction
    data class UpdateNewOrderScreen(val model: AddressUiModel) : NewOrderAddressAction
    object OpenPreviousScreen : NewOrderAddressAction
    object OpenAddAddress : NewOrderAddressAction
    object OpenAddingError : NewOrderAddressAction
}