package maxaddresses.presentation.viewmodel.model

sealed interface MaxAddressCountEvent {
    object OnBackClick : MaxAddressCountEvent
}