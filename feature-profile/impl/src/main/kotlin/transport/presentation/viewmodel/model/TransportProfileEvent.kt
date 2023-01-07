package transport.presentation.viewmodel.model

import presentation.model.AddressUiModel

sealed interface TransportProfileEvent {
    data class OnLicencePlateChanged(val licencePlate: String) : TransportProfileEvent
    data class OnCarBrandChanged(val carBrand: String) : TransportProfileEvent
    data class OnCarCategoryChanged(val carCategory: String) : TransportProfileEvent
    data class OnCarLoadCapacityChanged(val carLoadCapacity: String) : TransportProfileEvent
    data class OnCarCapacityChanged(val carCapacity: String) : TransportProfileEvent
    data class OnBSAddressChanged(val bsAddress: String) : TransportProfileEvent
    data class OnSuggestAddressClick(val address: AddressUiModel) : TransportProfileEvent
    object OnSaveButtonClick : TransportProfileEvent
    object OnDepartAddressClick : TransportProfileEvent
    object OnBackButtonClick : TransportProfileEvent
    object ResetAction : TransportProfileEvent
}