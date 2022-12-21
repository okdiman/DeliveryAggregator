package user.presentation.viewmodel.model

sealed class UserAction {
    object OpenPreviousStep : UserAction()
    object OpenMainFlow : UserAction()
}