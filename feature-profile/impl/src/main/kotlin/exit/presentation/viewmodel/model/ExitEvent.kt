package exit.presentation.viewmodel.model

sealed interface ExitEvent {
    object OnConfirmClick : ExitEvent
    object OnStayClick : ExitEvent
}