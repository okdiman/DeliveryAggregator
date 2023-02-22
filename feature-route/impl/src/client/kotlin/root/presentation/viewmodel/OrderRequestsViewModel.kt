package root.presentation.viewmodel

import BaseViewModel
import coroutines.AppDispatchers
import network.exceptions.NotFoundException
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import root.domain.usecase.GetOrderRequestsUseCase
import root.presentation.compose.model.OrderStatusCategoryUiModel
import root.presentation.mapper.OrderRequestsUiMapper
import root.presentation.viewmodel.model.OrderRequestsAction
import root.presentation.viewmodel.model.OrderRequestsEvent
import root.presentation.viewmodel.model.OrderRequestsState

class OrderRequestsViewModel : BaseViewModel<OrderRequestsState, OrderRequestsAction, OrderRequestsEvent>(
    initialState = OrderRequestsState()
), KoinComponent {

    private val getOrderRequests by inject<GetOrderRequestsUseCase>()
    private val appDispatchers by inject<AppDispatchers>()
    private val mapper by inject<OrderRequestsUiMapper>()

    init {
        getContent()
    }

    override fun obtainEvent(viewEvent: OrderRequestsEvent) {
        when (viewEvent) {
            is OrderRequestsEvent.OnFilterByStatusClick -> onFilterOrdersByStatus(viewEvent.status)
            is OrderRequestsEvent.OnOpenOrderDetailsClick -> onOpenOrderDetailsClick(viewEvent.id)
            OrderRequestsEvent.OnCreateNewOrderClick -> onCreateNewOrderClick()
            OrderRequestsEvent.OnNotificationsClick -> onOpenNotificationsClick()
            OrderRequestsEvent.OnRetryClick -> getContent()
            OrderRequestsEvent.ResetAction -> onResetAction()
            OrderRequestsEvent.OnRefreshSwipe -> {
                viewState = viewState.copy(isRefreshing = true)
                getContent()
            }
        }
    }

    private fun onFilterOrdersByStatus(status: OrderStatusCategoryUiModel) {
        val shouldClear = (viewState.selectedCategoryFilter == status)
        viewState = viewState.copy(
            selectedCategoryFilter = if (shouldClear) null else status,
            filteredOrderRequests = if (shouldClear) {
                emptyList()
            } else viewState.orderRequests.filter { order ->
                order.statusCategory == status
            }
        )
    }

    private fun onOpenOrderDetailsClick(orderId: Long) {
        // ...
    }

    private fun onCreateNewOrderClick() {
        // ...
    }

    private fun onOpenNotificationsClick() {
        // ...
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
                        orderRequests = emptyList()
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
                orderRequests = mapper.map(orderRequests),
            )
        }
    }
}
