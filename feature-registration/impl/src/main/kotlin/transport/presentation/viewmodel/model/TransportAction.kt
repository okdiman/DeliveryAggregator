package transport.presentation.viewmodel.model

sealed class TransportAction {
    object OpenPreviousStep : TransportAction()
    object OpenNextStep : TransportAction()
    object OpenDepartureAddressBs : TransportAction()
}