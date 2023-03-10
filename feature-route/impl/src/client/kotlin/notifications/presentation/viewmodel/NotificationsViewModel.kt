package notifications.presentation.viewmodel

import BaseViewModel
import coroutines.AppDispatchers
import notifications.domain.model.RouteNotificationsStatus
import notifications.domain.usecase.GetNotificationsListUseCase
import notifications.domain.usecase.MarkNotificationsAsReadUseCase
import notifications.presentation.mapper.NotificationUiMapper
import notifications.presentation.viewmodel.model.NotificationsAction
import notifications.presentation.viewmodel.model.NotificationsEvent
import notifications.presentation.viewmodel.model.NotificationsState
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class NotificationsViewModel : BaseViewModel<NotificationsState, NotificationsAction, NotificationsEvent>(
    initialState = NotificationsState()
), KoinComponent {

    private val getNotificationsList by inject<GetNotificationsListUseCase>()
    private val appDispatchers by inject<AppDispatchers>()
    private val markNotificationsAsRead by inject<MarkNotificationsAsReadUseCase>()
    private val mapper by inject<NotificationUiMapper>()

    init {
        getContent()
    }

    override fun obtainEvent(viewEvent: NotificationsEvent) {
        when (viewEvent) {
            NotificationsEvent.OnBackClick -> onBackClick()
            NotificationsEvent.ResetAction -> onResetAction()
            NotificationsEvent.OnRetryClick -> getContent()
            is NotificationsEvent.OnNotificationClick -> onNotificationClick(viewEvent.id)
            is NotificationsEvent.OnSeeChangesClick -> onOpenOrderChanges(viewEvent.orderId)
        }
    }

    private fun getContent() {
        launchJob(context = appDispatchers.network, onError = {
            viewState = viewState.copy(isLoading = false, isError = true)
        }) {
            viewState = viewState.copy(isLoading = true, isError = false)
            val notificationsDomain = getNotificationsList()
            viewState = viewState.copy(
                notifications = notificationsDomain.map { mapper.map(it) }.sortedByDescending { it.notificationId },
                isLoading = false,
                isError = false
            )
            launchJob(appDispatchers.network) {
                val unreadNotifications = notificationsDomain
                    .filter { !it.isRead }
                    .map { it.id }
                    .sorted()
                if (unreadNotifications.isNotEmpty()) {
                    markNotificationsAsRead(unreadNotifications)
                }
            }
        }
    }

    private fun onNotificationClick(notificationId: Long) {
        viewState.notifications.firstOrNull { it.notificationId == notificationId }?.let { clickedNotification ->
            val orderId = clickedNotification.notificationId

            when (clickedNotification.status) {
                RouteNotificationsStatus.NEW,
                RouteNotificationsStatus.CANCELLED,
                RouteNotificationsStatus.ASSIGNED -> onOpenOrderDetails(orderId)
                RouteNotificationsStatus.DONE -> onOpenOrderPayment(orderId)
                RouteNotificationsStatus.CHANGED -> onOpenOrderChanges(orderId)
                else -> {}
            }
        }
    }

    private fun onOpenOrderChanges(orderId: Long) {
        viewAction = NotificationsAction.OpenConfirmChangesScreen(orderId)
    }

    private fun onOpenOrderDetails(orderId: Long) {
        // ...
    }

    private fun onOpenOrderPayment(orderId: Long) {
        // ...
    }

    private fun onBackClick() {
        viewAction = NotificationsAction.OpenPreviousScreen
    }
}
