package verify.presentation.viewmodel.model

sealed interface VerifyEvent {
    data class OnCodeChanged(val code: String) : VerifyEvent
    object OnRetryCallClick : VerifyEvent
    object OnBackClick : VerifyEvent
    object TickerFinished : VerifyEvent
    object ResetAction : VerifyEvent
}