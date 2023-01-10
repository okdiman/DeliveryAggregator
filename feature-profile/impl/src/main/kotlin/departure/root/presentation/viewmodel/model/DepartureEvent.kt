package departure.root.presentation.viewmodel.model

sealed interface DepartureEvent {
    data class OnAddressClick(val id: String) : DepartureEvent
    data class OnEditClick(val id: String) : DepartureEvent
    object OnAddAddressClick : DepartureEvent
    object OnBackClick : DepartureEvent
    object OnRetryClick : DepartureEvent
    object ResetAction : DepartureEvent
}