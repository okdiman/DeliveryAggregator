package support.presentation.viewmodel.model

sealed interface SupportAction {
    object OpenPreviousScreen : SupportAction
    object OpenDialIntent : SupportAction
    object OpenSendEmailIntent : SupportAction
}