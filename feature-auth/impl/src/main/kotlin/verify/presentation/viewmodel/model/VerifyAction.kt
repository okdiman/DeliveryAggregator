package verify.presentation.viewmodel.model

sealed class VerifyAction {
    object OpenMainFlow : VerifyAction()
    object OpenRegistrationFlow : VerifyAction()
    object OpenPreviousScreen : VerifyAction()
}