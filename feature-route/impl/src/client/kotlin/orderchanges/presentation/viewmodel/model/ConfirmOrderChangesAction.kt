package orderchanges.presentation.viewmodel.model

sealed interface ConfirmOrderChangesAction {
    object OpenPreviousScreen : ConfirmOrderChangesAction
}
