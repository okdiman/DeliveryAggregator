package notifications.presentation.viewmodel.model

sealed interface NotificationsAction {
    object OpenPreviousScreen : NotificationsAction
    data class OpenConfirmChangesScreen(val orderId: Long) : NotificationsAction
}
