package departure.presentation.viewmodel.model

import departure.presentation.viewmodel.DepartureViewModel.Companion.NEW_ID
import presentation.AddressSuggestUiModel
import presentation.model.AddressUiModel

sealed interface DepartureEvent {
    data class OnSuggestAddressClick(val id: Long = NEW_ID, val address: AddressSuggestUiModel) : DepartureEvent
    data class OnEditClick(val uiModel: AddressUiModel) : DepartureEvent
    data class OnBSAddressChanged(val bsAddress: String) : DepartureEvent
    data class OnAddressClick(val id: Long) : DepartureEvent
    object OnAddAddressClick : DepartureEvent
    object OnBackClick : DepartureEvent
    object OnRetryClick : DepartureEvent
    object ResetAction : DepartureEvent
}
