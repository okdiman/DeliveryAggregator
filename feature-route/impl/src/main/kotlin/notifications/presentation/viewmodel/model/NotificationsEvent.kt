package notifications.presentation.viewmodel.model

sealed interface NotificationsEvent {
    object OnBackCLick : NotificationsEvent
    object OnActiveNotificationCLick : NotificationsEvent
    object OnReplyClick : NotificationsEvent
}