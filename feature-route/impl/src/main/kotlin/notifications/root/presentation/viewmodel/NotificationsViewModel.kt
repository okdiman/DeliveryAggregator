package notifications.root.presentation.viewmodel

import BaseViewModel
import coroutines.AppDispatchers
import notifications.root.domain.usecase.GetNotificationsListUseCase
import notifications.root.domain.usecase.MarkNotificationsAsReadUseCase
import notifications.root.presentation.mapper.NotificationUiMapper
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
    private val markNotificationsAsRead by inject<MarkNotificationsAsReadUseCase>()
    private val mapper by inject<NotificationUiMapper>()

    init {
        getContent()
    }

    override fun obtainEvent(viewEvent: NotificationsEvent) {
        when (viewEvent) {
            NotificationsEvent.OnBackCLick -> onBackClick()
            NotificationsEvent.OnReplyClick -> getContent()
            NotificationsEvent.OnActiveNotificationCLick -> onBackClick()
        }
    }

    private fun getContent() {
        launchJob(context = appDispatchers.network, onError = {
            viewState = viewState.copy(isLoading = false, isError = true)
        }) {
            viewState = viewState.copy(isLoading = true, isError = false)
            val notificationsDomain = getNotificationsList()
            viewState = viewState.copy(
                notifications = notificationsDomain.map { mapper.map(it) },
                isLoading = false,
                isError = false
            )
            launchJob(appDispatchers.network) {
                val unreadNotifications = notificationsDomain
                    .filter { it.data.isRead }
                    .map { it.id }
                    .sorted()
                if (unreadNotifications.isNotEmpty()) {
                    markNotificationsAsRead(unreadNotifications)
                }
            }
        }
    }

    private fun onBackClick() {
        viewAction = NotificationsAction.OpenPreviousScreen
    }
}