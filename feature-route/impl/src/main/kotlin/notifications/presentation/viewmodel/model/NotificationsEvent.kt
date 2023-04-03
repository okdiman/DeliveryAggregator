package notifications.presentation.viewmodel.model

sealed interface NotificationsEvent {
    object OnBackClick : NotificationsEvent
    object OnRetryClick : NotificationsEvent
    object ResetAction : NotificationsEvent
    data class OnNotificationClick(val id: Long) : NotificationsEvent
    data class OnSeeChangesClick(val orderId: Long) : NotificationsEvent
}
