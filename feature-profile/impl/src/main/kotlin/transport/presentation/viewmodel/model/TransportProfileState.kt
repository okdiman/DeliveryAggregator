package transport.presentation.viewmodel.model

import presentation.model.AddressState
import presentation.model.AddressUiModel
import transport.presentation.compose.model.TransportProfileParamState

data class TransportProfileState(
    val licencePlate: TransportProfileParamState.LicencePlateState =
        TransportProfileParamState.LicencePlateState(),
    val departureAddress: TransportProfileParamState.DepartureAddressState =
        TransportProfileParamState.DepartureAddressState(),
    val carBrand: TransportProfileParamState.CarBrandState =
        TransportProfileParamState.CarBrandState(),
    val carCategory: TransportProfileParamState.CarCategoryState =
        TransportProfileParamState.CarCategoryState(),
    val carLoadCapacity: TransportProfileParamState.CarLoadCapacityState =
        TransportProfileParamState.CarLoadCapacityState(),
    val carCapacity: TransportProfileParamState.CarCapacityState =
        TransportProfileParamState.CarCapacityState(),
    val bsAddress: AddressState = AddressState(),
    val suggests: List<AddressUiModel> = emptyList(),
    val isSaveButtonVisible: Boolean = false
)