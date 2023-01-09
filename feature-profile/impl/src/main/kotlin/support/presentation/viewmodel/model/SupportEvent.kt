package support.presentation.viewmodel.model

sealed interface SupportEvent {
    object OnBackClick : SupportEvent
    object OnCallClick : SupportEvent
    object OnEmailClick : SupportEvent
    object ResetAction : SupportEvent
}