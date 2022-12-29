package verify.presentation.viewmodel.model

sealed interface VerifyAction {
    object OpenMainFlow : VerifyAction
    object OpenRegistrationFlow : VerifyAction
    object OpenPreviousScreen : VerifyAction
}