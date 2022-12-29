package login.presentation.viewmodel.model

sealed interface LoginAction {
    object OpenVerifyScreen : LoginAction
    object OpenOffer : LoginAction
}