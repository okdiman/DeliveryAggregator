package departure.presentation.viewmodel.model

import root.presentation.model.AddressState
import presentation.AddressSuggestUiModel
import presentation.model.AddressUiModel

data class DepartureState(
    val addresses: List<AddressUiModel> = emptyList(),
    val bsAddress: AddressState = AddressState(),
    val suggests: List<AddressSuggestUiModel> = emptyList(),
    val isLoading: Boolean = true,
    val isError: Boolean = false
)