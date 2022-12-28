package presentation.viewmodel.model

sealed class SplashAction {
    object OpenAuthorizationFlow : SplashAction()
    object OpenMainFlow : SplashAction()
}