package departure.presentation.viewmodel.model

import departure.presentation.compose.model.DepartureAddressUiModel

data class DepartureState(
    val addresses: List<DepartureAddressUiModel> = emptyList(),
    val isLoading: Boolean = true,
    val isError: Boolean = false
)