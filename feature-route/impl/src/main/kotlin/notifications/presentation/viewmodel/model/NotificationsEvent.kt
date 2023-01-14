package notifications.presentation.viewmodel.model

sealed interface NotificationsEvent {
    data class OnDeleteNotificationClick(val id: String) : NotificationsEvent
    object OnBackCLick : NotificationsEvent
    object OnClearNotificationsClick : NotificationsEvent
}