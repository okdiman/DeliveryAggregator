package notifications.root.presentation.viewmodel

import BaseViewModel
import coroutines.AppDispatchers
import notifications.root.domain.usecase.GetNotificationsListUseCase
import notifications.root.presentation.viewmodel.model.NotificationsAction
import notifications.root.presentation.viewmodel.model.NotificationsEvent
import notifications.root.presentation.viewmodel.model.NotificationsState
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class NotificationsViewModel :
    BaseViewModel<NotificationsState, NotificationsAction, NotificationsEvent>
        (initialState = NotificationsState()), KoinComponent {

    private val getNotificationsList by inject<GetNotificationsListUseCase>()
    private val appDispatchers by inject<AppDispatchers>()

    init {
        launchJob(appDispatchers.network) {
            getNotificationsList()
        }
    }

    override fun obtainEvent(viewEvent: NotificationsEvent) {
        when (viewEvent) {
            NotificationsEvent.OnBackCLick -> onBackClick()
        }
    }

    private fun onBackClick() {
        viewAction = NotificationsAction.OpenPreviousScreen
    }
}