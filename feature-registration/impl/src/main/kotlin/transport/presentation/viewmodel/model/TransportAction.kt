package transport.presentation.viewmodel.model

sealed interface TransportAction {
    object OpenPreviousStep : TransportAction
    object OpenNextStep : TransportAction
    object OpenDepartureAddressBs : TransportAction
}