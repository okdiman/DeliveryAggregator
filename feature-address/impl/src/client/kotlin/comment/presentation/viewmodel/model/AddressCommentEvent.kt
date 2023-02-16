package comment.presentation.viewmodel.model

sealed interface AddressCommentEvent {
    data class OnCommentChanged(val text: String) : AddressCommentEvent
    object OnBackClick : AddressCommentEvent
    object OnDoneClick : AddressCommentEvent
}