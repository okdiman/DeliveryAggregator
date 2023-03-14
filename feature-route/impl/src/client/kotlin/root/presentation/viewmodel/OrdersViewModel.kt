package root.presentation.viewmodel

import BaseViewModel
import coroutines.AppDispatchers
import network.domain.GetAuthTokenSyncUseCase
import network.exceptions.NotFoundException
import neworder.creationerror.presentation.CreationErrorParameters
import neworder.payment.domain.GetPaymentUriUseCase
import notifications.domain.usecase.GetUnreadNotificationsCountUseCase
import orderdetails.root.domain.model.OrderDetailsModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import permissions.AppPermissionState
import permissions.PermissionsConstants
import permissions.domain.interactor.PermissionsInteractor
import presentation.DeeplinkParameters
import root.DeeplinkNavigatorHandler
import root.domain.model.status.OrderStatusProgress
import root.domain.usecase.GetOrdersUseCase
import root.presentation.compose.model.OrderStatusCategoryUiModel
import root.presentation.mapper.OrdersUiMapper
import root.presentation.viewmodel.model.OrdersAction
import root.presentation.viewmodel.model.OrdersEvent
import root.presentation.viewmodel.model.OrdersState
import trinity_monsters.delivery_aggregator.feature_route.impl.R
import utils.CommonConstants.Helpers.RUBBLES
import utils.ext.getSafeQueryParameter
import utils.resource.domain.ResourceInteractor

class OrdersViewModel(private val deeplinkParameters: DeeplinkParameters?) :
    BaseViewModel<OrdersState, OrdersAction, OrdersEvent>(initialState = OrdersState()), KoinComponent {

    private val getUnreadNotificationsCount by inject<GetUnreadNotificationsCountUseCase>()
    private val getOrderRequests by inject<GetOrdersUseCase>()
    private val getAuthTokenSyncUseCase by inject<GetAuthTokenSyncUseCase>()
    private val appDispatchers by inject<AppDispatchers>()
    private val permission by inject<PermissionsInteractor>()
    private val mapper by inject<OrdersUiMapper>()
    private val deeplinkNavigatorHandler by inject<DeeplinkNavigatorHandler>()
    private val resourceInteractor by inject<ResourceInteractor>()
    private val getPaymentUri by inject<GetPaymentUriUseCase>()

    private var orderRequests: List<OrderDetailsModel> = emptyList()

    init {
        checkDeeplink()
        getContent()
        getUnreadNotificationCount()
    }

    override fun obtainEvent(viewEvent: OrdersEvent) {
        when (viewEvent) {
            is OrdersEvent.OnFilterByStatusClick -> onFilterOrdersByStatus(viewEvent.status)
            is OrdersEvent.OnOpenOrderDetailsClick -> onOpenOrderDetailsClick(viewEvent.id)
            is OrdersEvent.OnPermissionStateChanged -> onPermissionStateChanged(viewEvent.permissionState)
            OrdersEvent.OnCreateNewOrderClick -> onCreateNewOrderClick()
            OrdersEvent.OnNotificationsClick -> onOpenNotificationsClick()
            OrdersEvent.OnRetryClick -> getContent()
            OrdersEvent.ResetAction -> onResetAction()
            OrdersEvent.OnRationaleDismiss -> onRationaleDismiss()
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
        val unpaidOrders = orderRequests.filter { it.status == OrderStatusProgress.DONE && !it.isPaid }
            .sortedBy { it.arrivalDay }
        viewAction = if (unpaidOrders.isNotEmpty()) {
            getAuthTokenSyncUseCase()?.let { token ->
                OrdersAction.OpenCreationErrorScreen(
                    CreationErrorParameters(getPaymentUri(unpaidOrders.first().id, token))
                )
            }
        } else {
            OrdersAction.OpenNewOrderScreen
        }
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
            orderRequests = getOrderRequests()
            viewState = viewState.copy(
                isLoading = false,
                isRefreshing = false,
                orders = mapper.map(orderRequests),
            )
        }
    }

    /**
     * навигация по диплинкам
     */
    private fun checkDeeplink() {
        viewAction = when (deeplinkNavigatorHandler.getDestination(deeplinkParameters?.uri)) {
            PAYMENT_SUCCESS -> {
                val price = deeplinkParameters?.uri?.getSafeQueryParameter(PRICE)?.let {
                    val withRubbles = buildString { append(it + RUBBLES) }
                    String.format(
                        resourceInteractor.getString(R.string.new_order_payment_subtitle),
                        withRubbles
                    )
                }
                OrdersAction.OpenPaymentSuccess(price)
            }
            else -> null
        }
    }

    private fun onPermissionStateChanged(state: AppPermissionState) {
        launchJob {
            when (state) {
                AppPermissionState.Rationale -> {
                    if (!permission.isShowRationaleDismissed(PermissionsConstants.Notification)) {
                        viewState = viewState.copy(notificationsPermission = state)
                    }
                }
                else -> {
                    viewState = viewState.copy(notificationsPermission = state)
                }
            }
        }
    }

    private fun onRationaleDismiss() {
        launchJob {
            viewState = viewState.copy(notificationsPermission = AppPermissionState.Denied)
            permission.setRationaleDismissed(PermissionsConstants.Notification)
        }
    }


    private companion object {
        const val PAYMENT_SUCCESS = "success"
        const val PRICE = "price"
    }
}
