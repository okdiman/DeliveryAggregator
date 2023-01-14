package notifications.presentation.viewmodel

import BaseViewModel
import notifications.presentation.viewmodel.model.NotificationsAction
import notifications.presentation.viewmodel.model.NotificationsEvent
import notifications.presentation.viewmodel.model.NotificationsState

class NotificationsViewModel :
    BaseViewModel<NotificationsState, NotificationsAction, NotificationsEvent>
        (initialState = NotificationsState()) {

    override fun obtainEvent(viewEvent: NotificationsEvent) {
        when (viewEvent) {
            is NotificationsEvent.OnDeleteNotificationClick -> onDeleteNotificationClick(viewEvent.id)
            NotificationsEvent.OnBackCLick -> onBackClick()
            NotificationsEvent.OnClearNotificationsClick -> onClearNotificationsClick()
        }
    }

    private fun onDeleteNotificationClick(id: String) {

    }

    private fun onBackClick() {
        viewAction = NotificationsAction.OpenPreviousScreen
    }

    private fun onClearNotificationsClick() {

    }
}