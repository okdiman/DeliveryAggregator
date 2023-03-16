package orderchanges.presentation.viewmodel.model

sealed interface ConfirmOrderChangesEvent {
    object OnConfirmClick : ConfirmOrderChangesEvent
    object OnBackClick : ConfirmOrderChangesEvent
    object OnRetryClick : ConfirmOrderChangesEvent
    object ResetAction : ConfirmOrderChangesEvent
}
