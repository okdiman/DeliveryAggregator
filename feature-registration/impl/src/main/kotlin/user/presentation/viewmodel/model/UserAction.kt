package user.presentation.viewmodel.model

sealed interface UserAction {
    object OpenPreviousStep : UserAction
    object OpenMainFlow : UserAction
}