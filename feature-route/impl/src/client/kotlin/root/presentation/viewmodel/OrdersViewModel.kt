package root.presentation.viewmodel

import BaseViewModel
import coroutines.AppDispatchers
import network.exceptions.NotFoundException
import notifications.domain.usecase.GetUnreadNotificationsCountUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import root.domain.usecase.GetOrdersUseCase
import root.presentation.compose.model.OrderStatusCategoryUiModel
import root.presentation.mapper.OrdersUiMapper
import root.presentation.viewmodel.model.OrdersAction
import root.presentation.viewmodel.model.OrdersEvent
import root.presentation.viewmodel.model.OrdersState

class OrdersViewModel : BaseViewModel<OrdersState, OrdersAction, OrdersEvent>(
    initialState = OrdersState()
), KoinComponent {

    private val getUnreadNotificationsCount by inject<GetUnreadNotificationsCountUseCase>()
    private val getOrderRequests by inject<GetOrdersUseCase>()
    private val appDispatchers by inject<AppDispatchers>()
    private val mapper by inject<OrdersUiMapper>()

    init {
        getContent()
        getUnreadNotificationCount()
    }

    override fun obtainEvent(viewEvent: OrdersEvent) {
        when (viewEvent) {
            is OrdersEvent.OnFilterByStatusClick -> onFilterOrdersByStatus(viewEvent.status)
            is OrdersEvent.OnOpenOrderDetailsClick -> onOpenOrderDetailsClick(viewEvent.id)
            OrdersEvent.OnCreateNewOrderClick -> onCreateNewOrderClick()
            OrdersEvent.OnNotificationsClick -> onOpenNotificationsClick()
            OrdersEvent.OnRetryClick -> getContent()
            OrdersEvent.ResetAction -> onResetAction()
            OrdersEvent.OnRefreshSwipe -> {
                viewState = viewState.copy(isRefreshing = true)
                getContent()
            }
        }
    }

    private fun onFilterOrdersByStatus(status: OrderStatusCategoryUiModel) {
        val shouldClear = (viewState.selectedCategoryFilter == status)
        viewState = viewState.copy(
            selectedCategoryFilter = if (shouldClear) null else status,
            filteredOrders = if (shouldClear) {
                emptyList()
            } else viewState.orders.filter { order ->
                order.statusCategory == status
            }
        )
    }

    private fun onOpenOrderDetailsClick(orderId: Long) {
        // ...
    }

    private fun onCreateNewOrderClick() {
        viewAction = OrdersAction.OpenNewOrderScreen
    }

    private fun onOpenNotificationsClick() {
        viewAction = OrdersAction.OpenNotificationsScreen
    }

    private fun getUnreadNotificationCount() {
        launchJob(appDispatchers.network) {
            val count = getUnreadNotificationsCount()
            viewState = viewState.copy(
                notificationsCount = count
            )
        }
    }

    private fun getContent() {
        launchJob(
            context = appDispatchers.network,
            onError = {
                viewState = if (it is NotFoundException) {
                    viewState.copy(
                        isLoading = false,
                        isError = false,
                        isRefreshing = false,
                        orders = emptyList()
                    )
                } else {
                    viewState.copy(isLoading = false, isError = true, isRefreshing = false)
                }
            }
        ) {
            viewState = viewState.copy(isError = false, isLoading = true)
            val orderRequests = getOrderRequests()
            viewState = viewState.copy(
                isLoading = false,
                isRefreshing = false,
                orders = mapper.map(orderRequests),
            )
        }
    }
}
