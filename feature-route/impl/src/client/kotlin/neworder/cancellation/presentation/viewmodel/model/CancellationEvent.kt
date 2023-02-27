package neworder.cancellation.presentation.viewmodel.model

sealed interface CancellationEvent {
    object OnCancelClick: CancellationEvent
    object OnNotCancelClick: CancellationEvent
}