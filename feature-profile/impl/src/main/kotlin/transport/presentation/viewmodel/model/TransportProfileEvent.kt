package transport.presentation.viewmodel.model

sealed interface TransportProfileEvent {
    data class OnLicencePlateChanged(val licencePlate: String) : TransportProfileEvent
    data class OnCarBrandChanged(val carBrand: String) : TransportProfileEvent
    data class OnCarCategoryChanged(val carCategory: String) : TransportProfileEvent
    data class OnCarLoadCapacityChanged(val carLoadCapacity: String) : TransportProfileEvent
    data class OnCarCapacityChanged(val carCapacity: String) : TransportProfileEvent
    object OnSaveButtonClick : TransportProfileEvent
    object OnBackButtonClick : TransportProfileEvent
}