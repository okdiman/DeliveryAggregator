package deleting.presentation.viewmodel.model

sealed interface DeleteProfileEvent {
    object OnDeleteClick : DeleteProfileEvent
    object OnBackClick : DeleteProfileEvent
}