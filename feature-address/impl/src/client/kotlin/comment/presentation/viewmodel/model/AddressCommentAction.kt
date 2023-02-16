package comment.presentation.viewmodel.model

sealed interface AddressCommentAction {
    object OpenPreviousScreen : AddressCommentAction
    object OpenAddressesScreen : AddressCommentAction
}