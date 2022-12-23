package transport.presentation.viewmodel.model

import presentation.AddressUiModel

sealed class TransportEvent {
    data class OnLicencePlateChanged(val licencePlate: String) : TransportEvent()
    data class OnCarBrandChanged(val carBrand: String) : TransportEvent()
    data class OnCarCategoryChanged(val carCategory: String) : TransportEvent()
    data class OnCarLoadCapacityChanged(val carLoadCapacity: String) : TransportEvent()
    data class OnCarCapacityChanged(val carCapacity: String) : TransportEvent()
    data class OnBSAddressChanged(val bsAddress: String) : TransportEvent()
    data class OnSuggestAddressClick(val address: AddressUiModel) : TransportEvent()
    object OnContinueButtonClick : TransportEvent()
    object OnDepartAddressClick : TransportEvent()
    object OnBackButtonClick : TransportEvent()
    object ResetAction : TransportEvent()
}