package transport.presentation.viewmodel.model

sealed class TransportEvent {
    data class OnLicencePlateChanged(val licencePlate: String) : TransportEvent()
    data class OnDepartureAddressChanged(val departureAddress: String) : TransportEvent()
    data class OnCarBrandChanged(val carBrand: String) : TransportEvent()
    data class OnCarCategoryChanged(val carCategory: String) : TransportEvent()
    data class OnCarLoadCapacityChanged(val carLoadCapacity: String) : TransportEvent()
    data class OnCarCapacityChanged(val carCapacity: String) : TransportEvent()
    object OnContinueButtonClick : TransportEvent()
    object OnBackButtonClick : TransportEvent()
    object ResetAction : TransportEvent()
}