package notifications.root.presentation.viewmodel.model

sealed interface NotificationsAction {
    object OpenPreviousScreen : NotificationsAction
}