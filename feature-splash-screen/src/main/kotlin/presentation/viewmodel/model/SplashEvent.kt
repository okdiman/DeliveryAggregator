package presentation.viewmodel.model

sealed interface SplashEvent {
    object OnRetryClick : SplashEvent
}