package notifications.presentation.viewmodel.model

sealed interface NotificationsAction {
    object OpenPreviousScreen : NotificationsAction
}