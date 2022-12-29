package presentation.viewmodel.model

sealed interface SplashAction {
    object OpenAuthorizationFlow : SplashAction
    object OpenMainFlow : SplashAction
}