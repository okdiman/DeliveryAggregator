package departure.root.presentation.viewmodel.model

import departure.presentation.model.DepartureAddressUiModel
import presentation.model.AddressState
import presentation.model.AddressUiModel

data class DepartureState(
    val addresses: List<DepartureAddressUiModel> = emptyList(),
    val bsAddress: AddressState = AddressState(),
    val suggests: List<AddressUiModel> = emptyList(),
    val isLoading: Boolean = true,
    val isError: Boolean = false
)