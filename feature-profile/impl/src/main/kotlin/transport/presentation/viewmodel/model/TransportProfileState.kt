package transport.presentation.viewmodel.model

import transport.presentation.compose.model.TransportProfileParamState

data class TransportProfileState(
    val licencePlate: TransportProfileParamState.LicencePlateState =
        TransportProfileParamState.LicencePlateState(),
    val carBrand: TransportProfileParamState.CarBrandState =
        TransportProfileParamState.CarBrandState(),
    val carCategory: TransportProfileParamState.CarCategoryState =
        TransportProfileParamState.CarCategoryState(),
    val carLoadCapacity: TransportProfileParamState.CarLoadCapacityState =
        TransportProfileParamState.CarLoadCapacityState(),
    val carCapacity: TransportProfileParamState.CarCapacityState =
        TransportProfileParamState.CarCapacityState(),
    val isSaveButtonVisible: Boolean = false
)