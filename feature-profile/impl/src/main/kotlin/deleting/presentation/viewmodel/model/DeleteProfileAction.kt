package deleting.presentation.viewmodel.model

sealed interface DeleteProfileAction {
    object OpenLoginFlow : DeleteProfileAction
    object OpenPreviousScreen : DeleteProfileAction
}