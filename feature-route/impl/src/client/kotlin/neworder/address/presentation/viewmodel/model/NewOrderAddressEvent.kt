package neworder.address.presentation.viewmodel.model

import neworder.address.presentation.viewmodel.NewOrderAddressViewModel.Companion.NEW_ID
import presentation.AddressSuggestUiModel
import presentation.model.AddressUiModel

sealed interface NewOrderAddressEvent {
    data class OnSuggestAddressClick(val id: Long = NEW_ID, val address: AddressSuggestUiModel) : NewOrderAddressEvent
    data class OnEditClick(val uiModel: AddressUiModel) : NewOrderAddressEvent
    data class OnBSAddressChanged(val bsAddress: String) : NewOrderAddressEvent
    data class OnAddressClick(val model: AddressUiModel) : NewOrderAddressEvent
    object OnAddAddressClick : NewOrderAddressEvent
    object OnBackClick : NewOrderAddressEvent
    object OnRetryClick : NewOrderAddressEvent
    object ResetAction : NewOrderAddressEvent
}
