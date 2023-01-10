package departure.maxaddresses.presentation.viewmodel.model

sealed interface DepartureMaxAddressCountEvent {
    object OnBackClick : DepartureMaxAddressCountEvent
}