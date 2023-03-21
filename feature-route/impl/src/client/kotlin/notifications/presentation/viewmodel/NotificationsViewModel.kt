package notifications.presentation.viewmodel

import BaseViewModel
import coroutines.AppDispatchers
import network.domain.GetAuthTokenSyncUseCase
import neworder.payment.domain.GetPaymentUriUseCase
import notifications.domain.model.NotificationServerModel
import notifications.domain.model.RouteNotificationsStatus
import notifications.domain.usecase.AssociateNotificationsToOrdersUseCase
import notifications.domain.usecase.GetNotificationsListUseCase
import notifications.domain.usecase.MarkNotificationsAsReadUseCase
import notifications.presentation.mapper.NotificationUiMapper
import notifications.presentation.viewmodel.model.NotificationsAction
import notifications.presentation.viewmodel.model.NotificationsEvent
import notifications.presentation.viewmodel.model.NotificationsState
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import root.domain.model.status.OrderStatusProgress

class NotificationsViewModel : BaseViewModel<NotificationsState, NotificationsAction, NotificationsEvent>(
    initialState = NotificationsState()
), KoinComponent {

    private val getNotificationsList by inject<GetNotificationsListUseCase>()
    private val associateNotificationsToOrders by inject<AssociateNotificationsToOrdersUseCase>()
    private val appDispatchers by inject<AppDispatchers>()
    private val markNotificationsAsRead by inject<MarkNotificationsAsReadUseCase>()
    private val mapper by inject<NotificationUiMapper>()
    private val getPaymentUri by inject<GetPaymentUriUseCase>()
    private val getAuthTokenSync by inject<GetAuthTokenSyncUseCase>()

    private var notifications: List<NotificationServerModel> = emptyList()

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
            notifications = getNotificationsList().distinctBy { notification ->
                /**
                 * Сервер может присылать дублирующиеся уведомления для одного и того же заказа,
                 * поэтому пока что обрабатываем этот недочет на клиенте при помощи `distinctBy`
                 */
                notification.data.subjectId to notification.data.status
            }

            associateNotificationsToOrders(
                notifications = notifications,
                associateIf = { notification ->
                    notification.data.status == RouteNotificationsStatus.DONE ||
                        notification.data.status == RouteNotificationsStatus.CHANGED
                }
            )

            viewState = viewState.copy(
                notifications = notifications.map { mapper.map(it) }.sortedByDescending { it.notificationId },
                isLoading = false,
                isError = false
            )
            launchJob(appDispatchers.network) {
                val unreadNotifications = notifications
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
        notifications.firstOrNull { it.id == notificationId }?.let { clickedNotification ->
            val orderId = clickedNotification.data.subjectId

            when (clickedNotification.data.status) {
                RouteNotificationsStatus.NEW,
                RouteNotificationsStatus.CANCELLED,
                RouteNotificationsStatus.ASSIGNED -> onOpenOrderDetails(orderId)
                RouteNotificationsStatus.DONE -> {
                    if (clickedNotification.associatedOrder?.isPaid == false) {
                        onOpenOrderPayment(orderId)
                    } else {
                        onOpenOrderDetails(orderId)
                    }
                }
                RouteNotificationsStatus.CHANGED -> {
                    if (clickedNotification.associatedOrder?.status == OrderStatusProgress.CHANGED) {
                        onOpenOrderChanges(orderId)
                    } else {
                        onOpenOrderDetails(orderId)
                    }
                }
                else -> {}
            }
        }
    }

    private fun onOpenOrderChanges(orderId: Long) {
        viewAction = NotificationsAction.OpenConfirmChangesScreen(orderId)
    }

    private fun onOpenOrderDetails(orderId: Long) {
        viewAction = NotificationsAction.OpenOrderDetails(orderId)
    }

    private fun onOpenOrderPayment(orderId: Long) {
        getAuthTokenSync()?.let { token ->
            viewAction = NotificationsAction.OpenPaymentInBrowser(getPaymentUri(orderId, token))
        }
    }

    private fun onBackClick() {
        viewAction = NotificationsAction.OpenPreviousScreen
    }
}
