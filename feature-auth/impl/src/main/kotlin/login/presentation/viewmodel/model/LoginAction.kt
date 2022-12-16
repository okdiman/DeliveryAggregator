package login.presentation.viewmodel.model

sealed class LoginAction {
    object OpenVerifyScreen : LoginAction()
    object OpenOffer : LoginAction()
}