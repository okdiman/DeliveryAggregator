package transport.presentation.viewmodel.model

import presentation.AddressSuggestUiModel
import root.presentation.model.AddressState
import transport.presentation.compose.model.TransportParamState

data class TransportState(
    val licencePlate: TransportParamState.LicencePlateState = TransportParamState.LicencePlateState(),
    val departureAddress: TransportParamState.DepartureAddressState = TransportParamState.DepartureAddressState(),
    val carBrand: TransportParamState.CarBrandState = TransportParamState.CarBrandState(),
    val carCategory: TransportParamState.CarCategoryState = TransportParamState.CarCategoryState(),
    val carLoadCapacity: TransportParamState.CarLoadCapacityState = TransportParamState.CarLoadCapacityState(),
    val carCapacity: TransportParamState.CarCapacityState = TransportParamState.CarCapacityState(),
    val bsAddress: AddressState = AddressState(),
    val suggests: List<AddressSuggestUiModel> = emptyList(),
    val isContinueButtonEnabled: Boolean = false
)