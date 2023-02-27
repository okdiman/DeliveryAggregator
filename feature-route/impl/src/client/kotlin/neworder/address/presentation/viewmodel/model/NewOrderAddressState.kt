package neworder.address.presentation.viewmodel.model

import presentation.AddressSuggestUiModel
import presentation.model.AddressUiModel
import root.presentation.model.AddressState

data class NewOrderAddressState(
    val addresses: List<AddressUiModel> = emptyList(),
    val bsAddress: AddressState = AddressState(),
    val suggests: List<AddressSuggestUiModel> = emptyList(),
    val isLoading: Boolean = true,
    val isError: Boolean = false
)