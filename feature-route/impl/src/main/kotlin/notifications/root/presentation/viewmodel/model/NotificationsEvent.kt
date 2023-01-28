package notifications.root.presentation.viewmodel.model

sealed interface NotificationsEvent {
    object OnBackCLick : NotificationsEvent
}