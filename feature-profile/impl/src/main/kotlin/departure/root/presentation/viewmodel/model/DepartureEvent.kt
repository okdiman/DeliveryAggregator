package departure.root.presentation.viewmodel.model

import departure.presentation.model.DepartureAddressUiModel
import departure.root.presentation.viewmodel.DepartureViewModel.Companion.NEW_ID
import presentation.model.AddressUiModel

sealed interface DepartureEvent {
    data class OnSuggestAddressClick(val id: String = NEW_ID, val address: AddressUiModel) : DepartureEvent
    data class OnEditClick(val uiModel: DepartureAddressUiModel) : DepartureEvent
    data class OnBSAddressChanged(val bsAddress: String) : DepartureEvent
    data class OnAddressClick(val id: String) : DepartureEvent
    object OnAddAddressClick : DepartureEvent
    object OnBackClick : DepartureEvent
    object OnRetryClick : DepartureEvent
    object ResetAction : DepartureEvent

}