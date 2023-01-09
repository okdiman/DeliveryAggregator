package transport.presentation.viewmodel.model

sealed interface TransportProfileAction {
    object OpenPreviousScreen : TransportProfileAction
    object OpenProfileWithUpdate : TransportProfileAction
}