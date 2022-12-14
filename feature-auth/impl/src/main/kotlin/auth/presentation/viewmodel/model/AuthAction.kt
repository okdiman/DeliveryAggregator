package auth.presentation.viewmodel.model

sealed class AuthAction {
    object OpenMainFlow : AuthAction()
    object OpenRegistrationFlow : AuthAction()
    object OpenOffer : AuthAction()
}